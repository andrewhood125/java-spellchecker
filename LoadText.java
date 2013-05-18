/**
 * Write a description of class LoadWordlist here.
 * 
 * @author Andrew Hood
 * @version 0.1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadText
{
    
    public static void loadWordlist(ABCTrie rootNode)
    {
        String filename = "wordlist.txt";
        File file = new File(filename);
        
        try
        {
            Scanner input = new Scanner(file);
            while(input.hasNext())
            {
                ABCTrie currentNode = rootNode;
                String word = input.nextLine().trim().toUpperCase();
                int i;
                for(i = 0; i < word.length(); i++)
                {
                    char c = word.charAt(i);
                    if(currentNode.hasChild(c))
                    {
                        currentNode = currentNode.getChild(c);
                    } else {
                        currentNode.addChild(c);
                        currentNode = currentNode.getChild(c);
                    }
                    if(i+1 >= word.length())
                    {
                        currentNode.setTerminating();
                    }
                }
            }
        } catch(FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
