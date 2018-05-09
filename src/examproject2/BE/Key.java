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
public class Key {

    private String keyWord;
    private String jsonAttribute;
    private int columnIndex;
    private String secondaryKeyWord;
    private String defaultValue;
    private int secondaryIndex;

    public int getSecondaryIndex() {
        return secondaryIndex;
    }

    public void setSecondaryIndex(int secondaryIndex) {
        this.secondaryIndex = secondaryIndex;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getSecondaryKeyWord() {
        return secondaryKeyWord;
    }

    public void setSecondaryKeyWord(String secondaryKeyWord) {
        this.secondaryKeyWord = secondaryKeyWord;
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
