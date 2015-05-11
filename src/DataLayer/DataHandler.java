/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author hasaan
 */

//Singleton-Class
public class DataHandler {
    private Settings settings=null;
    private DataHandler(){
        settings = new Settings();
    }

    
    private static DataHandler instance = null;
    public static DataHandler getInstance(){
        if(instance==null)
            instance = new DataHandler();
        return instance;
    }
}
