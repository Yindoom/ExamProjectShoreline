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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Yindo
 */
public class MainViewController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAdm;
    @FXML
    private Label AdminPass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleAdmin(ActionEvent event) {
    }

    @FXML
    private void handleLogin(ActionEvent event) {
    }

}
