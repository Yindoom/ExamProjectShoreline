/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BLL;

import examproject2.BE.Config;
import static examproject2.BLL.Converter.filetype.xlsx;
import static examproject2.BLL.Converter.filetype.xml;
import examproject2.DAL.DALManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yindo
 */
public class BLLManager {
    
    Converter convert = new Converter();
    DALManager dal = new DALManager();
    
    public void convert(String file, String path) throws IOException {
       
        List<Config> config = new ArrayList(dal.getConfig());
        convert.convert(dal.getIterator(file), xlsx, config);
        dal.write(convert.myJSONObjects, path);
    }
}
