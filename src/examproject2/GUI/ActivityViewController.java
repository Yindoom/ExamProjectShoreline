/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Activity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Yindo
 */
public class ActivityViewController implements Initializable {
    
    Model model = Model.getInstance();

    @FXML
    private TableView<Activity> lstActivity;
    @FXML
    private TableColumn<Activity, String> colUse;
    @FXML
    private TableColumn<Activity, String> colAct;
    @FXML
    private TableColumn<Activity, String> colSub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colUse.setCellValueFactory((cellFeatures) -> cellFeatures.getValue().nameProperty());
        colAct.setCellValueFactory((cellFeatures) -> cellFeatures.getValue().typeProperty());
        colSub.setCellValueFactory((cellFeatures) -> cellFeatures.getValue().subjectProperty());
        lstActivity.setItems(model.getActivity()); 
    }
    
}
