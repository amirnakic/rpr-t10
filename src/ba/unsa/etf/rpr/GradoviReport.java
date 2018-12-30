package ba.unsa.etf.rpr;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class GradoviReport extends JFrame {
    public void showReport(Connection conn) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/geografijainvoice.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
    }

    public void saveAs(String format, Connection conn, String filePath) throws JRException {
        String reportSrcFile = getClass().getResource("/reports/geografijainvoice.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        switch (format) {
            case ".pdf":
                JasperExportManager.exportReportToPdfFile(print, filePath);
                break;
            case ".docx":
                JRDocxExporter export = new JRDocxExporter();
                export.setExporterInput(new SimpleExporterInput(print));
                export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(filePath)));
                SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
                export.setConfiguration(config);
                export.exportReport();
                break;
            case ".xlsx":
                JRXlsxExporter export1 = new JRXlsxExporter();
                export1.setExporterInput(new SimpleExporterInput(print));
                export1.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(filePath)));
                SimpleXlsxReportConfiguration config1 = new SimpleXlsxReportConfiguration();
                export1.setConfiguration(config1);
                export1.exportReport();
                break;
            default:
                System.out.println("Nema te ekstenzije!");
                break;
        }
    }
}