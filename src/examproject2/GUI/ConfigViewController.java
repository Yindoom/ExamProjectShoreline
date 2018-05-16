/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Config;
import examproject2.BE.Key;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yindo
 */
public class ConfigViewController implements Initializable {

    Model model = Model.getInstance();
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setUser(String currentUser, String activity) {
        ObservableList<String> files = FXCollections.observableArrayList();
        files.add("xlsx");
        files.add("csv");
        user = currentUser;
        this.activity = activity;
        fileType.setItems(files);
    }

    @FXML
    private void clickSave(ActionEvent event) {
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
            for (Config config : model.getConfigs()) {
                if (config.getName().equals(configName.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Config already exists");
                    alert.setContentText("This config name already exists, please choose a different name");
                    alert.showAndWait();
                    break;
                }
            }
            Config config = new Config();
            config.setFileType(fileType.getSelectionModel().getSelectedItem());
            config.setName(configName.getText());
            model.saveConfig(config, keys);
        } else {
            for (Key key : keys) {
                key.setId(id);
            }
            model.updateConfig(keys);

            String logActivity = user + activity + configName.getText();
            model.saveActivity(logActivity);
        }

    }

    public void setConfig(Config selectedConfig) {
        configName.setText(selectedConfig.getName());
        this.id = selectedConfig.getId();
        edit = true;
        List<Key> keys = model.getKeys(selectedConfig);
        for (Key key : keys) {
            if (key.getJsonAttribute().equals("type")) {
                typeKey.setText(key.getKeyWord());
                typeSec.setText(key.getSecondaryKeyWord());
                typeDef.setText(key.getDefaultValue());
            }
            if (key.getJsonAttribute().equals("externalWorkOrderId")) {
                externalKey.setText(key.getKeyWord());
                externalSec.setText(key.getSecondaryKeyWord());
                externalDef.setText(key.getDefaultValue());
            }
            if (key.getJsonAttribute().equals("systemStatus")) {
                sysKey.setText(key.getKeyWord());
                sysSec.setText(key.getSecondaryKeyWord());
                sysDef.setText(key.getDefaultValue());
            }
            if (key.getJsonAttribute().equals("userStatus")) {
                userKey.setText(key.getKeyWord());
                userSec.setText(key.getSecondaryKeyWord());
                userDef.setText(key.getDefaultValue());
            }
            if (key.getJsonAttribute().equals("name")) {
                nameKey.setText(key.getKeyWord());
                nameSec.setText(key.getSecondaryKeyWord());
                nameDef.setText(key.getDefaultValue());
            }
            if (key.getJsonAttribute().equals("priority")) {
                priorityKey.setText(key.getKeyWord());
                prioritySec.setText(key.getSecondaryKeyWord());
                priorityDef.setText(key.getDefaultValue());
            }
            if (key.getJsonAttribute().equals("status")) {
                statusKey.setText(key.getKeyWord());
                statusSec.setText(key.getSecondaryKeyWord());
                statusDef.setText(key.getDefaultValue());
            }
            if (key.getJsonAttribute().equals("latestFinishDate")) {
                finishKey.setText(key.getKeyWord());
                finishSec.setText(key.getSecondaryKeyWord());
                finishDef.setText(key.getDefaultValue());
            }
            if (key.getJsonAttribute().equals("earliestStartDate")) {
                earlyStartKey.setText(key.getKeyWord());
                earlyStartSec.setText(key.getSecondaryKeyWord());
                earlyStartDef.setText(key.getDefaultValue());
            }
            if (key.getJsonAttribute().equals("latestStartDate")) {
                lateStartKey.setText(key.getKeyWord());
                lateStartSec.setText(key.getSecondaryKeyWord());
                lateStartDef.setText(key.getDefaultValue());
            }
            if (key.getJsonAttribute().equals("estimatedTime")) {
                timeKey.setText(key.getKeyWord());
                timeSec.setText(key.getSecondaryKeyWord());
                timeDef.setText(key.getDefaultValue());
            }
        }
    }

