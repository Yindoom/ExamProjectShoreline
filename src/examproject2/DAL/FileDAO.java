/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.DAL;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;

/**
 *
 * @author Yindo
 */
public class FileDAO {

    public Sheet getExcel(String text) throws FileNotFoundException, IOException {

        File excel = new File(text);
        FileInputStream fis = new FileInputStream(excel);
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet = book.getSheetAt(0);

        return sheet;
    }

    public void write(JSONArray jsonFiles, String path, String name) throws IOException {

        try (FileWriter file = new FileWriter(path + "\\" + name + ".json")) {

            file.write(jsonFiles.toString(4));
            file.flush();
        }
    }

    public Sheet getCSV(String text) throws FileNotFoundException, IOException {
        Workbook wb = new HSSFWorkbook();
        CreationHelper helper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        CSVReader reader = new CSVReader(new FileReader(text));
        String[] line;
        int r = 0;
        while ((line = reader.readNext()) != null) {
            Row row = sheet.createRow((short) r++);

            for (int i = 0; i < line.length; i++) {
                row.createCell(i)
                        .setCellValue(helper.createRichTextString(line[i]));
            }
        }
        return sheet;
    }
}
