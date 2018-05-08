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
import static org.apache.poi.ss.usermodel.CellType.BLANK;
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
    List<Config> keys;
    JSONArray myJSONObjects = new JSONArray();
    List<Config> secondary;

    public enum filetype {
        xlsx,
        xml
    }

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

            while (secondIterator.hasNext()) {
                Cell cell = secondIterator.next();
                secondaryAddToJson(obj, planning, cell);
            }
            if (row.getRowNum() != 0) {
                obj.put("assetSerialNumber", "asset.id");
                obj.put("siteName", "");
                obj.put("createdBy", "SAP");
                obj.put("createdOn", LocalDate.now());
                obj.put("Planning", planning);
                myJSONObjects.put(obj);
            }

        }

    }

    private void setIndex() {
        for (Header header : headers) {
            for (Config key : keys) {
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
            if (index.getKeyWord() == null) {
                secondary.add(index);
            }
            if (index.getColumnIndex() == cell.getColumnIndex()) {
                if (index.getJsonAttribute().toLowerCase().contains("date")) {
                    if (cell.getCellTypeEnum() == STRING) {
                        if (cell.getStringCellValue().isEmpty()) {
                            secondary.add(index);
                        }
                    } else {
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
                            if (cell.getStringCellValue().isEmpty()) {
                                secondary.add(index);
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

    private void secondaryAddToJson(JSONObject obj, JSONObject planning, Cell cell) {
        for (Config index : secondary) {
            if (index.getSecondaryKeyWord() == null) {
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
