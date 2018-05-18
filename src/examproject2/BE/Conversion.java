/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BE;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Bastian
 */
public class Conversion {

    private final StringProperty filePath = new SimpleStringProperty();
    private final StringProperty fileName = new SimpleStringProperty();
    private final DoubleProperty progress = new SimpleDoubleProperty();
    private String savePath;
    private final ObjectProperty<Thread> task = new SimpleObjectProperty<>();

    public Thread getTask() {
        return task.get();
    }

    public void setTask(Thread value) {
        task.set(value);
    }

    public ObjectProperty taskProperty() {
        return task;
    }
    

    
    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getFilePath() {
        return filePath.get();
    }

    public void setFilePath(String value) {
        filePath.set(value);
    }

    public StringProperty filePathProperty() {
        return filePath;
    }

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
