/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.DAL;

import examproject2.BE.Config;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Yindo
 */
public class DALManager {
    
    DatabaseDAO db = new DatabaseDAO();

    public List<Config> getConfig() {
        return db.getKeyWords();
    }
    
}
