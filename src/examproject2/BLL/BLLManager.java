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

    public void convert(String text) throws FileNotFoundException, IOException {
        List<String> keywords = new ArrayList();
        
        List<Integer> colIndex = new ArrayList();
        
        keywords.add("Order Type");
        keywords.add("System Status");
        keywords.add("Opr. short text");
        keywords.add("priority");
        
        JSONObject obj = new JSONObject();
        File excel = new File(text);
        FileInputStream fis = new FileInputStream(excel);
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet = book.getSheetAt(0);
        
        Iterator<Row> itr = sheet.iterator();
        
        
        while(itr.hasNext()) {
            Row row = itr.next();
            
            Iterator<Cell> cellIterator = row.cellIterator(); 
            if(row.getRowNum() == 0) {
                
                for (String key : keywords) {
                int n = 0;
                
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    
                            if(cell.getStringCellValue().toLowerCase().equals(key.toLowerCase())) {
                                if(n != 0) 
                                    cell.setCellValue(cell.getStringCellValue()+n);
                                if(cell.getStringCellValue().toLowerCase().contains(key.toLowerCase()))
                                colIndex.add(cell.getColumnIndex());
                                n++;
                            }
                        }
                    }
                    System.out.println(colIndex);
//                    obj.put("test", cell);
//                    switch (cell.getCellType()) {
//                    case Cell.CELL_TYPE_STRING:
//                        System.out.print(cell.getStringCellValue() + "\t");
//                        break;
//                    case Cell.CELL_TYPE_NUMERIC:
//                        System.out.print(cell.getNumericCellValue() + "\t");
//                        break;
//                    case Cell.CELL_TYPE_BOOLEAN:
//                        System.out.print(cell.getBooleanCellValue() + "\t");
//                        break;
//                    default:

                    }
                }
//                try (FileWriter file = new FileWriter("C:\\Users\\Yindo\\Desktop\\test.json")) {
//                   file.write(obj.toJSONString());
//                   file.flush();
                }
                //System.out.println("");
           // }
    //}
    
}
