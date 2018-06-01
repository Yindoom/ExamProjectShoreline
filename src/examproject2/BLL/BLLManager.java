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
import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.Sheet;

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

        for (Conversion conversion : conversions) {                             // goes through all Conversions in the list
            try {                                                               // 3 times, to set their threads
                Thread t = new Thread(setTask(conversion, con));                // start the threads, and join the threads
                t.setDaemon(true);
                conversion.setTask(t);
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("No config");
                alert.setContentText("Please select a configuration");
                alert.showAndWait();
            }
        }
        for (Conversion conversion : conversions) {
            conversion.getTask().start();
        }
        for (Conversion conversion : conversions) {
            try {
                conversion.getTask().join();
            } catch (InterruptedException ex) {
                Logger.getLogger(BLLManager.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                xlsxConverter converter = new xlsxConverter();
                List<Key> config = new ArrayList(dal.getConfig(con));

                try {
                    converter.convert(getSheet(conversion.getFilePath()), config, conversion);
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

    public Sheet getSheet(String path) throws IOException {                     // saves the sheet to memory, using
        Sheet sheet = null;                                                     // methods based on which type of file
        if (path.endsWith(".csv")) {
            sheet = dal.getCSV(path);
        }
        if (path.endsWith(".xlsx")) {
            sheet = dal.getIterator(path);
        }
        return sheet;
    }
}
