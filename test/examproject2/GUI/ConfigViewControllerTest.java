/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examproject2.GUI;

import examproject2.BE.Key;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Yindo
 */
public class ConfigViewControllerTest {
    
    public ConfigViewControllerTest() {
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
     * Test of initialize method, of class ConfigViewController.
     */
    @org.junit.Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        ConfigViewController instance = new ConfigViewController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setUser method, of class ConfigViewController.
     */
    @org.junit.Test
    public void testSetUser() {
        System.out.println("setUser");
        String currentUser = "";
        String activity = "";
        ConfigViewController instance = new ConfigViewController();
        instance.setUser(currentUser, activity);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @org.junit.Test
    public void testSave() {
        List<Key> keys = new ArrayList();
        Key type = new Key("type");
        Key externalWorkOrderId = new Key("externalWorkOrderId");
        Key priority = new Key("priority");
        
        keys.add(type);
        keys.add(externalWorkOrderId);
        keys.add(priority);
        
        ConfigViewController instance = new ConfigViewController();
        instance.save(keys);
        
    }
}
