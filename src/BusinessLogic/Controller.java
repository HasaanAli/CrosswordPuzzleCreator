/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

/**
 *
 * @author hasaan
 */
//Singleton Class
public class Controller {
    
    private static Controller instance = null;
    
    private Controller(){
        
    }
    public static Controller getInstance(){
        if(instance==null)
            instance = new Controller();
        return instance;
    }
    
}
