/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataLayer.DataHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author hasaan
 */
public class PuzzleGenerator {
private HashMap<String, String> answerClueHashMap = null;
    private HashMap<Integer,ArrayList<String>> lengthAnswerHashMap = null;
    private HashMap<String, ArrayList<String>> charPositionHashMap = null;
    
    public PuzzleGenerator() {
        DataHandler dh = DataHandler.getInstance();
        answerClueHashMap = dh.getAnswerClueHashMap();
        lengthAnswerHashMap = dh.getLengthAnswersHashMap();
        charPositionHashMap = dh.getCharPosiHashMap();
    }

    
    public void generate(Character[][] puzzleGrid, LinkedHashMap<String, String> acrossAnsClueHM, LinkedHashMap<String, String> downAnsClueHM) {
        
       //get grid size from puzzleGrid[gridSize][gridSize]
       int size = puzzleGrid.length;
       
       for(int i=0;i<size;i++)
       {
           for(int j=0;j<size;j++)
           {
               puzzleGrid[i][j]='.';
           }
       }
       
       ArrayList<String> acrossWordsList = new ArrayList<String>();
       ArrayList<String> downWordsList = new ArrayList<String>();
       
       int x=0,temp=0;
       int y=0;
       int clueNo = 1;
       
       Boolean wordFound = false, conflictFound = false;
       String word = "";
       ArrayList<String> acrossConflicts;
       ArrayList<String> downConflicts;
       
       while(wordFound == false || temp<200)
       {
           System.out.println("temp = "+temp);
           System.out.println("Total Words = "+ answerClueHashMap.size());
           System.out.println("Inserted Words = "+(acrossWordsList.size()+downWordsList.size()));
           acrossConflicts = new ArrayList<String>();
           downConflicts = new ArrayList<String>();
            // selecting random starting position of word
           x=generateRandom(0,size-1);
           y=generateRandom(0,size-1);
           System.out.println("X="+x+","+"Y="+y);
           // size of word in across or down
           int acrossWordSize = 0;
           int downWordSize = 0;
           for(int i=y;i<size;i++)
           {
               if(puzzleGrid[x][i]!='.')
               {
                   int tempSize = 0;
                   String tempWord = "";
                   for(int j=i;j<size;j++)
                   {
                       if(puzzleGrid[x][j] == '.')
                       {
                           break;
                       }
                       tempWord+=puzzleGrid[x][j];
                       tempSize++;
                   }
                   System.out.println("tempWord = "+tempWord+", tempSize = "+tempSize);
//                   if(puzzleGrid[x][i+2]=='.')
//                   {
//                           acrossConflicts.add(puzzleGrid[x][i]+""+acrossWordSize);
//                           conflictFound = true;
//                   }
//                   else
//                   {
//                       break;
//                   }
                   if(tempSize<2)   // valid conflict
                   {
                       acrossConflicts.add(tempWord+""+acrossWordSize);
                       conflictFound = true;
                   }
                   else
                   {
                       break;
                   }
               }
               acrossWordSize++;
           }
           
           for(int j=x;j<size;j++)
           {
               if(puzzleGrid[j][y]!='.')
               {
                   int tempSize = 0;
                   String tempWord = "";
                   for(int i=j;i<size;i++)
                   {
                       if(puzzleGrid[i][y] == '.')
                       {
                           break;
                       }
                       tempWord+=puzzleGrid[i][y];
                       tempSize++;
                   }
                   System.out.println("tempWord = "+tempWord+", tempSize = "+tempSize);
//                   if(puzzleGrid[j+2][y]=='.')
//                   {
//                        downConflicts.add(puzzleGrid[j][y]+""+downWordSize);
//                        conflictFound = true;
//                   }
//                   else
//                   {
//                       break;
//                   }
                   if(tempSize<2)   // valid conflict
                   {
                       downConflicts.add(tempWord+""+downWordSize);
                       conflictFound = true;
                   }
                   else
                   {
                       
                       break;
                   }
               }
               downWordSize++;
           }
           System.out.println("AcrossWordSize = "+acrossWordSize+", DownWordSize = "+downWordSize);
           
           if(conflictFound == false)
           {
                int randomIndex  = generateRandom(0, lengthAnswerHashMap.size()-1);
                System.out.println("randomIndex = "+randomIndex);
                int n=0;
                Set set = lengthAnswerHashMap.entrySet();
                Iterator iterator = set.iterator();
                while(iterator.hasNext())
                {
                    Map.Entry me = (Map.Entry) iterator.next();
                     if(n==randomIndex)                                  // if index match
                     {
                         if((Integer)me.getKey() <= acrossWordSize && acrossWordSize>downWordSize)
                         {
                             

                             ArrayList<String> wordList = (ArrayList<String>) me.getValue();
                             int randomWord = generateRandom(0,wordList.size()-1);
                             word = wordList.get(randomWord);
                             if(acrossWordsList.contains(word) == false && downWordsList.contains(word) == false)
                             {
                                wordFound = true;
                                for(int i=0;i<word.length();i++)
                                {
                     //             System.out.println("ACROSS - "+x+","+(y+i));
                                    puzzleGrid[x][y+i] = word.charAt(i);
                                }
                                String tempClue = (clueNo++) +"."+answerClueHashMap.get(word);
                                acrossAnsClueHM.put(word, tempClue);
                                
                                acrossWordsList.add(word);
                             }
                         }
                         if(wordFound == false && (Integer)me.getKey() <= downWordSize)
                         {

                             ArrayList<String> wordList = (ArrayList<String>) me.getValue();
                             int randomWord = generateRandom(0,wordList.size()-1);
                             word = wordList.get(randomWord);
                             if(acrossWordsList.contains(word) == false && downWordsList.contains(word) == false)
                             {
                                wordFound = true;
                                for(int i=0;i<word.length();i++)
                                {
                     //             System.out.println("ACROSS - "+x+","+(y+i));
                                    puzzleGrid[x+i][y] = word.charAt(i);
                                }
                                
                                String tempClue = (clueNo++)+"."+answerClueHashMap.get(word);
                                downAnsClueHM.put(word, tempClue);
                                downWordsList.add(word);
                             }
                         }
                         break;
                     }
                     n++;
                 }
           }
           else
           {
               ArrayList<String> wordList = new ArrayList<String>();
               Boolean matchFound = false;
               
               
               System.out.println("AcrossConflict size - "+acrossConflicts.size());
               System.out.println("DownConflict size - "+downConflicts.size());
               if(acrossConflicts.isEmpty()==false)
               {
                   ArrayList<String> list = charPositionHashMap.get(acrossConflicts.get(0));
                   System.out.print(acrossConflicts.get(0)+" , ");
                                
                   if(list!=null && list.isEmpty()==false)
                   {
                        for(int i=0;i<list.size();i++)
                        {
                            if(acrossConflicts.size()>1)
                            {    
                                for(int j=1;j<acrossConflicts.size();j++)
                                {
                                    System.out.print(acrossConflicts.get(j)+" , ");

                                    ArrayList<String> list2 = charPositionHashMap.get(acrossConflicts.get(j));
                                    if(list2!=null && list2.contains(list.get(i)))
                                    {    matchFound = true;  }
                                    else
                                    {    matchFound = false; }
                                }
                            }
                            else
                            {
                                if(list.get(i).length()<acrossWordSize)
                                {
                                    matchFound = true;
                                }
                            }
                             System.out.println(list.get(i)+","+list.get(i).length()+",match = "+matchFound+","+acrossWordsList.contains(list.get(i))+","+downWordsList.contains(list.get(i)));
                            if(matchFound == true && list.get(i).length()<acrossWordSize && !acrossWordsList.contains(list.get(i)) && !downWordsList.contains(list.get(i)))
                            {
//                                System.out.println("Insert down = "+list.get(i));
//                                System.out.println("X="+x+","+"Y="+y);
                                int a=0;
                                 for(int k=y;k<y+list.get(i).length();k++)
                                 {
//                                     System.out.println(a);
                                     puzzleGrid[x][k] = list.get(i).charAt(a++);
                                 }
                                 String tempClue = (clueNo++)+"."+answerClueHashMap.get(list.get(i));
                                 acrossAnsClueHM.put(list.get(i), tempClue);
                                 
                                 acrossWordsList.add(list.get(i));
                            }
                        }
                        
                   }
                   System.out.println();
               }
               if(downConflicts.isEmpty()==false)
               {
                   ArrayList<String> list = charPositionHashMap.get(downConflicts.get(0));
                   System.out.print(downConflicts.get(0)+" , ");
                                
                   if( list!=null && list.isEmpty()==false)
                   {
                        for(int i=0;i<list.size();i++)
                        {
                            
                            if(downConflicts.size()>1)
                            {
                                for(int j=1;j<downConflicts.size();j++)             // if there are more than 1 conflicts
                                {
                                    System.out.print(downConflicts.get(j)+" , ");
                                    ArrayList<String> list2 = charPositionHashMap.get(downConflicts.get(j));
                                    if(list2!=null && list2.contains(list.get(i)))
                                    {    matchFound = true;  }
                                    else
                                    {    matchFound = false; }
                                }
                            }
                            else
                            {
                                if(list.get(i).length()<downWordSize)
                                {
                                    matchFound = true;
                                }
                            }
                             System.out.println(list.get(i)+","+list.get(i).length()+",match = "+matchFound+","+acrossWordsList.contains(list.get(i))+","+downWordsList.contains(list.get(i)));
                            if(matchFound == true && list.get(i).length()<downWordSize && !acrossWordsList.contains(list.get(i)) && !downWordsList.contains(list.get(i)))
                            {
//                                System.out.println("Insert down = "+list.get(i));
//                                System.out.println("X="+x+","+"Y="+y);
                                int a=0;
                                 for(int k=x;k<x+list.get(i).length();k++)
                                 {
//                                     System.out.println(a);
                                     puzzleGrid[k][y] = list.get(i).charAt(a++);
                                 }
                                 String tempClue = (clueNo++)+"."+answerClueHashMap.get(list.get(i));
                                 downAnsClueHM.put(list.get(i), tempClue);
                                 
                                 downWordsList.add(list.get(i));
                            }
                        }
                       
                   }
                   System.out.println();
               }
               conflictFound = false;
           }
           for(int i=0;i<size;i++)
            {
                for(int j=0;j<size;j++)
                {
                    System.out.print(puzzleGrid[i][j]+"    ");
                }
                System.out.println();
            }
           temp++;
       }
       
       

    }
    
    private int generateRandom(int min, int max){
        Random random = new Random();
        int rand = random.nextInt((max-min)+1)+min;
        return rand;
    }

}
