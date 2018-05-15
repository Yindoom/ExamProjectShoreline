/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Config;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZeXVex
 */
public class AdminSettingsViewController implements Initializable {

    Model model = Model.getInstance();

    String currentUser;
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
    private void btnEdit(ActionEvent event) throws IOException {
        Config selectedConfig
                = lstConfiguration.getSelectionModel().getSelectedItem();
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("ConfigView.fxml"));

        Parent root = fxLoader.load();
        ConfigViewController cvc = fxLoader.getController();
        cvc.setUser(currentUser, " edited ");
        cvc.setConfig(selectedConfig);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    @FXML
    private void btnAdd(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("ConfigView.fxml"));

        Parent root = fxLoader.load();
        ConfigViewController cvc = fxLoader.getController();
        cvc.setUser(currentUser, " added ");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();

    }

    public void setUser(String currentUser) {
        this.currentUser = currentUser;
    }

}
