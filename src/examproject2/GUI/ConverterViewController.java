/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ZeXVex
 */
public class ConverterViewController implements Initializable {

    @FXML
    private ComboBox<?> cbmSettings;
    @FXML
    private TableView<?> tbvConvertProcess;
    @FXML
    private TextField txfFielPath;
    @FXML
    private Button admConfig;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void Configeure(ActionEvent event) {
    }

    @FXML
    private void btnFilePath(ActionEvent event) {
    }

    @FXML
    private void btnConvert(ActionEvent event) {
    }
    
}
