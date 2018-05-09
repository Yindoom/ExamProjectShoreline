/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.DAL;

import examproject2.BE.Config;
import examproject2.BE.Key;
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

    public List<Key> getKeyWords(Config configuration) {
        List<Key> configs
                = new ArrayList();

        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM keyword WHERE config=?");
            stmt.setInt(1, configuration.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Key config = new Key();
                config.setJsonAttribute(rs.getString("jsonAttribute"));
                config.setDefaultValue(rs.getString("defaultvalue"));
                config.setSecondaryKeyWord(rs.getString("secondarykeyword"));
                config.setKeyWord(rs.getString("keyword"));
                configs.add(config);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return configs;
    }

    List<Config> getConfigs() {
        List<Config> configs
                = new ArrayList();

        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM Configs");
            //stmt.setInt(1, );
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Config config = new Config();
                config.setName(rs.getString("name"));
                config.setId(rs.getInt("id"));
                configs.add(config);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return configs;
    }

}
