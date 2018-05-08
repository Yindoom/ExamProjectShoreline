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

    private String keyWord;
    private String jsonAttribute;
    private int columnIndex;
    
    
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
