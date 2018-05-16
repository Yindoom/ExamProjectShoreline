/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.DAL;

import examproject2.BE.Config;
import examproject2.BE.Key;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void saveConfig(Config config) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO Configs"
                    + "(name, filetype) "
                    + "VALUES(?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, config.getName());
            pstmt.setString(2, config.getFileType());

            int affected = pstmt.executeUpdate();
            if (affected < 1) {
                throw new SQLException("movie could not be added");
            }

            // Get database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                config.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    int getConfigId(Config config) {
        int id = -1;
        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT id FROM Configs WHERE name=?" );
            stmt.setString(1, config.getName());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return id;
    }

    public void saveKey(Key key) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO keyword"
                    + "(keyword, jsonAttribute, config, secondarykeyword, defaultvalue) "
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, key.getKeyWord());
            pstmt.setString(2, key.getJsonAttribute());
            pstmt.setInt(3, key.getId());
            pstmt.setString(4, key.getSecondaryKeyWord());
            pstmt.setString(5, key.getDefaultValue());

            int affected = pstmt.executeUpdate();
            if (affected < 1) {
                throw new SQLException("keyword could not be added");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

}
