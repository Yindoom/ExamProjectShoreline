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

        List<String> headers = new ArrayList();
        List<String> keywords = new ArrayList();
        List<String> cells = new ArrayList();

        List<Integer> colIndex = new ArrayList();

        keywords.add("Order Type");
        keywords.add("System Status");
        keywords.add("Opr. short text");
        keywords.add("priority");
        keywords.add("priority1");

        JSONObject obj = new JSONObject();
        File excel = new File(text);
        FileInputStream fis = new FileInputStream(excel);
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet = book.getSheetAt(0);

        Iterator<Row> itr = sheet.iterator();

        while (itr.hasNext()) {
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
                            if(n != 0)
                            cellValue = cellValue + n;
                        }
                        if (cellValue.toLowerCase().equals(key.toLowerCase())) {
                            colIndex.add(cell.getColumnIndex());
                            headers.add(cellValue);
                        }
                    }
                }
            
            System.out.println(colIndex);
                  
         if (row.getRowNum() != 0) {
             for (int index : colIndex) 
                 if (index == cell.getColumnIndex())
                      switch (cell.getCellType()) { 
                          case Cell.CELL_TYPE_STRING:
                              obj.put(cell.getColumnIndex(),cell.getStringCellValue());
                              break;
                          case Cell.CELL_TYPE_NUMERIC:
                              obj.put(cell.getColumnIndex(), cell.getNumericCellValue());
                              break;
                          default:
                      }
                     }
 
       
         
         
            


        }
    }
             try (FileWriter file = new FileWriter("C:\\Users\\Emil Pc\\Desktop\\test.json")) {
                file.write(obj.toJSONString());
                file.flush();
             }

}}
   


