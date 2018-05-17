/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BLL;

import examproject2.BE.Activity;
import examproject2.BE.Config;
import examproject2.BE.Key;
import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Yindo
 */
public interface IBLLManager {

    public void convert(String text, String path, Config con) throws IOException;

    public List<Config> getConfigs();

    public List<Key> getKeys(Config selectedConfig);

    public void saveConfig(Config config, List<Key> keys);

    public void updateConfig(List<Key> keys);

    public void log(Activity log);

    public ObservableList getActivity();

}
