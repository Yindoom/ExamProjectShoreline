/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.DAL;

import examproject2.BE.Activity;
import examproject2.BE.Config;
import examproject2.BE.Key;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONArray;

/**
 *
 * @author Yindo
 */
public interface IDALFacade {

    public List<Key> getConfig(Config config);

    public Sheet getIterator(String filepath) throws IOException;

    public void write(JSONArray jsonFiles, String path, String name) throws IOException;

    public List<Config> getAllConfigs();

    public List<Key> getKeys(Config selectedConfig);

    public void saveConfig(Config config);

    public void saveKey(Key key);

    public void updateKey(Key key);

    public void log(Activity log);

    public ObservableList getActivity();

    public Sheet getCSV(String filepath);

}
