/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Activity;
import examproject2.BE.Config;
import examproject2.BE.Conversion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import examproject2.GUI.AdminSettingsViewController;

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
    private TableView<Conversion> tbvConversions;
    @FXML
    private TableColumn<Conversion, String> convertName;
    @FXML
    private TableColumn<Conversion, Double> convertProgress;
    @FXML
    private TextField txtPath;
    @FXML
    private TextField txtSavePath;
    @FXML
    private Label lblUser;
    @FXML
    private TableColumn<Conversion, String> convertPath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        convertName.setCellValueFactory((cellFeatures) -> cellFeatures.getValue().fileNameProperty());
        convertProgress.setCellValueFactory(new PropertyValueFactory<Conversion, Double>(
                "progress"));
        convertProgress
                .setCellFactory(ProgressBarTableCell.<Conversion>forTableColumn());
        convertPath.setCellValueFactory((cellFeatures) -> cellFeatures.getValue().filePathProperty());
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
    private void btnConvert(ActionEvent event) throws IOException, InvalidFormatException, OutOfMemoryError {
        try {
            model.convert(tbvConversions.getItems(), cbmSettings.getSelectionModel().getSelectedItem());
        } catch (OutOfMemoryError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("A file is too big, and cannot be converted");
            alert.setHeaderText("Out of memory");
            alert.showAndWait();
        }
    private void btnConvert(ActionEvent event) throws IOException, InvalidFormatException {
        for (Conversion con : tbvConversions.getItems()) {
            Activity log = new Activity();
            log.setSubject(con.getFileName());
            log.setName(currentUser);
            log.setType("Convert");
            model.saveActivity(log);

        }

        model.convert(tbvConversions.getItems(), cbmSettings.getSelectionModel().getSelectedItem());
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
        lblUser.setText(user);
        this.currentUser = user;
    }

    @FXML
    private void btnActivity(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("ActivityView.fxml"));

        Parent root = fxLoader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
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

    @FXML
    private void addConversion(ActionEvent event) {
        if (!txtPath.getText().isEmpty() && !txtSavePath.getText().isEmpty()) {
            Conversion con = new Conversion();
            con.setFileName(FilenameUtils.getBaseName(txtPath.getText()));
            con.setFilePath(txtPath.getText());
            con.setSavePath(txtSavePath.getText());

            tbvConversions.getItems().add(con);
        }
    }

    @FXML
    private void clickStop(ActionEvent event) {
        for (Conversion con : tbvConversions.getItems()) {
            con.getTask().interrupt();
        }
    }

}
