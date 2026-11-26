package com.andesfresh.portal.controller;

import com.andesfresh.portal.model.Producto;
import com.andesfresh.portal.service.ProductoService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
    private final ProductoService service;
    public ReporteController(ProductoService service) { this.service = service; }

    @GetMapping("/csv")
    public void exportarCsv(HttpServletResponse res) throws IOException {
        res.setContentType("text/csv");
        res.setCharacterEncoding("UTF-8");
        res.setHeader("Content-Disposition", "attachment; filename=productos.csv");
        PrintWriter w = res.getWriter();
        w.write('\uFEFF');
        w.println("ID,Nombre,Marca,Categoria,Precio,Cantidad");
        for (Producto p : service.listar()) {
            w.printf(Locale.US, "%d,%s,%s,%s,%.2f,%d%n", p.getId(), p.getNombre(), p.getMarca(), p.getCategoria(), p.getPrecio(), p.getCantidad());
        }
        w.flush();
    }

    @GetMapping("/excel")
    public void exportarExcel(HttpServletResponse res) throws IOException {
        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        res.setHeader("Content-Disposition", "attachment; filename=productos.xlsx");
        try (Workbook wb = new XSSFWorkbook()) {
            Sheet s = wb.createSheet("Productos");
            Font f = wb.createFont(); f.setBold(true);
            CellStyle cs = wb.createCellStyle(); cs.setFont(f);
            String[] cab = {"ID", "Nombre", "Marca", "Categoría", "Precio", "Cantidad", "Subtotal"};
            Row r0 = s.createRow(0);
            for (int i = 0; i < cab.length; i++) {
                r0.createCell(i).setCellValue(cab[i]);
                r0.getCell(i).setCellStyle(cs);
                s.setColumnWidth(i, 18 * 256);
            }
            int rowNum = 1;
            for (Producto p : service.listar()) {
                Row r = s.createRow(rowNum);
                int ex = rowNum + 1;
                r.createCell(0).setCellValue(p.getId());
                r.createCell(1).setCellValue(p.getNombre());
                r.createCell(2).setCellValue(p.getMarca());
                r.createCell(3).setCellValue(p.getCategoria());
                r.createCell(4).setCellValue(p.getPrecio());
                r.createCell(5).setCellValue(p.getCantidad());
                r.createCell(6).setCellFormula("E" + ex + "*F" + ex);
                rowNum++;
            }
            Row tot = s.createRow(rowNum);
            tot.createCell(5).setCellValue("TOTAL");
            tot.getCell(5).setCellStyle(cs);
            tot.createCell(6).setCellFormula("SUM(G2:G" + rowNum + ")");
            wb.write(res.getOutputStream());
        }
    }

    @GetMapping("/pdf")
    public void generarPdf(HttpServletResponse res) throws Exception {
        try (InputStream jrxml = getClass().getResourceAsStream("/reportes/productos.jrxml")) {
            JasperReport rep = JasperCompileManager.compileReport(jrxml);
            Map<String, Object> params = Map.of("titulo", "Catálogo de productos - Andes Fresh S.A.C.");
            JasperPrint jp = JasperFillManager.fillReport(rep, params, new JRBeanCollectionDataSource(service.listar()));
            res.setContentType("application/pdf");
            res.setHeader("Content-Disposition", "inline; filename=productos.pdf");
            JasperExportManager.exportReportToPdfStream(jp, res.getOutputStream());
        }
    }
}
