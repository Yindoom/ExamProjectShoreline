/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BLL;

import examproject2.BE.Config;
import examproject2.BE.Key;
import static examproject2.BLL.Converter.filetype.xlsx;
import examproject2.DAL.DALManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Bastian
 */
public class BLLManager {

    Converter convert = new Converter();
    DALManager dal = new DALManager();

    public void convert(String text, String path, Config con) throws IOException {

        List<Config> config = new ArrayList(dal.getConfig(con));
        convert.convert(dal.getIterator(text), xlsx, config);
        dal.write(convert.myJSONObjects, path, getName(text));
    }

    private String getName(String text) {
      String name = 
      FilenameUtils.getBaseName(text);
      return name;
    }

    public List<Config> getConfigs() {
        return dal.getAllConfigs();
    }

    public List<Key> getKeys(Config selectedConfig) {
        return dal.getKeys(selectedConfig);
    }

    public void saveConfig(Config config, List<Key> keys) {
        dal.saveConfig(config);
        for (Key key : keys) {
            key.setId(config.getId());
            dal.saveKey(key);
        }
    }
}
