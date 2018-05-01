/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Admin;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import javafx.stage.Popup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author ZeXVex
 */
public class ConverterViewController implements Initializable {
    
    Model model = Model.getInstance();
    
    String currentUser;

    
    @FXML
    private ComboBox<?> cbmSettings;
    @FXML
    private Button admConfig;
    @FXML
    private TableView<?> tbvConversions;
    @FXML
    private TableColumn<?, ?> convertName;
    @FXML
    private TableColumn<?, ?> convertProgress;
    @FXML
    private TextField txtPath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


    @FXML
    private void btnFilePath(ActionEvent event) {
        String Stringpath = null;
        
        final FileChooser fileChooser = new FileChooser();
        
        File filePath = fileChooser.showOpenDialog(null);
        if (filePath != null)
        {
            Stringpath = filePath.getAbsolutePath();
        }
        txtPath.setText(Stringpath);
    }

    @FXML
    private void btnConvert(ActionEvent event) throws IOException, InvalidFormatException {
        model.convert(txtPath.getText());
        }
    
    @FXML
    private void Configure(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Please be patient");
        alert.setHeaderText(currentUser);
        alert.setContentText("This feature is not available as of yet");
        alert.show();
    }

    public void setUser(String user) {
        currentUser = user;
        admConfig.setVisible(false);
    }

    public void setUser(Admin admin) {
        currentUser = admin.getName();
    }

    @FXML
    private void btnActivity(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Wow there pardner");
        alert.setHeaderText(currentUser);
        alert.setContentText("You can't do that on TV");
        alert.show();
    }
    
}
