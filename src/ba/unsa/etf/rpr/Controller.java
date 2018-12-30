package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class Controller {
    public TextField textField;
    public Label glavniGradLbl, drzavaLbl, glavniGradLbl2;
    public GeografijaDAO dao;
    public Button pronadiBtn, stampajBtn;
    public Menu fajl, pogled, jezik;
    public MenuItem snimi, bosanski, engleski, njemački, francuski;

    public Controller() {
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        dao = GeografijaDAO.getInstance();
    }

    public void clickOnButton(ActionEvent actionEvent) {
        Grad g = dao.glavniGrad(textField.getText());
        if (g == null) {
            glavniGradLbl.setText("GREŠKA!");
            return;
        }
        glavniGradLbl.setText(g.getNaziv().toUpperCase());
    }

    public void clickOnŠtampaj(ActionEvent actionEvent) {
        try {
            new GradoviReport().showReport(dao.getConn());
        } catch (JRException e1) {
            e1.printStackTrace();
        }

    }

    public void bosanski(ActionEvent actionEvent) {
        fajl.setText(ResourceBundle.getBundle("Translation_bs").getString("Fajl"));
        pogled.setText(ResourceBundle.getBundle("Translation_bs").getString("Pogled"));
        jezik.setText(ResourceBundle.getBundle("Translation_bs").getString("Jezik"));
        snimi.setText(ResourceBundle.getBundle("Translation_bs").getString("Snimi"));
        bosanski.setText(ResourceBundle.getBundle("Translation_bs").getString("Bosanski"));
        engleski.setText(ResourceBundle.getBundle("Translation_bs").getString("Engleski"));
        njemački.setText(ResourceBundle.getBundle("Translation_bs").getString("Njemacki"));
        francuski.setText(ResourceBundle.getBundle("Translation_bs").getString("Francuski"));
        pronadiBtn.setText(ResourceBundle.getBundle("Translation_bs").getString("Pronadji"));
        stampajBtn.setText(ResourceBundle.getBundle("Translation_bs").getString("Stampaj"));
        drzavaLbl.setText(ResourceBundle.getBundle("Translation_bs").getString("Drzava"));
        glavniGradLbl2.setText(ResourceBundle.getBundle("Translation_bs").getString("Glavni_grad"));
    }

    public void engleski(ActionEvent actionEvent) {
        fajl.setText(ResourceBundle.getBundle("Translation_en").getString("Fajl"));
        pogled.setText(ResourceBundle.getBundle("Translation_en").getString("Pogled"));
        jezik.setText(ResourceBundle.getBundle("Translation_en").getString("Jezik"));
        snimi.setText(ResourceBundle.getBundle("Translation_en").getString("Snimi"));
        bosanski.setText(ResourceBundle.getBundle("Translation_en").getString("Bosanski"));
        engleski.setText(ResourceBundle.getBundle("Translation_en").getString("Engleski"));
        njemački.setText(ResourceBundle.getBundle("Translation_en").getString("Njemacki"));
        francuski.setText(ResourceBundle.getBundle("Translation_en").getString("Francuski"));
        pronadiBtn.setText(ResourceBundle.getBundle("Translation_en").getString("Pronadji"));
        stampajBtn.setText(ResourceBundle.getBundle("Translation_en").getString("Stampaj"));
        drzavaLbl.setText(ResourceBundle.getBundle("Translation_en").getString("Drzava"));
        glavniGradLbl2.setText(ResourceBundle.getBundle("Translation_en").getString("Glavni_grad"));
    }

    public void njemacki(ActionEvent actionEvent) {
        fajl.setText(ResourceBundle.getBundle("Translation_de").getString("Fajl"));
        pogled.setText(ResourceBundle.getBundle("Translation_de").getString("Pogled"));
        jezik.setText(ResourceBundle.getBundle("Translation_de").getString("Jezik"));
        snimi.setText(ResourceBundle.getBundle("Translation_de").getString("Snimi"));
        bosanski.setText(ResourceBundle.getBundle("Translation_de").getString("Bosanski"));
        engleski.setText(ResourceBundle.getBundle("Translation_de").getString("Engleski"));
        njemački.setText(ResourceBundle.getBundle("Translation_de").getString("Njemacki"));
        francuski.setText(ResourceBundle.getBundle("Translation_de").getString("Francuski"));
        pronadiBtn.setText(ResourceBundle.getBundle("Translation_de").getString("Pronadji"));
        stampajBtn.setText(ResourceBundle.getBundle("Translation_de").getString("Stampaj"));
        drzavaLbl.setText(ResourceBundle.getBundle("Translation_de").getString("Drzava"));
        glavniGradLbl2.setText(ResourceBundle.getBundle("Translation_de").getString("Glavni_grad"));
    }

    public void francuski(ActionEvent actionEvent) {
        fajl.setText(ResourceBundle.getBundle("Translation_fr").getString("Fajl"));
        pogled.setText(ResourceBundle.getBundle("Translation_fr").getString("Pogled"));
        jezik.setText(ResourceBundle.getBundle("Translation_fr").getString("Jezik"));
        snimi.setText(ResourceBundle.getBundle("Translation_fr").getString("Snimi"));
        bosanski.setText(ResourceBundle.getBundle("Translation_fr").getString("Bosanski"));
        engleski.setText(ResourceBundle.getBundle("Translation_fr").getString("Engleski"));
        njemački.setText(ResourceBundle.getBundle("Translation_fr").getString("Njemacki"));
        francuski.setText(ResourceBundle.getBundle("Translation_fr").getString("Francuski"));
        pronadiBtn.setText(ResourceBundle.getBundle("Translation_fr").getString("Pronadji"));
        stampajBtn.setText(ResourceBundle.getBundle("Translation_fr").getString("Stampaj"));
        drzavaLbl.setText(ResourceBundle.getBundle("Translation_fr").getString("Drzava"));
        glavniGradLbl2.setText(ResourceBundle.getBundle("Translation_fr").getString("Glavni_grad"));
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public void clickOnSave(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter docFilter = new FileChooser.ExtensionFilter("Microsoft Word Documents (*.docx)", "*.docx");
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF Documents (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter xlsFilter = new FileChooser.ExtensionFilter("Microsoft Excel Documents (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(docFilter);
        fileChooser.getExtensionFilters().add(pdfFilter);
        fileChooser.getExtensionFilters().add(xlsFilter);
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            GradoviReport gradoviReport = new GradoviReport();
            try {
                gradoviReport.saveAs(getFileExtension(file), GeografijaDAO.getConn(), file.getCanonicalPath());
            } catch (JRException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
