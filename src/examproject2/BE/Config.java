/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BE;

/**
 *
 * @author Yindo
 */
public class Config {

    private String dataType;
    private String keyWord;
    private String jsonAttribute;
    private int columnIndex;
    private String stringData;
    private int intData;

    
    public int getIntData() {
        return intData;
    }

    public void setIntData(int intData) {
        this.intData = intData;
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getJsonAttribute() {
        return jsonAttribute;
    }

    public void setJsonAttribute(String jsonAttribute) {
        this.jsonAttribute = jsonAttribute;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

}
