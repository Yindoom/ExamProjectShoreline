/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Admin;
import examproject2.BE.Config;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * FXML Controller class
 *
 * @author ZeXVex
 */
public class ConverterViewController implements Initializable {

    Model model = Model.getInstance();

    String currentUser;

    @FXML
    private ComboBox<Config> cbmSettings;
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
    @FXML
    private TextField txtSavePath;
    @FXML
    private Label lblUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setConfigs();
    }

    @FXML
    private void btnFilePath(ActionEvent event) {
        String Stringpath = null;

        final FileChooser fileChooser = new FileChooser();

        File filePath = fileChooser.showOpenDialog(null);
        if (filePath != null) {
            Stringpath = filePath.getAbsolutePath();
        }
        txtPath.setText(Stringpath);
    }

    @FXML
    private void btnConvert(ActionEvent event) throws IOException, InvalidFormatException {
        try {
            model.convert(txtPath.getText(), txtSavePath.getText(), cbmSettings.getSelectionModel().getSelectedItem());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Complete");
            alert.setHeaderText("Conversion Succesful");
            alert.setContentText("File succesfully converted");
            alert.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Please make sure you have chosen a file to convert, a configuration, and a save folder");
            alert.show();
        }
    }

    @FXML
    private void Configure(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AdminSettingsView.fxml"));

        Parent root = fxLoader.load();
        AdminSettingsViewController ctrl = fxLoader.getController();
        ctrl.setUser(currentUser);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    public void setUser(String user) {
        currentUser = user;
    }

    @FXML
    private void btnActivity(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Sorry");
        alert.setHeaderText(currentUser);
        alert.setContentText("This window has not been implemented yet. \t Thank you for your patience.");
        alert.show();
    }

    private void setConfigs() {
        cbmSettings.getItems().setAll(model.getConfigs());
    }

    @FXML
    private void btnFilePathSave(ActionEvent event) {
        String Stringpath = null;

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            Stringpath = selectedDirectory.getAbsolutePath();
        }
        txtSavePath.setText(Stringpath);
    }

}
