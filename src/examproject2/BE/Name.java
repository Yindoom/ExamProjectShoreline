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
 * @author ZeXVex
 */
public class Name {

    private final StringProperty Name = new SimpleStringProperty();

    @Override
    public String toString() {
        return Name.getValue();
    }

    
    
    public Name(String name) {
        this.Name.set(name);
    }

    
    
    public String getName() {
        return Name.get();
    }

    public void setName(String value) {
        Name.set(value);
    }

    public StringProperty NameProperty() {
        return Name;
    }
    
}
