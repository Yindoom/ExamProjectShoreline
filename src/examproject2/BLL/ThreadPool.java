/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BLL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import examproject2.BLL.BLLManager;
import examproject2.DAL.ConnectionManager;
import examproject2.DAL.ConnectionPool;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pgn
 */
public class ThreadPool extends ObjectPool2<Thread> {
    
    private static ThreadPool INSTANCE;

    public ThreadPool() {
        super();
    }

    @Override
    public void expire(Thread o) throws InterruptedException {
        o.join();
    }

    @Override
    public boolean validate(Thread o) {
            return o.isAlive();
    }

    @Override
    protected Thread create() {
        Thread t = new Thread();
        return t;
    }

    public synchronized static ThreadPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadPool();
        }
        return INSTANCE;
    }

}
