/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BLL;

import examproject2.BE.Config;
import static examproject2.BLL.Converter.filetype.xlsx;
import examproject2.DAL.DALManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;

/**
 *
 * @author Yindo
 */
public class BLLManager {
    
    Converter convert = new Converter();
    DALManager dal = new DALManager();
    List<Config> config = new ArrayList(dal.getConfig());
    
    public void convert(String text) throws IOException {
        convert.convert(text, xlsx, config);
    }
}
