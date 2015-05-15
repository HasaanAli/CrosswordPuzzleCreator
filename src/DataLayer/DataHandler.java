/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author hasaan
 */

//Singleton-Class
public class DataHandler {
    private Settings settings=null;

    
    private HashMap<String, String> answerClueHashMap = null;
    private HashMap<Integer,ArrayList<String>> lengthAnswerHashMap = null;
    private HashMap<String, ArrayList<String>> charPositionHashMap = null;
    
    public HashMap<String, String> getAnswerClueHashMap() {
        if(answerClueHashMap==null){
            loadDictionary();
        }
        return answerClueHashMap;
        
    }

    public HashMap<Integer, ArrayList<String>> getLengthAnswersHashMap() {
        if(lengthAnswerHashMap==null){
            loadDictionary();
        }
        return lengthAnswerHashMap;
    }

    public HashMap<String, ArrayList<String>> getCharPosiHashMap(){
        if(charPositionHashMap==null){
        
            if(answerClueHashMap==null){
                loadDictionary();
            }
            loadCharPosiHashMap();
        }        
        return charPositionHashMap;
    }

    public void loadCharPosiHashMap()   {
        charPositionHashMap = new HashMap<String, ArrayList<String>>();
        
        Set set = answerClueHashMap.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext())
        {
            Map.Entry me = (Map.Entry)iterator.next();
            String word = me.getKey().toString();
            for(int i=0;i<word.length();i++)
            {
                String key = word.charAt(i)+""+i;       // key = a0,a1,a2 etc
                ArrayList<String> wordList;
                if(charPositionHashMap.get(key)==null)         // if key not found then add entry in hashmap
                {
                    wordList = new ArrayList<String>();
                    wordList.add(word);
                    
                }
                else
                {
                    wordList = charPositionHashMap.get(key); // return list of words according to alphabet position
                    wordList.add(word);
                    
                }
                charPositionHashMap.put(key, wordList);
            }
            
        }
        displayHashMap(charPositionHashMap);
    }
    
    public void loadDictionary()    {
        //initialize relevant hashmaps
        answerClueHashMap = new HashMap<String, String>();
        lengthAnswerHashMap = new HashMap<Integer, ArrayList<String>>();
        
        //read files and fill lists
        File file = new File("dictionary.txt");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader breader = new BufferedReader(reader);
            
            String line = "";
            while((line = breader.readLine())!=null)
            {
                StringTokenizer strtok = new StringTokenizer(line, "$");
                String clue = strtok.nextToken().toLowerCase().trim();
                String answer = strtok.nextToken().toLowerCase().trim().replace(" ", "");

                answerClueHashMap.put(answer,clue);
                
                ArrayList<String> wordList;
                if(lengthAnswerHashMap.get(answer.length())==null)         // if key not found then add entry in hashmap
                {
                    wordList = new ArrayList<String>();
                    wordList.add(answer);
                    
                }
                else
                {
                    wordList = lengthAnswerHashMap.get(answer.length()); // return list of words according to alphabet position
                    if(!wordList.contains(answer))
                    {
                        wordList.add(answer);
                    }
                    
                }
                lengthAnswerHashMap.put(answer.length(), wordList);       // hash map on basis of words size
                
//                if(answer.length()>size)
//                {
//                    size = answer.length();
//                }
//                System.out.println(clue+"           "+answer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        displayHashMap(lengthAnswerHashMap);
        
    }
    public void displayHashMap(HashMap hashMap)
    {
        Set alSet = hashMap.entrySet();
        Iterator alIterator = alSet.iterator();
        while(alIterator.hasNext())
        {
            Map.Entry me = (Map.Entry) alIterator.next();
            System.out.print(me.getKey()+"      ");
            ArrayList<String> wordList = (ArrayList<String>) me.getValue();
            for(String s:wordList)
            {
                System.out.print(s+" --> ");
            }
            System.out.println();
        }
    }

    
    
    ////////////////////////////////////////
    ////////Singleton Pattern///////////////
    private static DataHandler instance = null;    
    private DataHandler(){
        settings = new Settings();
    }
    public static DataHandler getInstance(){
        if(instance==null)
            instance = new DataHandler();
        return instance;
    }
    
}
