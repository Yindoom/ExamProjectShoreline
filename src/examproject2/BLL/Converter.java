/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BLL;

import examproject2.BE.Config;
import examproject2.BE.Header;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    public void convert(String text, filetype xlsx, List config) throws FileNotFoundException, IOException {

        List<Config> keys = config;
        List<Header> headers = new ArrayList();

        List<JSONObject> myJSONObjects = new ArrayList();
        File excel = new File(text);
        FileInputStream fis = new FileInputStream(excel);
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet = book.getSheetAt(0);

        Iterator<Row> itr = sheet.iterator();

        while (itr.hasNext()) {
            JSONObject obj = new JSONObject();
            JSONObject planning = new JSONObject();
            Row row = itr.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();

                if (row.getRowNum() == 0) {
                    Header newheader = new Header();
                    newheader.setIndex(cell.getColumnIndex());
                    newheader.setValue(cell.getStringCellValue());
                    int n = 1;
                    for (Header header : headers) {
                        if (newheader.getValue().equals(header.getValue())) {
                            n++;
                            header.setValue(header.getValue() + n);
                        }
                    }
                    headers.add(newheader);
                    if (!cellIterator.hasNext()) {
                        for (Header header : headers) {
                            for (Config key : keys) {
                                if (header.getValue().contains(key.getKeyWord())) {
                                    key.setColumnIndex(header.getIndex());
                                }
                            }
                        }
                    }
                }

                if (row.getRowNum() != 0) {
                    for (Config index : keys) {
                        if (index.getColumnIndex() == cell.getColumnIndex()) {
                            if (index.getJsonAttribute().toLowerCase().contains("date")) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                planning.put(index.getJsonAttribute(), sdf.format(cell.getDateCellValue()));
                            } else if (index.getJsonAttribute().toLowerCase().contains("time")) {
                                planning.put(index.getJsonAttribute(), cell.getNumericCellValue());
                            } else {
                                switch (cell.getCellTypeEnum()) {
                                    case STRING:
                                        obj.put(index.getJsonAttribute(), cell.getStringCellValue());
                                        break;
                                    case NUMERIC:
                                        obj.put(index.getJsonAttribute(), cell.getNumericCellValue());
                                        break;
                                    default:

                                }
                            }
                        }
                    }
                }
            }
            if (row.getRowNum() != 0) {
                obj.put("createdOn", LocalDate.now());
                obj.put("Planning", planning);
                myJSONObjects.add(obj);

            }
        }
        for (int i = 0;
                i < myJSONObjects.size();
                i++) {
            try (FileWriter file = new FileWriter("C:\\Users\\Yindo\\Desktop\\obj\\test" + i + ".json")) {
                file.write(myJSONObjects.get(i).toJSONString());
                file.flush();
            }

        }

    }

}
