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
import javafx.scene.Node;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    /////////////////////////////////////
    @FXML
    private ScrollPane logPane;
    @FXML
    private ScrollPane settingPane;
    @FXML
    private ScrollPane setPane;
    @FXML
    private VBox logAnchor;
    @FXML
    private VBox type;
    @FXML
    private VBox externalWorkOrderId;
    @FXML
    private VBox systemStatus;
    @FXML
    private VBox userStatus;
    @FXML
    private VBox name;
    @FXML
    private VBox priority;
    @FXML
    private VBox status;
    @FXML
    private VBox latestFinishDate;
    @FXML
    private VBox earliestStartDate;
    @FXML
    private VBox latestStartDate;
    @FXML
    private VBox estimatedTime;

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

        for (Conversion con : tbvConversions.getItems()) {
            Activity log = new Activity();
            log.setSubject(con.getFileName());
            log.setName(currentUser);
            log.setType("Convert");
            model.saveActivity(log);
            lstActivity.getItems().setAll(model.getActivity());
        }

        model.convert(tbvConversions.getItems(), cbmSettings.getSelectionModel().getSelectedItem());

    }

    private void setConfigs() {
        cbmSettings.getItems().setAll(model.getConfigs());
        cbmSettings.getSelectionModel().selectFirst();
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
    private void clickStop(ActionEvent event) throws InterruptedException {
        for (Conversion con : tbvConversions.getItems()) {
            con.getTask().wait();
        }
    }

    //////////////////////////
    @FXML
    private void btnEdit(ActionEvent event) throws IOException {

        ObservableList<Node> logNodes = logAnchor.getChildren();

        for (Node logNode : logNodes) {

            if (logNode.getClass() == HBox.class) {
                HBox hbox = (HBox) logNode;
                ObservableList<Node> hboxChildren = hbox.getChildren();

                for (Node node : hboxChildren) {
                    if (node.getClass() == TextField.class) {
                        TextField text = (TextField) node;
                        text.clear();
                    }

                    if (node.getClass() == VBox.class) {
                        VBox vbox = (VBox) node;
                        ObservableList<Node> vboxChildren = vbox.getChildren();

                        for (Node node2 : vboxChildren) {
                            if (node2.getClass() == TextField.class) {
                                TextField text = (TextField) node2;
                                text.clear();
                            }
                        }
                    }
                }

            }
        }

        Config selectedConfig
                = lstConfiguration.getSelectionModel().getSelectedItem();
        setConfig(selectedConfig);
        setUser(currentUser, "Edit");
        setPane.toFront();

    }

    @FXML
    private void btnAdd(ActionEvent event) throws IOException {
        edit = false;
        ObservableList<Node> logNodes = logAnchor.getChildren();

        for (Node logNode : logNodes) {

            if (logNode.getClass() == HBox.class) {
                HBox hbox = (HBox) logNode;
                ObservableList<Node> hboxChildren = hbox.getChildren();

                for (Node node : hboxChildren) {
                    if (node.getClass() == TextField.class) {
                        TextField text = (TextField) node;
                        text.clear();
                    }

                    if (node.getClass() == VBox.class) {
                        VBox vbox = (VBox) node;
                        ObservableList<Node> vboxChildren = vbox.getChildren();

                        for (Node node2 : vboxChildren) {
                            if (node2.getClass() == TextField.class) {
                                TextField text = (TextField) node2;
                                text.clear();
                            }
                        }
                    }
                }

            }
        }

        setUser(currentUser, "Add");
        setPane.toFront();

    }
    //////////////////////////// 

    public void setUser(String currentUser, String activity) {
        user = currentUser;
        this.activity = activity;
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

                config.setName(configName.getText());
                model.saveConfig(config, keys);

                Activity log = new Activity();
                log.setSubject(configName.getText());
                log.setName(user);
                log.setType(activity);

                model.saveActivity(log);
                lstActivity.getItems().setAll(model.getActivity());
                lstConfiguration.getItems().clear();
                lstConfiguration.getItems().addAll(model.getConfigs());
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
            lstActivity.getItems().setAll(model.getActivity());

        }
        lstConfiguration.getItems().clear();
        lstConfiguration.getItems().addAll(model.getConfigs());

        settingPane.toFront();

    }

    public void setConfig(Config selectedConfig) {
        configName.setText(selectedConfig.getName());
        this.id = selectedConfig.getId();
        edit = true;
        List<Key> keys = model.getKeys(selectedConfig);
        ObservableList<Node> logNodes = logAnchor.getChildren();

        for (Node logNode : logNodes) {

            if (logNode.getClass() == HBox.class) {
                HBox hbox = (HBox) logNode;
                ObservableList<Node> hboxChildren = hbox.getChildren();

                for (Node node : hboxChildren) {

                    if (node.getClass() == VBox.class) {
                        VBox vbox = (VBox) node;

                        for (Key key : keys) {

                            if (vbox.getId().equals(key.getJsonAttribute())) {

                                ObservableList<Node> vboxChildren = vbox.getChildren();
                                for (Node node1 : vboxChildren) {
                                    if (node1.getClass() == TextField.class) {
                                        TextField text = (TextField) node1;

                                        if (text.getId().contains("Key")) {
                                            text.setText(key.getKeyWord());
                                        }
                                        if (text.getId().contains("Sec")) {
                                            text.setText(key.getSecondaryKeyWord());
                                        }
                                        if (text.getId().contains("Def")) {
                                            text.setText(key.getDefaultValue());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void save(List<Key> keys) {
        ObservableList<Node> logNodes = logAnchor.getChildren();

        for (Node logNode : logNodes) {

            if (logNode.getClass() == HBox.class) {
                HBox hbox = (HBox) logNode;
                ObservableList<Node> hboxChildren = hbox.getChildren();

                for (Node node : hboxChildren) {

                    if (node.getClass() == VBox.class) {
                        VBox vbox = (VBox) node;

                        for (Key key : keys) {

                            if (vbox.getId().equals(key.getJsonAttribute())) {

                                ObservableList<Node> vboxChildren = vbox.getChildren();
                                for (Node node1 : vboxChildren) {
                                    if (node1.getClass() == TextField.class) {
                                        TextField text = (TextField) node1;

                                        if (text.getId().contains("Key") && !text.getText().trim().isEmpty()) {
                                            key.setKeyWord(text.getText());
                                        }
                                        if (text.getId().contains("Sec") && !text.getText().trim().isEmpty()) {
                                            key.setSecondaryKeyWord(text.getText());
                                        }
                                        if (text.getId().contains("Def") && !text.getText().trim().isEmpty()) {
                                            key.setDefaultValue(text.getText());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /////////////////////////////////
    @FXML
    private void clickHome(ActionEvent event
    ) {
        homePane.toFront();
    }

    @FXML
    private void clickLogs(ActionEvent event
    ) {
        logPane.toFront();
    }

    @FXML
    private void clickSetting(ActionEvent event
    ) {
        settingPane.toFront();
    }

    @FXML
    private void clickLogOff(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));

        Parent root = fxLoader.load();
        MainViewController asv = fxLoader.getController();

        Stage oldStage = (Stage) configName.getScene().getWindow();
        oldStage.close();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
