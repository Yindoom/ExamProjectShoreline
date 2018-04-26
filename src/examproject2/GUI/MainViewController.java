/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Admin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Yindo
 */
public class MainViewController implements Initializable {

    Model model = Model.getInstance();

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAdm;
    @FXML
    private Label AdminPass;
    @FXML
    private Button btnAdmin;
    @FXML
    private Label lblName;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtAdm.setVisible(false);
        AdminPass.setVisible(false);
    }

    @FXML
    private void handleAdmin(ActionEvent event) {
        txtAdm.setVisible(true);
        AdminPass.setVisible(true);
        btnAdmin.setVisible(false);
        lblName.setText("Please insert username");
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        if (!txtAdm.getText().isEmpty()) {
            Admin admin = new Admin();
            admin.setName("adm");
            admin.setPassword("1");
            if (txtName.getText().equals(admin.getName()) && txtAdm.getText().equals(admin.getPassword())) {
                
                Stage primaryStage = new Stage();
                primaryStage.initModality(Modality.WINDOW_MODAL);
                FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("ConverterView.fxml"));

                Parent root = fxLoader.load();
                ConverterViewController cvc = fxLoader.getController();
                cvc.setUser(admin);

                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.showAndWait();
                
                Stage oldStage = (Stage) loginButton.getScene().getWindow();
                oldStage.close();
            }
        } 
        else {
            Stage primaryStage = new Stage();
            primaryStage.initModality(Modality.WINDOW_MODAL);
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("ConverterView.fxml"));

            Parent root = fxLoader.load();
            ConverterViewController cvc = fxLoader.getController();
            cvc.setUser(txtName.getText());

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.showAndWait();
            
            Stage oldStage = (Stage) loginButton.getScene().getWindow();
            oldStage.close();
        }
    }
}
