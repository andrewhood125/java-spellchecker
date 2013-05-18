
/**
 * Return a list of suggestions for a misspelled word
 * 
 * @author Andrew Hood, --- 
 * @version 0.3
 * 
 * Version 0.3
 * - Breaking out suggest1,2 and 3 methods to accept LinkedList<String> corrections
 *   and a wrong word.
 * 
 * Version 0.2
 * - Breaking out the correct method to Omission, Swap, Single Letter and Add a letter.
 * - Adding the +All methods.
 *   Before +All methods
 *      (tommorow) 1*TOMM* 2*TOMMER* 3** 
 *      (Carribean) 1*CARRIE* 2*CARRIABLE* 3** 
 *      (reicieve) 1*REIC* 2*REICHENB* 3** 
 *      (basicly) 1*BASICY* 2*BASICAL* 3** 
 *      (bizzare) 1*BIZZ* 2*BIZZ* 3** 
 *   After +All methods
 *      (tommorow) *tomorrow* *tomorrow* *tomorrow* *tomorrow* *tomorrow* 1*TOMM* 2*TOMMER* 3** 
 *      (Carribean) *Caridean* *Caribbean* *Caribbean* *Caridean* *Caribbean* *Caribbean* *Caribbean* 1*CARRIE* 2*CARRIABLE* 3** 
 *      (reicieve) *receive* *relieve* *relieve* *receive* 1*REIC* 2*REICHENB* 3** 
 *      (basicly) *bailly* *basely* *easily* *busily* *basely* *basify* *basils* *basify* *basics* *basial* *basics* 1*BASICY* 2*BASICAL* 3** 
 *      (bizzare) *izzard* *bizarre* *bizarre* *bizarre* *bizarre* *rizzar* *bizarre* 1*BIZZ* 2*BIZZ* 3** 
 * Version 0.1 
 * -This is the initial build and design of the Suggest class
 */

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class Suggest
{
    // instance variables
    ABCTrie root;
    
    // constructor
    public Suggest(ABCTrie root)
    {
        this.root = root;
    }
    
    /*
     * Omission
     * LinkedList<String> corrections - accepts a list
     *  to be populated with possible corrections.
     *  
     *  String word - provides the misspelled word to 
     *   to try and provide corrections for.
     *  
     *  Return void - The results will be added to the 
     *    list a return is not neccesary.
     *    
     *  Description - This method works on the basis of
     *  if user accidentally repeats a letter inserts a
     *  letter incorrectly this will find the word they
     *  intended to type be iteration through delete each
     *  letter and seeing if it is a valid word.
     */
    public void omission(LinkedList<String> corrections, String wrongWord)
    {
        // Omission
        for(int i = 0; i < wrongWord.length(); i++)
        {
            String testWord = wrongWord.substring(0,i) + wrongWord.substring(i+1);
            if(root.hasWord(testWord))
            {
                corrections.add(testWord);
            }
        }
    }
    
    /*
     * Swap
     *  LinkedList<String> corrections - accepts a list
     *  to be populated with possible corrections.
     *  
     *  String word - provides the misspelled word to 
     *   to try and provide corrections for.
     *  
     *  Return void - The results will be added to the 
     *    list a return is not neccesary.
     *    
     *  Description - Swap works on the basis that a user
     *  types two letters in reverse order. 
     *      'Example' Copmuter => Computer
     *  Swap will swap two adjacent letters for the length 
     *  of the word.
     */
    public void swap(LinkedList<String> corrections, String wrongWord)
    {
        for(int i = 0; i+1 < wrongWord.length(); i++)
        {
            String testWord = wrongWord.substring(0,i) + wrongWord.charAt(i+1) + wrongWord.charAt(i) + wrongWord.substring(i+2);
            if(root.hasWord(testWord))
            {
                corrections.add(testWord);
            }
        }
    }
    
    /*
     * singleLetter
     * LinkedList<String> corrections - accepts a list
     *  to be populated with possible corrections.
     *  
     *  String word - provides the misspelled word to 
     *   to try and provide corrections for.
     *  
     *  Return void - The results will be added to the 
     *    list a return is not neccesary.
     *    
     *  Description - Change a single letter works on
     *   the assumption that a user mistyped a single 
     *   letter. Each letter iterates through A=>Z.
     */
    public void singleLetter(LinkedList<String> corrections, String wrongWord)
    {
        for(int i = 0; i < wrongWord.length(); i++)
        {
            String testWord;
            for(int a = 'a'; a < 'z'; a++)
            {
                testWord = wrongWord.substring(0,i) + ((char) a) + wrongWord.substring(i+1);
                if(root.hasWord(testWord))
                {
                    corrections.add(testWord);
                }
            }
        }
    }
    
    /*
     * addLetter
     * LinkedList<String> corrections - accepts a list
     *  to be populated with possible corrections.
     *  
     *  String word - provides the misspelled word to 
     *   to try and provide corrections for.
     *  
     *  Return void - The results will be added to the 
     *    list a return is not neccesary.
     *    
     *  Description - Add a letter works on the basis
     *   a single letter was omitted and for each index
     *   it iterates a=>z to find a word that will make
     *   that gap.
     */
    public void addLetter(LinkedList<String> corrections, String wrongWord)
    {
        for(int i = 0; i < wrongWord.length(); i++)
        {
            String testWord;
            for(int a = 'a'; a < 'z'; a++)
            {
                testWord = wrongWord.substring(0,i) + ((char) a) + wrongWord.substring(i);
                if(root.hasWord(testWord))
                {
                    corrections.add(testWord);
                }
            }
        }
    }
    
    
    /*
     * Omission All
     * LinkedList<String> corrections - accepts a list
     *  to be populated with possible corrections.
     *  
     *  String word - provides the misspelled word to 
     *   to try and provide corrections for.
     *  
     *  Return void - The results will be added to the 
     *    list a return is not neccesary.
     *    
     *  Description - This method first uses the omission method 
     *  then feeds the result to omission, swap, singleLetter, and addLetter
     */
    public void omissionAll(LinkedList<String> corrections, String wrongWord)
    {
        // Omission
        for(int i = 0; i < wrongWord.length(); i++)
        {
            String testWord = wrongWord.substring(0,i) + wrongWord.substring(i+1);
            omission(corrections, testWord);
            swap(corrections, testWord);
            singleLetter(corrections, testWord);
            addLetter(corrections, testWord);
        }
    }
    
    /*
     * Swap All
     * LinkedList<String> corrections - accepts a list
     *  to be populated with possible corrections.
     *  
     *  String word - provides the misspelled word to 
     *   to try and provide corrections for.
     *  
     *  Return void - The results will be added to the 
     *    list a return is not neccesary.
     *    
     *  Description - This method first uses the swap method 
     *  then feeds the result to omission, swap, singleLetter, and addLetter
     */
    public void swapAll(LinkedList<String> corrections, String wrongWord)
    {
        for(int i = 0; i+1 < wrongWord.length(); i++)
        {
            String testWord = wrongWord.substring(0,i) + wrongWord.charAt(i+1) + wrongWord.charAt(i) + wrongWord.substring(i+2);
            omission(corrections, testWord);
            swap(corrections, testWord);
            singleLetter(corrections, testWord);
            addLetter(corrections, testWord);
        }
    }
    
    /*
     * single Letter  All
     * LinkedList<String> corrections - accepts a list
     *  to be populated with possible corrections.
     *  
     *  String word - provides the misspelled word to 
     *   to try and provide corrections for.
     *  
     *  Return void - The results will be added to the 
     *    list a return is not neccesary.
     *    
     *  Description - This method first uses the singleLetter method 
     *  then feeds the result to omission, swap, singleLetter and addLetter
     */
    public void singleLetterAll(LinkedList<String> corrections, String wrongWord)
    {
        for(int i = 0; i < wrongWord.length(); i++)
        {
            String testWord;
            for(int a = 'a'; a < 'z'; a++)
            {
                testWord = wrongWord.substring(0,i) + ((char) a) + wrongWord.substring(i+1);
                omission(corrections, testWord);
                swap(corrections, testWord);
                singleLetter(corrections, testWord);
                addLetter(corrections, testWord);
            }
        }
    }
    
    /*
     * all Letter  All
     * LinkedList<String> corrections - accepts a list
     *  to be populated with possible corrections.
     *  
     *  String word - provides the misspelled word to 
     *   to try and provide corrections for.
     *  
     *  Return void - The results will be added to the 
     *    list a return is not neccesary.
     *    
     *  Description - This method first uses the addLetter method 
     *  then feeds the result to omission, swap, singleLetter and addLetter
     */
    public void addLetterAll(LinkedList<String> corrections, String wrongWord)
    {
        for(int i = 0; i < wrongWord.length(); i++)
        {
            String testWord;
            for(int a = 'a'; a < 'z'; a++)
            {
                testWord = wrongWord.substring(0,i) + ((char) a) + wrongWord.substring(i);
                omission(corrections, testWord);
                swap(corrections, testWord);
                singleLetter(corrections, testWord);
                addLetter(corrections, testWord);
            }
        }
    }
    
    public double score(String word, String wrongWord)
    {
        double score = 0;
        for(int i = 0; i < Math.min(word.length(), wrongWord.length()); i++)
        {
            if(word.charAt(i) == wrongWord.charAt(i))
            {
                score++;
            }
        }
        return score/wrongWord.length();
    }
    
    public LinkedList<CorrectedWord> processCorrections(LinkedList<String> corrections, String wrongWord)
    {
        LinkedList<CorrectedWord> sortedScored = new LinkedList<CorrectedWord>();
        // converted linked list to set to remove duplicates
        Set<String> set = new HashSet<String>(corrections);
        // add each item with score to linked list with score and word
        for(String s : set)
        {
            double score = score(s, wrongWord);
            sortedScored.add(new CorrectedWord(score, s));
        }
        
        //sort the list
        Collections.sort(sortedScored);
        return sortedScored;
    }
    
    public LinkedList<CorrectedWord> correct(String wrongWord)
    {
        LinkedList<String> corrections = new LinkedList<String>();
        LinkedList<CorrectedWord> sortedScored = new LinkedList<CorrectedWord>();
        
        // Omission and Swap methods require the word to
        // of at least length 2
        if(wrongWord.length() > 1)
        {
            omission(corrections, wrongWord);
            swap(corrections, wrongWord);
        }
        singleLetter(corrections, wrongWord);
        addLetter(corrections, wrongWord);
        suggest1(corrections, wrongWord);
        suggest2(corrections, wrongWord);
        suggest3(corrections, wrongWord);
        
        // At this point initial checks are over if at least one suggestion was found return that list now. 
        // if not test a combination of our methods on the idea that a user swapped two letters and omitted
        // another.
        if(wrongWord.length() > 1)
        {
            omissionAll(corrections, wrongWord);
            swapAll(corrections, wrongWord);
            singleLetterAll(corrections, wrongWord);
            addLetterAll(corrections, wrongWord);
        }
        
        sortedScored = processCorrections(corrections, wrongWord);
        
        // Process the linked list
        // Need to sort by most likely to be correct and omit duplicate suggestions
        return sortedScored;
    }
    
    // Version 0.3 change return from String to void, add param LL corrections
    // removed param ABCTrie
    public void suggest1(LinkedList<String> corrections, String wrongWord)
    {
        ABCTrie currentNode = root;
        wrongWord = wrongWord.toUpperCase();
        for(int i = 0; i < wrongWord.length(); i++)
        {
            char currentChar = wrongWord.charAt(i);
            if(currentNode.hasChild(currentChar))
            {
                currentNode = currentNode.getChild(currentChar);
            }
        }
        String suggestion = "";
        while(currentNode.getParent() != null)
        {
            suggestion = currentNode.getData() + suggestion.toString();
            currentNode = currentNode.getParent();
        }
        corrections.add(suggestion.toLowerCase());
    }
    
    public void suggest2(LinkedList<String> corrections, String wrongWord)
    {
        ABCTrie currentNode = root;
        wrongWord = wrongWord.toUpperCase();
        for(int i = 0; i < wrongWord.length(); i++)
        {
            char currentChar = wrongWord.charAt(i);
            if(currentNode.hasChild(currentChar))
            {
                currentNode = currentNode.getChild(currentChar);
            } else {
                for(int j = 0; j < 26; j++)
                {
                    if(currentNode.hasChild((char)(j+65)))
                    {
                        currentNode = currentNode.getChild((char)(j+65));
                        break;
                    }
                }
            }
        }
        String suggestion = "";
        while(currentNode.getParent() != null)
        {
            suggestion = currentNode.getData() + suggestion.toString();
            currentNode = currentNode.getParent();
        }
        corrections.add(suggestion.toLowerCase());
    }
    
    public void suggest3(LinkedList<String> corrections, String word)
        {
            ArrayList<String> maybe = new ArrayList<String>();
            
            for(int i=0; i < word.length(); i++) 
                {
                    maybe.add(word.substring(0, i) + word.substring(i+1));
                }
               
            for(int i=0; i < word.length()-1; i++) 
                {
                    maybe.add(word.substring(0, i) + word.substring(i+1, i+2) + word.substring(i, i+1) + word.substring(i+2));
                }
              
            for(int i=0; i < word.length(); i++) 
                {
                    for(char letter='a'; letter <= 'z'; letter++) 
                        {
                            maybe.add(word.substring(0, i) + String.valueOf(letter) + word.substring(i+1));
                        }
                }
                
            for(int i=0; i <= word.length(); i++) 
                {
                    for(char letter='a'; letter <= 'z'; letter++)
                        {
                            maybe.add(word.substring(0, i) + String.valueOf(letter) + word.substring(i)); 
                        }
                 }
             
            for(int i = 0; i < maybe.size(); i++)
                {
                if(!root.hasWord(maybe.get(i)))
                    {
                        maybe.remove(i);
                    }
                }
                 
            for(int i = 0; i < maybe.size(); i++)
                {
                if(root.hasWord(maybe.get(i)))
                    {
                        corrections.add(maybe.get(i).toLowerCase());
                    }
                }
        }
}
