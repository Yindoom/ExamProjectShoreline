/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BLL;

import examproject2.BE.Activity;
import examproject2.BE.Config;
import examproject2.BE.Key;
import static examproject2.BLL.Converter.filetype.xlsx;
import examproject2.DAL.DALManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Bastian
 */
public class BLLManager implements IBLLManager {

    private static BLLManager INSTANCE;
    Converter convert = new Converter();
    DALManager dal = new DALManager();
    
    
        public synchronized static BLLManager getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new BLLManager();
        }
        return INSTANCE;
    }
    

    @Override
    public void convert(String text, String path, Config con) throws IOException {

        List<Config> config = new ArrayList(dal.getConfig(con));
        convert.convert(dal.getIterator(text), xlsx, config);
        dal.write(convert.myJSONObjects, path, getName(text));
    }

    private String getName(String text) {
        String name
                = FilenameUtils.getBaseName(text);
        return name;
    }

    @Override
    public List<Config> getConfigs() {
        return dal.getAllConfigs();
    }

    @Override
    public List<Key> getKeys(Config selectedConfig) {
        return dal.getKeys(selectedConfig);
    }

    @Override
    public void saveConfig(Config config, List<Key> keys) {
        boolean proceed = true;
        try {
            dal.saveConfig(config);
        } catch (NullPointerException e) {
            proceed = false;
        }
        
        if (proceed) {
            for (Key key : keys) {
                key.setId(config.getId());
            }
            for (Key key : keys) {
                dal.saveKey(key);
            }
        }
    }

    @Override
    public void updateConfig(List<Key> keys) {
        for (Key key : keys) {
            dal.updateKey(key);
        }
    }

    @Override
    public void log(Activity log) {
        dal.log(log);
    }

    @Override
    public ObservableList getActivity() {
        return dal.getActivity();
    }
}
