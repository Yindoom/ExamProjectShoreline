/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Emil Pc
 */
public class Activity {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty subject = new SimpleStringProperty();
    private final StringProperty type = new SimpleStringProperty();
    

    public StringProperty nameProperty() {
        return name;
    }
    
    public StringProperty subjectProperty() {
        return subject;
    }
    
    public StringProperty typeProperty() {
        return type;
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String value) {
        type.set(value);
    }

    public String getSubject() {
        return subject.get();
    }

    public void setSubject(String value) {
        subject.set(value);
    }
}
