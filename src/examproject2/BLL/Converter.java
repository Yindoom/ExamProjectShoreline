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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Yindo
 */
public class Converter {

    List<Header> headers = new ArrayList();
    List<Config> keys;
    JSONArray myJSONObjects = new JSONArray();

    public enum filetype {
        xlsx,
        xml
    }

    public void convert(Iterator<Row> itr, filetype xlsx, List config) throws IOException {

        keys = config;

        while (itr.hasNext()) {
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
            if (row.getRowNum() != 0) {
                obj.put("createdOn", LocalDate.now());
                obj.put("Planning", planning);
                myJSONObjects.put(obj);
            }

        }

    }

    private void setIndex() {
        for (Header header : headers) {
            for (Config key : keys) {
                if (header.getValue().toLowerCase().trim().contains(key.getKeyWord().toLowerCase().trim())) {
                    key.setColumnIndex(header.getIndex());
                }
            }
        }
    }

    private void getHeaders(Cell cell) {

        Header newheader = new Header();
        newheader.setIndex(cell.getColumnIndex());
        newheader.setValue(cell.getStringCellValue());
        int n = 1;
        for (Header header : headers) {
            if (newheader.getValue().equals(header.getValue())) {
                n++;
                newheader.setValue(header.getValue() + n);
            }
        }
        headers.add(newheader);
    }

    private void addToJson(JSONObject obj, JSONObject planning, Cell cell) {
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
                        case _NONE:

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
