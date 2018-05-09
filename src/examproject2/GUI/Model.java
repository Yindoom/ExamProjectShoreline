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

    void convert(String text, Config config) throws IOException {
        bll.convert(text, config);
    }

    List<Config> getConfigs() {
        return bll.getConfigs();
    void convert(String file, String path) throws IOException {
        bll.convert(file, path);
    }
    
    private static class ModelHolder {

        private static final Model INSTANCE = new Model();
    }
}
