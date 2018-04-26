/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Admin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    }

    @FXML
    private void btnConvert(ActionEvent event) {
    }
    
    @FXML
    private void Configure(ActionEvent event) {
    }

    public void setUser(String user) {
        currentUser = user;
        admConfig.setVisible(false);
    }

    void setUser(Admin admin) {
        currentUser = admin.getName();
    }
    
}
