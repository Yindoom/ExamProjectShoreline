/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BLL;

import examproject2.BE.Activity;
import examproject2.BE.Config;
import examproject2.BE.Conversion;
import examproject2.BE.Key;
import examproject2.DAL.DALManager;
import examproject2.DAL.IDALFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import static javafx.scene.control.Pagination.INDETERMINATE;
import javafx.scene.control.TextField;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Bastian
 */
public class BLLManager implements IBLLFacade {

    private static BLLManager INSTANCE;
    IDALFacade dal = new DALManager();

    public synchronized static BLLManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BLLManager();
        }
        return INSTANCE;
    }

    @Override
    public void convert(ObservableList<Conversion> conversions, Config con) throws IOException {

        for (Conversion conversion : conversions) {
            Thread t = new Thread(setTask(conversion, con));
            t.setDaemon(true);
            conversion.setTask(t);
            conversion.getTask().start();
        }
    }

    @Override
    public List<Config> getConfigs() {
        return dal.getAllConfigs();
    }

    @Override
    public List<Key> getKeys(Config selectedConfig) {
        return dal.getKeys(selectedConfig);
    }

    @Override
    public void saveConfig(Config config, List<Key> keys) {
        boolean proceed = true;
        try {
            dal.saveConfig(config);
        } catch (NullPointerException e) {
            proceed = false;
        }

        if (proceed) {
            for (Key key : keys) {
                key.setId(config.getId());
            }
            for (Key key : keys) {
                dal.saveKey(key);
            }
        }
    }

    @Override
    public void updateConfig(List<Key> keys) {
        for (Key key : keys) {
            dal.updateKey(key);
        }
    }

    @Override
    public void log(Activity log) {
        dal.log(log);
    }

    @Override
    public ObservableList getActivity() {
        return dal.getActivity();
    }

    private Runnable setTask(Conversion conversion, Config con) {
        Runnable runCon = new Runnable() {
            @Override
            public void run() {
                Converter converter = new Converter();
                List<Config> config = new ArrayList(dal.getConfig(con));

                try {
                    conversion.setProgress(0.5);
                    converter.convert(dal.getIterator(conversion.getFilePath()), config);
                } catch (IOException ex) {
                    Logger.getLogger(BLLManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BLLManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    dal.write(converter.myJSONObjects, conversion.getSavePath(), conversion.getFileName());
                    conversion.setProgress(1);
                } catch (IOException ex) {
                    Logger.getLogger(BLLManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        return runCon;
    }
}
