/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Config;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author ZeXVex
 */
public class AdminSettingsViewController implements Initializable {
    
    Model model = Model.getInstance();

    @FXML
    private ListView<Config> lstConfiguration;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstConfiguration.getItems().setAll(model.getConfigs());
        // TODO
    }    

    @FXML
    private void btnEdit(ActionEvent event) {
    }

    @FXML
    private void btnAdd(ActionEvent event) {
        
    }
    
}
