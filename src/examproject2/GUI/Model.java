/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Config;
import examproject2.BLL.BLLManager;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Yindo
 */
public class Model {
    
    private Model() {
    }
    
    BLLManager bll = new BLLManager();
    
    public static Model getInstance() {
        return ModelHolder.INSTANCE;
    }

<<<<<<< HEAD
    public void convert(String text) throws IOException {
        bll.convert(text);
=======
    void convert(String text, Config config) throws IOException {
        bll.convert(text, config);
    }

    List<Config> getConfigs() {
        return bll.getConfigs();
    void convert(String file, String path) throws IOException {
        bll.convert(file, path);
>>>>>>> fc4767f8b0ed663ec346d3caf5650d9cb254cff5
    }
    
    private static class ModelHolder {

        private static final Model INSTANCE = new Model();
    }
}
