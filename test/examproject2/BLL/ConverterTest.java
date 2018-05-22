/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.BLL;

import examproject2.BE.Conversion;
import examproject2.DAL.DALManager;
import examproject2.DAL.IDALFacade;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import examproject2.BE.Config;

/**
 *
 * @author Yindo
 */
public class ConverterTest {
    
    xlsxConverter instance = new xlsxConverter();
    
    public ConverterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of convert method, of class xlsxConverter.
     */
    @Test
    public void testConvert() throws Exception {
        IDALFacade dal = new DALManager();
        Config con = new Config();
        con.setId(1);
        System.out.println("convert");
        Sheet sheet = dal.getIterator("C:\\Users\\Yindo\\Desktop\\Exam\\Import_data.xlsx");
        List config = dal.getConfig(con);
        Conversion conversion = new Conversion();
        instance.convert(sheet, config, conversion);
        int actualResult = instance.getJsonFiles().length();
        int expResult = 49;
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testCalculateProgress() {
        System.out.println("calculate");
        int rows = 1_025_024;
        int rowNum = 1;
        double expRes = 9.7558691308691308691308691308691e-7;
        double actualRes = instance.calculateProgress(rowNum, rows);
        assertEquals(expRes, actualRes, 0.005);
    }
}
