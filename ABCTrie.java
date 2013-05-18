
/**
 * Write a description of class ABCTrie here.
 * 
 * @author Andrew Hood
 * @version 0.1
 */
public class ABCTrie
{
    // instance variables
    private ABCTrie parent;
    private char data;
    private ABCTrie[] children;
    private boolean isTermChar; // Is this the terminating character of the word.

    /**
     * Constructor for objects of class ABCTrie
     */
    public ABCTrie(ABCTrie parent, char data)
    {
        this.parent = parent;
        this.data = data;
        children = new ABCTrie[26];
        isTermChar = false;
    }
    
    public ABCTrie()
    {
        children = new ABCTrie[26];
    }
    
    public boolean hasChild(char c)
    {
        int index = c - 65;
        return children[index] != null;
    }
    
    public boolean hasWord(String word)
    {
        ABCTrie currentNode = this;
        String currentWord = word.toUpperCase();
        for(int i = 0; i < currentWord.length(); i++)
        {
            char currentChar = currentWord.charAt(i);
            if(currentNode.hasChild(currentChar))
            {
                currentNode = currentNode.getChild(currentChar);
            } else {
                return false;
            }
        }
        return currentNode.getTermChar();
    }
    
    public ABCTrie getChild(char c)
    {
        int index = c - 65;
        return children[index];
    }
   
    public void addChild(char c)
    {
        int index = c - 65;
        children[index] = new ABCTrie(this, c);
    }
    
    public char getData()
    {
        return data;
    }
    
    public ABCTrie getParent()
    {
        return parent;
    }
    
    public boolean getTermChar()
    {
        return isTermChar;
    }
    
    public void setTerminating()
    {
        this.isTermChar = true;
    }
    
}
