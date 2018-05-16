/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Config;
import examproject2.BE.Key;
import examproject2.BLL.BLLManager;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.TextField;

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

    void convert(String file, String path, Config config) throws IOException {
        bll.convert(file, path, config);
    }

    List<Config> getConfigs() {
        return bll.getConfigs();
    }

    List<Key> getKeys(Config selectedConfig) {
        return bll.getKeys(selectedConfig);
    }

    void saveActivity(String logActivity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void saveConfig(Config config, List<Key> keys) {
        bll.saveConfig(config, keys);
    }

    private static class ModelHolder {

        private static final Model INSTANCE = new Model();
    }
}
