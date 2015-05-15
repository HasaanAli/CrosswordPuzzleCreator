/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataLayer.DataHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author hasaan
 */
//Singleton Class
public class Controller {

    public void generatePuzzle(Character[][] puzzleGrid, LinkedHashMap<String, String> acrossAnsClueHM, LinkedHashMap<String, String> downAnsClueHM) {
        new PuzzleGenerator().generate(puzzleGrid, acrossAnsClueHM, downAnsClueHM);
        
    }
    
    
    
    
    
    ////////////////////////////////////////
    ///////////Singleton////////////////////
    private static Controller instance = null;
    private Controller(){
    }
    public static Controller getInstance(){
        if(instance==null)
            instance = new Controller();
        return instance;
    }

    
}
