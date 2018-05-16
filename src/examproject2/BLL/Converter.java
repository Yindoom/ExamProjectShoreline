/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BLL;

import examproject2.BE.Key;
import examproject2.BE.Header;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Yindo
 */
public class Converter {

    List<Header> headers = new ArrayList();
    List<Key> keys;
    JSONArray myJSONObjects = new JSONArray();
    List<Key> secondary;

    public enum filetype {
        xlsx,
        xml
    }

    //@author Bastian and Emil
    
    public void convert(Iterator<Row> itr, filetype xlsx, List config) throws IOException {

        keys = config;

        while (itr.hasNext()) {

            secondary = new ArrayList();

            JSONObject obj = new JSONObject();
            JSONObject planning = new JSONObject();
            Row row = itr.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();

                if (row.getRowNum() == 0) {
                    getHeaders(cell);
                }

                if (!cellIterator.hasNext()) {
                    setIndex();
                }

                if (row.getRowNum() != 0) {
                    addToJson(obj, planning, cell);
                }
            }
            Iterator<Cell> secondIterator = row.cellIterator();

            while (secondIterator.hasNext()) {                                  //Here we go through the row again, using the secondary
                Cell cell = secondIterator.next();                              //list of keys
                secondaryAddToJson(obj, planning, cell);
            }
            if (row.getRowNum() != 0) {                                         //here we add the final objects and data to the JSON
                obj.put("assetSerialNumber", "asset.id");
                obj.put("siteName", "");
                obj.put("createdBy", "SAP");
                obj.put("createdOn", LocalDate.now());      
                obj.put("Planning", planning);
                myJSONObjects.put(obj);
            }

        }

    }

    private void setIndex() {                                                   //This is where we go through all the headers 
        for (Header header : headers) {                                         //and compare them to our keys' primary and 
            for (Key key : keys) {                                              //secondary keywords
                if (key.getKeyWord() != null) {
                    if (header.getValue().toLowerCase().trim().contains(key.getKeyWord().toLowerCase().trim())) {
                        key.setColumnIndex(header.getIndex());
                    }
                }
                if (key.getSecondaryKeyWord() != null) {
                    if (header.getValue().toLowerCase().trim().contains(key.getSecondaryKeyWord().toLowerCase().trim())) {
                        key.setSecondaryIndex(header.getIndex());
                    }
                }
            }
        }
    }

    private void getHeaders(Cell cell) {                                        //Here we go through the entire first row, to find all headers
                                                                                //and add them to a list, so we can compare them to 
        Header newheader = new Header();                                        //our keys
        newheader.setIndex(cell.getColumnIndex());
        newheader.setValue(cell.getStringCellValue());
        int n = 1;
        for (Header header : headers) {
            if (newheader.getValue().equals(header.getValue())) {               //If a header has the same name as another header
                n++;                                                            //We add a number to the name
                newheader.setValue(header.getValue() + n);
            }
        }
        headers.add(newheader);
    }

    private void addToJson(JSONObject obj, JSONObject planning, Cell cell) {    //Here is where we add data to the JsonObjects
        for (Key index : keys) {
            if (index.getKeyWord() == null) {
                secondary.add(index);                                           //If the primary keyword doesn't exist, add the index to
            }                                                                   //the secondary list of keys
            if (index.getColumnIndex() == cell.getColumnIndex()) {
                if (index.getJsonAttribute().toLowerCase().contains("date")) {
                    if (cell.getCellTypeEnum() == STRING) {                     
                        if (cell.getStringCellValue().isEmpty()) {
                            secondary.add(index);
                        }                                                       //If the JsonAttributes contain "date" or "time" we add it to 
                    } else {                                                    //a different object, because of the "planning" object
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        planning.put(index.getJsonAttribute(), sdf.format(cell.getDateCellValue()));
                    }
                } else if (index.getJsonAttribute().toLowerCase().contains("time")) {
                    if (cell.getCellTypeEnum() == STRING) {
                        if (cell.getStringCellValue().isEmpty()) {
                            secondary.add(index);
                        }
                    } else {
                        planning.put(index.getJsonAttribute(), cell.getNumericCellValue());
                    }
                } else {
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            if (cell.getStringCellValue().isEmpty()) {          //Again whenever a cell is empty, we add the key to
                                secondary.add(index);                           //the secondary key list
                            }
                            else
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

    private void secondaryAddToJson(JSONObject obj, JSONObject planning, Cell cell) { //This method is basically the same as the regular
        for (Key index : secondary) {                                                 //AddToJson(), except it uses the secondary list
            if (index.getSecondaryKeyWord() == null) {                                //of Keys, using a secondary key, or a default value
                obj.put(index.getJsonAttribute(), index.getDefaultValue());
            }
            if (index.getSecondaryIndex() == cell.getColumnIndex()) {
                if (index.getJsonAttribute().toLowerCase().contains("date")) {
                    if (cell.getCellTypeEnum() == STRING) {
                        if (cell.getStringCellValue().isEmpty()) {
                            planning.put(index.getJsonAttribute(), index.getDefaultValue());
                        }
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        planning.put(index.getJsonAttribute(), sdf.format(cell.getDateCellValue()));
                    }
                } else if (index.getJsonAttribute().toLowerCase().contains("time")) {
                    if (cell.getStringCellValue().isEmpty()) {
                        planning.put(index.getJsonAttribute(), index.getDefaultValue());
                    } else {
                        planning.put(index.getJsonAttribute(), cell.getNumericCellValue());
                    }
                } else {
                    switch (cell.getCellTypeEnum()) {
                        case STRING:                               
                            if (cell.getStringCellValue().isEmpty()) {
                                obj.put(index.getJsonAttribute(), index.getDefaultValue());
                            } else {
                                obj.put(index.getJsonAttribute(), cell.getStringCellValue());
                            }
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

    public JSONArray getJsonFiles() {
        return myJSONObjects;
    }
}
