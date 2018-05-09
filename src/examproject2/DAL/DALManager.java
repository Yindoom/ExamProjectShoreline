/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.DAL;

import examproject2.BE.Config;
import examproject2.BE.Key;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.xml.sax.SAXException;

/**
 *
 * @author Yindo
 */
public class DALManager {
    
    DatabaseDAO db = new DatabaseDAO();
    FileDAO file = new FileDAO();

    public List<Key> getConfig(Config config) {
        return db.getKeyWords(config);
    }
    
    public Iterator<Row> getIterator(String filepath) throws IOException {
        return file.getIterator(filepath);
    }
    
    public void write(JSONArray jsonFiles, String path, String name) throws IOException {
        file.write(jsonFiles, path, name);
    }

    public List<Config> getAllConfigs() {
        return db.getConfigs();
    }
    
}
