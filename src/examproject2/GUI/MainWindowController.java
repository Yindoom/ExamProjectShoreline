/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Activity;
import examproject2.BE.Config;
import examproject2.BE.Conversion;
import examproject2.BE.Key;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
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

/**
 * FXML Controller class
 *
 * @author Fábio
 */

public class MainWindowController implements Initializable {

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
    @FXML
    private ScrollPane homePane;
    ////////////////////////
    @FXML
    private TableView<Activity> lstActivity;
    @FXML
    private TableColumn<Activity, String> colUse;
    @FXML
    private TableColumn<Activity, String> colAct;
    @FXML
    private TableColumn<Activity, String> colSub;
    ///////////////////////
    
    @FXML
    private ListView<Config> lstConfiguration;
    /////////////////
    
    Boolean edit = false;
    String activity;
    String user;
    int id;
    @FXML
    private TextField configName;
    @FXML
    private TextField typeKey;
    @FXML
    private TextField typeSec;
    @FXML
    private TextField typeDef;
    @FXML
    private TextField externalKey;
    @FXML
    private TextField externalSec;
    @FXML
    private TextField externalDef;
    @FXML
    private TextField sysKey;
    @FXML
    private TextField sysSec;
    @FXML
    private TextField sysDef;
    @FXML
    private TextField userKey;
    @FXML
    private TextField userSec;
    @FXML
    private TextField userDef;
    @FXML
    private TextField nameKey;
    @FXML
    private TextField nameSec;
    @FXML
    private TextField nameDef;
    @FXML
    private TextField priorityKey;
    @FXML
    private TextField prioritySec;
    @FXML
    private TextField priorityDef;
    @FXML
    private TextField statusKey;
    @FXML
    private TextField statusSec;
    @FXML
    private TextField statusDef;
    @FXML
    private TextField finishKey;
    @FXML
    private TextField finishSec;
    @FXML
    private TextField finishDef;
    @FXML
    private TextField earlyStartKey;
    @FXML
    private TextField earlyStartSec;
    @FXML
    private TextField earlyStartDef;
    @FXML
    private TextField lateStartKey;
    @FXML
    private TextField lateStartSec;
    @FXML
    private TextField lateStartDef;
    @FXML
    private TextField timeKey;
    @FXML
    private TextField timeSec;
    @FXML
    private TextField timeDef;
    @FXML
    private ComboBox<String> fileType;
    /////////////////////////////////////
    
    @FXML
    private ScrollPane logPane;
    @FXML
    private ScrollPane settingPane;
    @FXML
    private ScrollPane setPane;

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
        
        colUse.setCellValueFactory((cellFeatures) -> cellFeatures.getValue().nameProperty());
        colAct.setCellValueFactory((cellFeatures) -> cellFeatures.getValue().typeProperty());
        colSub.setCellValueFactory((cellFeatures) -> cellFeatures.getValue().subjectProperty());
        lstActivity.setItems(model.getActivity()); 
        
        lstConfiguration.getItems().setAll(model.getConfigs());
        
