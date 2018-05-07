/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.DAL;

import examproject2.BE.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yindo
 */
public class DatabaseDAO {
    
    private ConnectionManager cm = new ConnectionManager();
    
    public List<Config> getKeyWords() {
        List<Config> configs
                = new ArrayList();

        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM keyword WHERE config=1");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Config config = new Config();
                config.setJsonAttribute(rs.getString("jsonAttribute".trim()));
                config.setKeyWord(rs.getString("keyword".trim()));
                configs.add(config);
                config.getJsonAttribute();
                config.getKeyWord();
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return configs;
    }
    
}
