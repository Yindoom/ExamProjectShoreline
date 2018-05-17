/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.DAL;

/**
 *
 * @author Yindo
 */
public class ConnectionPool {
    
    private ConnectionPool() {
    }
    
    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }
    
    private static class ConnectionPoolHolder {

        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}
