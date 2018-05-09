/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.DAL;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;

/**
 *
 * @author Yindo
 */
public class FileDAO {

    public Iterator<Row> getIterator(String text) throws FileNotFoundException, IOException {

        File excel = new File(text);
        FileInputStream fis = new FileInputStream(excel);
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet = book.getSheetAt(0);

        return sheet.iterator();
    }

<<<<<<< HEAD
    public void write(JSONArray jsonFiles, String name) throws IOException {

        try (FileWriter file = new FileWriter("C:\\Users\\ZeXVex\\Desktop\\Shoreline\\Test\\"+name+".json")) {
=======
    public void write(JSONArray jsonFiles, String path) throws IOException {
        
        try (FileWriter file = new FileWriter(path + "\\test.json")) {
>>>>>>> fc4767f8b0ed663ec346d3caf5650d9cb254cff5
            file.write(jsonFiles.toString(4));
            file.flush();
        }
    }

}
