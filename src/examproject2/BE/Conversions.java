/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BE;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Bastian
 */
public class Conversions {

    private final StringProperty fileName = new SimpleStringProperty();
    private final DoubleProperty progress = new SimpleDoubleProperty();

    public double getProgress() {
        return progress.get();
    }

    public void setProgress(double value) {
        progress.set(value);
    }

    public DoubleProperty progressProperty() {
        return progress;
    }
    

    public String getFileName() {
        return fileName.get();
    }

    public void setFileName(String value) {
        fileName.set(value);
    }

    public StringProperty fileNameProperty() {
        return fileName;
    }
    
    
    
}
