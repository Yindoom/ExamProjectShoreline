/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Name;
import java.awt.Component;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    @FXML
    private Button btnAdmin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtAdm.setVisible(false);
        AdminPass.setVisible(false);
    }

    Name person1 = new Name("Hello");

    @FXML
    private void handleAdmin(ActionEvent event) {
        txtAdm.setVisible(true);
        AdminPass.setVisible(true);
        btnAdmin.setVisible(false);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        if (!txtAdm.getText().isEmpty()) {

        }
        else {
        }

    }
}
