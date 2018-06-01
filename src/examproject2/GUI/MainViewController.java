/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Yindo
 */
public class MainViewController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    //Nicolai & Bastian
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        if (!txtName.getText().isEmpty()) {
            Stage primaryStage = new Stage();
            primaryStage.initModality(Modality.WINDOW_MODAL);
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));

            Parent root = fxLoader.load();
            MainWindowController cvc = fxLoader.getController();
            cvc.setUser(txtName.getText());

            Stage oldStage = (Stage) loginButton.getScene().getWindow();
            oldStage.close();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.showAndWait();
        }
    }
}
