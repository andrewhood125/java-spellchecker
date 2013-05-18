
/**
 * Write a description of class SpellCheck here.
 * 
 * @author Andrew Hood
 * @version 0.1
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;

public class SpellCheck
{
    public static void main(String[] args)
    {
        ABCTrie root = new ABCTrie();
        LoadText.loadWordlist(root);
        Suggest suggest = new Suggest(root);
        String filename = "spelling.txt";
        File file = new File(filename);
        try
        {
            Scanner input = new Scanner(file);
            while(input.hasNext())
            {
                String[] words = input.nextLine().split(" ");
                for(int i = 0; i < words.length; i++)
                {
                    String currentWord = words[i];
                    if(root.hasWord(currentWord))
                    {
                        System.out.print(currentWord + " ");
                    } else {
                        System.out.print("(" + currentWord + ") ");
                        LinkedList<CorrectedWord> suggestions = suggest.correct(currentWord);
                        ListIterator<CorrectedWord> iter = suggestions.listIterator();
                        while(iter.hasNext())
                        {
                            System.out.print(iter.next());
                        }
                    }
                }
                System.out.println();
            }
        } catch(FileNotFoundException ex)
        {
            System.err.println(ex.getMessage());
        }
        
    }
}
