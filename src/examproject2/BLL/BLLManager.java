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
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Yindo
 */
public class BLLManager {

    Converter convert = new Converter();
    DALManager dal = new DALManager();

    public void convert(String text, String path, Config con) throws IOException {

<<<<<<< HEAD

    public void convert(String text) throws IOException {

        List<Config> config = new ArrayList(dal.getConfig());
        convert.convert(dal.getIterator(text), xlsx, config);
        dal.write(convert.myJSONObjects, getName(text));
    }

    private String getName(String text) {
        String[] nameArray = text.split(".");
        int i = nameArray.length -1;
        return nameArray[i];

    
    public void convert(String text, Config con) throws IOException {
       
        List<Key> config = new ArrayList(dal.getConfig(con));
        convert.convert(dal.getIterator(text), xlsx, config);
        dal.write(convert.myJSONObjects, path);
    }

    public List<Config> getConfigs() {
        return dal.getAllConfigs();
    }
}
