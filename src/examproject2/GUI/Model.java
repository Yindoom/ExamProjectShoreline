/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

/**
 *
 * @author Yindo
 */
public class Model {
    
    private Model() {
    }
    
    public static Model getInstance() {
        return ModelHolder.INSTANCE;
    }
    
    private static class ModelHolder {

        private static final Model INSTANCE = new Model();
    }
}
