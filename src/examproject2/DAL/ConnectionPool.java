/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.DAL;

import examproject2.DAL.ObjectPool;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import examproject2.BLL.BLLManager;
import examproject2.DAL.ConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pgn
 */
public class ConnectionPool extends ObjectPool<Connection> {

    private static ConnectionPool INSTANCE;
    private Connection con;
    private ConnectionManager connector;

    public ConnectionPool() {
        super();
        connector = new ConnectionManager();

    }

    @Override
    public void expire(Connection o) {
        try {
            o.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean validate(Connection o) {
        try {
            return !o.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    protected Connection create(){
        try {
            con = connector.getConnection();
        } catch (SQLServerException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
            public synchronized static ConnectionPool getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }

}
