/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Config;
import examproject2.BE.Key;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yindo
 */
public class ConfigViewController implements Initializable {
    
    Model model = Model.getInstance();
    String activity;
    String user;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setUser(String currentUser, String activity) {
        user = currentUser;
        this.activity = activity;
    }

    public void setConfig(Config selectedConfig) {
        List<Key> keys = model.getKeys(selectedConfig);
        for (Key key : keys) {
            if(key.getJsonAttribute().equals("type")) {
                typeKey.setText(key.getKeyWord());
                typeSec.setText(key.getSecondaryKeyWord());
                typeDef.setText(key.getDefaultValue());
            }
        }
    }

    @FXML
    private void clickSave(ActionEvent event) {
        String logActivity = user + activity + configName.getText();
        model.saveActivity(logActivity);
    }
    
}