        cbmSettings.setPromptText("Standard");
        // TODO
    }    

    public void setUser(String user) {
        lblUser.setText(user);
        this.currentUser = user;
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
    
    //////////////////////////
    @FXML
    private void btnEdit(ActionEvent event) throws IOException {
        Config selectedConfig
                = lstConfiguration.getSelectionModel().getSelectedItem();
        setConfig(selectedConfig);
        setPane.toFront();
        /*Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("ConfigView.fxml"));

        Parent root = fxLoader.load();
        ConfigViewController cvc = fxLoader.getController();
        cvc.setUser(currentUser, "Edit");
        cvc.setConfig(selectedConfig);

        Stage oldStage = (Stage) lstConfiguration.getScene().getWindow();
        oldStage.close();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();*/
        
    }

    @FXML
    private void btnAdd(ActionEvent event) throws IOException {
        /*Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("ConfigView.fxml"));

        Parent root = fxLoader.load();
        ConfigViewController cvc = fxLoader.getController();
        cvc.setUser(currentUser, "Add");

        Stage oldStage = (Stage) lstConfiguration.getScene().getWindow();
        oldStage.close();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();*/
        
        setPane.toFront();
        

    }
    ////////////////////////////
    
    public void setUser(String currentUser, String activity) {
        ObservableList<String> files = FXCollections.observableArrayList();
        files.add("xlsx");
        files.add("csv");
        user = currentUser;
        this.activity = activity;
        fileType.setItems(files);
    }

    @FXML
    private void clickSave(ActionEvent event) throws IOException {
        List<Key> keys = new ArrayList();

        keys.add(new Key("type"));
        keys.add(new Key("externalWorkOrderId"));
        keys.add(new Key("systemStatus"));
        keys.add(new Key("userStatus"));
        keys.add(new Key("name"));
        keys.add(new Key("priority"));
        keys.add(new Key("status"));
        keys.add(new Key("latestFinishDate"));
        keys.add(new Key("earliestStartDate"));
        keys.add(new Key("latestStartDate"));
        keys.add(new Key("estimatedTime"));

        save(keys);

        if (!edit) {
            boolean proceed = true;
            for (Config config : model.getConfigs()) {
                if (config.getName().equals(configName.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Config already exists");
                    alert.setContentText("This config name already exists, please choose a different name");
                    alert.showAndWait();
                    proceed = false;
                }
            }
            if (proceed) {

                Config config = new Config();
                config.setFileType(fileType.getSelectionModel().getSelectedItem());
                config.setName(configName.getText());
                model.saveConfig(config, keys);

                Activity log = new Activity();
                log.setSubject(configName.getText());
                log.setName(user);
                log.setType(activity);

                model.saveActivity(log);
            }
        } else {
            for (Key key : keys) {
                key.setId(id);
            }
            model.updateConfig(keys);

            Activity log = new Activity();

            log.setSubject(configName.getText());
            log.setName(user);
            log.setType(activity);

            model.saveActivity(log);

        }
        /*Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AdminSettingsView.fxml"));

        Parent root = fxLoader.load();

        Stage oldStage = (Stage) configName.getScene().getWindow();
        oldStage.close();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();*/
        settingPane.toFront();

    }

    public void setConfig(Config selectedConfig) {
        configName.setText(selectedConfig.getName());
        this.id = selectedConfig.getId();
        edit = true;
        List<Key> keys = model.getKeys(selectedConfig);
        for (Key key : keys) {
            if (key.getJsonAttribute().equals("type")) {
                if (key.getKeyWord() != null) {
                    typeKey.setText(key.getKeyWord());
                }
                if (key.getSecondaryKeyWord() != null) {
                    typeSec.setText(key.getSecondaryKeyWord());
                }
                if (key.getDefaultValue() != null) {
                    typeDef.setText(key.getDefaultValue());
                }
            }
            if (key.getJsonAttribute().equals("externalWorkOrderId")) {
                if (key.getKeyWord() != null) {
                    externalKey.setText(key.getKeyWord());
                }
                if (key.getSecondaryKeyWord() != null) {
                    externalSec.setText(key.getSecondaryKeyWord());
                }
                if (key.getDefaultValue() != null) {
                    externalDef.setText(key.getDefaultValue());
                }
            }
            if (key.getJsonAttribute().equals("systemStatus")) {
                if (key.getKeyWord() != null) {
                    sysKey.setText(key.getKeyWord());
                }
                if (key.getSecondaryKeyWord() != null) {
                    sysSec.setText(key.getSecondaryKeyWord());
                }
                if (key.getDefaultValue() != null) {
                    sysDef.setText(key.getDefaultValue());
                }
            }
            if (key.getJsonAttribute().equals("userStatus")) {
                if (key.getKeyWord() != null) {
                    userKey.setText(key.getKeyWord());
                }
                if (key.getSecondaryKeyWord() != null) {
                    userSec.setText(key.getSecondaryKeyWord());
                }
                if (key.getDefaultValue() != null) {
                    userDef.setText(key.getDefaultValue());
                }
            }
            if (key.getJsonAttribute().equals("name")) {
                if (key.getKeyWord() != null) {
                    nameKey.setText(key.getKeyWord());
                }
                if (key.getSecondaryKeyWord() != null) {
                    nameSec.setText(key.getSecondaryKeyWord());
                }
                if (key.getDefaultValue() != null) {
                    nameDef.setText(key.getDefaultValue());
                }
            }
            if (key.getJsonAttribute().equals("priority")) {
                if (key.getKeyWord() != null) {
                    priorityKey.setText(key.getKeyWord());
                }
                if (key.getSecondaryKeyWord() != null) {
                    prioritySec.setText(key.getSecondaryKeyWord());
                }
                if (key.getDefaultValue() != null) {
                    priorityDef.setText(key.getDefaultValue());
                }
            }
            if (key.getJsonAttribute().equals("status")) {
                if (key.getKeyWord() != null) {
                    statusKey.setText(key.getKeyWord());
                }
                if (key.getSecondaryKeyWord() != null) {
                    statusSec.setText(key.getSecondaryKeyWord());
                }
                if (key.getDefaultValue() != null) {
                    statusDef.setText(key.getDefaultValue());
                }
            }
            if (key.getJsonAttribute().equals("latestFinishDate")) {
                if (key.getKeyWord() != null) {
                    finishKey.setText(key.getKeyWord());
                }
                if (key.getSecondaryKeyWord() != null) {
                    finishSec.setText(key.getSecondaryKeyWord());
                }
                if (key.getDefaultValue() != null) {
                    finishDef.setText(key.getDefaultValue());
                }
            }
            if (key.getJsonAttribute().equals("earliestStartDate")) {
                if (key.getKeyWord() != null) {
                    earlyStartKey.setText(key.getKeyWord());
                }
                if (key.getSecondaryKeyWord() != null) {
                    earlyStartSec.setText(key.getSecondaryKeyWord());
                }
                if (key.getDefaultValue() != null) {
                    earlyStartDef.setText(key.getDefaultValue());
                }
            }
            if (key.getJsonAttribute().equals("latestStartDate")) {
                if (key.getKeyWord() != null) {
                    lateStartKey.setText(key.getKeyWord());
                }
                if (key.getSecondaryKeyWord() != null) {
                    lateStartSec.setText(key.getSecondaryKeyWord());
                }
                if (key.getDefaultValue() != null) {
                    lateStartDef.setText(key.getDefaultValue());
                }
            }
            if (key.getJsonAttribute().equals("estimatedTime")) {
                if (key.getKeyWord() != null) {
                    timeKey.setText(key.getKeyWord());
                }
                if (key.getSecondaryKeyWord() != null) {
                    timeSec.setText(key.getSecondaryKeyWord());
                }
                if (key.getDefaultValue() != null) {
                    timeDef.setText(key.getDefaultValue());
                }
            }
        }
    }

    public void save(List<Key> keys) {
        for (Key key : keys) {
            if (key.getJsonAttribute().equals("type")) {
                if (!typeKey.getText().trim().isEmpty()) {
                    key.setKeyWord(typeKey.getText());
                }
                if (!typeSec.getText().trim().isEmpty()) {
                    key.setSecondaryKeyWord(typeSec.getText());
                }
                if (!typeDef.getText().trim().isEmpty()) {
                    key.setDefaultValue(typeDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("externalWorkOrderId")) {
                if (!externalKey.getText().trim().isEmpty()) {
                    key.setKeyWord(externalKey.getText());
                }
                if (!externalSec.getText().trim().isEmpty()) {
                    key.setSecondaryKeyWord(externalSec.getText());
                }
                if (!externalDef.getText().trim().isEmpty()) {
                    key.setDefaultValue(externalDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("systemStatus")) {
                if (!sysKey.getText().trim().isEmpty()) {
                    key.setKeyWord(sysKey.getText());
                }
                if (!sysSec.getText().trim().isEmpty()) {
                    key.setSecondaryKeyWord(sysSec.getText());
                }
                if (!sysDef.getText().trim().isEmpty()) {
                    key.setDefaultValue(sysDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("userStatus")) {
                if (!userKey.getText().trim().isEmpty()) {
                    key.setKeyWord(userKey.getText());
                }
                if (!userSec.getText().trim().isEmpty()) {
                    key.setSecondaryKeyWord(userSec.getText());
                }
                if (!userDef.getText().trim().isEmpty()) {
                    key.setDefaultValue(userDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("name")) {
                if (!nameKey.getText().trim().isEmpty()) {
                    key.setKeyWord(nameKey.getText());
                }
                if (!nameSec.getText().trim().isEmpty()) {
                    key.setSecondaryKeyWord(nameSec.getText());
                }
                if (!nameDef.getText().trim().isEmpty()) {
                    key.setDefaultValue(nameDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("priority")) {
                if (!priorityKey.getText().trim().isEmpty()) {
                    key.setKeyWord(priorityKey.getText());
                }
                if (!prioritySec.getText().trim().isEmpty()) {
                    key.setSecondaryKeyWord(prioritySec.getText());
                }
                if (!priorityDef.getText().trim().isEmpty()) {
                    key.setDefaultValue(priorityDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("status")) {
                if (!statusKey.getText().trim().isEmpty()) {
                    key.setKeyWord(statusKey.getText());
                }
                if (!statusSec.getText().trim().isEmpty()) {
                    key.setSecondaryKeyWord(statusSec.getText());
                }
                if (!statusDef.getText().trim().isEmpty()) {
                    key.setDefaultValue(statusDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("latestFinishDate")) {
                if (!finishKey.getText().trim().isEmpty()) {
                    key.setKeyWord(finishKey.getText());
                }
                if (!finishSec.getText().trim().isEmpty()) {
                    key.setSecondaryKeyWord(finishSec.getText());
                }
                if (!finishDef.getText().trim().isEmpty()) {
                    key.setDefaultValue(finishDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("earliestStartDate")) {
                if (!earlyStartKey.getText().trim().isEmpty()) {
                    key.setKeyWord(earlyStartKey.getText());
                }
                if (!earlyStartSec.getText().trim().isEmpty()) {
                    key.setSecondaryKeyWord(earlyStartSec.getText());
                }
                if (!earlyStartDef.getText().trim().isEmpty()) {
                    key.setDefaultValue(earlyStartDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("latestStartDate")) {
                if (!lateStartKey.getText().trim().isEmpty()) {
                    key.setKeyWord(lateStartKey.getText());
                }
                if (!lateStartSec.getText().trim().isEmpty()) {
                    key.setSecondaryKeyWord(lateStartSec.getText());
                }
                if (!lateStartDef.getText().trim().isEmpty()) {
                    key.setDefaultValue(lateStartDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("estimatedTime")) {
                if (!timeKey.getText().trim().isEmpty()) {
                    key.setKeyWord(timeKey.getText());
                }
                if (!timeSec.getText().trim().isEmpty()) {
                    key.setSecondaryKeyWord(timeSec.getText());
                }
                if (!timeDef.getText().trim().isEmpty()) {
                    key.setDefaultValue(timeDef.getText());
                }
            }
        }
    }
    
    /////////////////////////////////

    @FXML
    private void clickHome(ActionEvent event) {
        homePane.toFront();
    }


    @FXML
    private void clickLogs(ActionEvent event) {
        logPane.toFront();
    }

    @FXML
    private void clickSetting(ActionEvent event) {
        settingPane.toFront();
    }
    
}
