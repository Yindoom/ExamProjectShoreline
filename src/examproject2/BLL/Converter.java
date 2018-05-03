/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BLL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;

/**
 *
 * @author Yindo
 */
public class Converter {
        
    public enum filetype {
        xlsx,
        xml
    }
    
        public void convert(String text, filetype xlsx) throws FileNotFoundException, IOException {

        List<String> headers = new ArrayList();
        List<String> keywords = new ArrayList();
        List<Integer> colIndex = new ArrayList();

        keywords.add("Order Type");
        keywords.add("System Status");
        keywords.add("Opr. short text");
        keywords.add("priority");
        keywords.add("priority1");
        
        
        List<JSONObject> myJSONObjects = new ArrayList();
        File excel = new File(text);
        FileInputStream fis = new FileInputStream(excel);
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet = book.getSheetAt(0);

        Iterator<Row> itr = sheet.iterator();

        while (itr.hasNext()) {
            JSONObject obj = new JSONObject();
            Row row = itr.next();

            
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();

                if (row.getRowNum() == 0) {

                    String cellValue = cell.getStringCellValue();

                    for (String key : keywords) {
                        int n = 0;
                        if (cellValue.toLowerCase().equals(key.toLowerCase())) {
                            for (String header : headers) {
                                if (header.toLowerCase().equals(cellValue.toLowerCase())) {
                                    n++;
                                }
                            }
                            if (n != 0) {
                                cellValue = cellValue + n;
                            }
                        }
                        if (cellValue.toLowerCase().equals(key.toLowerCase())) {
                            colIndex.add(cell.getColumnIndex());
                            headers.add(cellValue);
                        }
                    }
                }
                
                
                //  System.out.println(colIndex);
                if (row.getRowNum() != 0) {
                    for (int index : colIndex) {
                        if (index == cell.getColumnIndex()) {
                           
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    System.out.println(cell.getStringCellValue());
                                    obj.put(cell.getColumnIndex(), cell.getStringCellValue());
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    System.out.println(cell.getNumericCellValue());
                                    obj.put(cell.getColumnIndex(), cell.getNumericCellValue());
                                    break;
                                default:
                        
                            }
                        }
                    }
                }
              
            }
            if(row.getRowNum() != 0)
        myJSONObjects.add(obj);    
        }
        for (int i = 0; i < myJSONObjects.size(); i++) {
            try (FileWriter file = new FileWriter("C:\\Users\\Yindo\\Desktop\\obj\\test"+ i+".json")) {
            file.write(myJSONObjects.get(i).toJSONString());
            file.flush();}
            
        }

    }
    
}