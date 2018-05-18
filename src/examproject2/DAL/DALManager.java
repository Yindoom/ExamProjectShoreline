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
import org.json.JSONArray;

/**
 *
 * @author Yindo
 */
public class DALManager implements IDALFacade {
    
    DatabaseDAO db = new DatabaseDAO();
    FileDAO file = new FileDAO();

    @Override
    public List<Key> getConfig(Config config) {
        return db.getKeyWords(config);
    }
 
    @Override
    public Iterator<Row> getIterator(String filepath) throws IOException {
        return file.getIterator(filepath);
    }
    
    @Override
    public void write(JSONArray jsonFiles, String path, String name) throws IOException {
        file.write(jsonFiles, path, name);
    }

    @Override
    public List<Config> getAllConfigs() {
        return db.getConfigs();
    }

    @Override
    public List<Key> getKeys(Config selectedConfig) {
        return db.getKeyWords(selectedConfig);
    }

    @Override
    public void saveConfig(Config config) {
        db.saveConfig(config);
    }


    @Override
    public void saveKey(Key key) {
        db.saveKey(key);
    }

    @Override
    public void updateKey(Key key) {
        db.updateKey(key);
    }

    @Override
    public void log(Activity log) {
        db.log(log);
    }

    @Override
    public ObservableList getActivity() {
        return db.getActivities();
    }
    
}