    private void save(List<Key> keys) {
        for (Key key : keys) {
            if (key.getJsonAttribute().equals("type")) {
                if (!typeKey.getText().isEmpty()) {
                    key.setKeyWord(typeKey.getText());
                }
                if (!typeSec.getText().isEmpty()) {
                    key.setSecondaryKeyWord(typeSec.getText());
                }
                if (!typeDef.getText().isEmpty()) {
                    key.setDefaultValue(typeDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("externalWorkOrderId")) {
                if (!externalKey.getText().isEmpty()) {
                    key.setKeyWord(externalKey.getText());
                }
                if (!externalSec.getText().isEmpty()) {
                    key.setSecondaryKeyWord(externalSec.getText());
                }
                if (!externalDef.getText().isEmpty()) {
                    key.setDefaultValue(externalDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("systemStatus")) {
                if (!sysKey.getText().isEmpty()) {
                    key.setKeyWord(sysKey.getText());
                }
                if (!sysSec.getText().isEmpty()) {
                    key.setSecondaryKeyWord(sysSec.getText());
                }
                if (!sysDef.getText().isEmpty()) {
                    key.setDefaultValue(sysDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("userStatus")) {
                if (!userKey.getText().isEmpty()) {
                    key.setKeyWord(userKey.getText());
                }
                if (!userSec.getText().isEmpty()) {
                    key.setSecondaryKeyWord(userSec.getText());
                }
                if (!userDef.getText().isEmpty()) {
                    key.setDefaultValue(userDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("name")) {
                if (!nameKey.getText().isEmpty()) {
                    key.setKeyWord(nameKey.getText());
                }
                if (!nameSec.getText().isEmpty()) {
                    key.setSecondaryKeyWord(nameSec.getText());
                }
                if (!nameDef.getText().isEmpty()) {
                    key.setDefaultValue(nameDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("priority")) {
                if (!priorityKey.getText().isEmpty()) {
                    key.setKeyWord(priorityKey.getText());
                }
                if (!prioritySec.getText().isEmpty()) {
                    key.setSecondaryKeyWord(prioritySec.getText());
                }
                if (!priorityDef.getText().isEmpty()) {
                    key.setDefaultValue(priorityDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("status")) {
                if (!statusKey.getText().isEmpty()) {
                    key.setKeyWord(statusKey.getText());
                }
                if (!statusSec.getText().isEmpty()) {
                    key.setSecondaryKeyWord(statusSec.getText());
                }
                if (!statusDef.getText().isEmpty()) {
                    key.setDefaultValue(statusDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("latestFinishDate")) {
                if (!finishKey.getText().isEmpty()) {
                    key.setKeyWord(finishKey.getText());
                }
                if (!finishSec.getText().isEmpty()) {
                    key.setSecondaryKeyWord(finishSec.getText());
                }
                if (!finishDef.getText().isEmpty()) {
                    key.setDefaultValue(finishDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("earliestStartDate")) {
                if (!earlyStartKey.getText().isEmpty()) {
                    key.setKeyWord(earlyStartKey.getText());
                }
                if (!earlyStartSec.getText().isEmpty()) {
                    key.setSecondaryKeyWord(earlyStartSec.getText());
                }
                if (!earlyStartDef.getText().isEmpty()) {
                    key.setDefaultValue(earlyStartDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("latestStartDate")) {
                if (!lateStartKey.getText().isEmpty()) {
                    key.setKeyWord(lateStartKey.getText());
                }
                if (!lateStartSec.getText().isEmpty()) {
                    key.setSecondaryKeyWord(lateStartSec.getText());
                }
                if (!lateStartDef.getText().isEmpty()) {
                    key.setDefaultValue(lateStartDef.getText());
                }
            }
            if (key.getJsonAttribute().equals("estimatedTime")) {
                if (!timeKey.getText().isEmpty()) {
                    key.setKeyWord(timeKey.getText());
                }
                if (!timeSec.getText().isEmpty()) {
                    key.setSecondaryKeyWord(timeSec.getText());
                }
                if (!timeDef.getText().isEmpty()) {
                    key.setDefaultValue(timeDef.getText());
                }
            }
        }
    }
}
