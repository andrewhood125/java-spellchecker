
/**
 * This class will hold a string and point value 
 * based on the resemblance to the original word
 * 
 * @author Andrew Hood
 * @version 0.1
 */

import java.text.DecimalFormat;

public class CorrectedWord implements Comparable<CorrectedWord>
{
    private double score;
    private String word;
    
    public CorrectedWord(double score, String word)
    {
        this.score = score;
        this.word = word;
    }
    
    public double getScore()
    {
        return score;
    }
    
    public String getWord()
    {
        return word;
    }
    
    public String toString()
    {
        double formattedNumber = Double.parseDouble(new DecimalFormat("#.##").format(score));
        return "[Score: " + formattedNumber + ", Word: " + word + "]";
    }
    
    public int compareTo(CorrectedWord o)
    {
        if(this.getScore() > o.getScore())
        {
            return -1;
        } else if(this.getScore() < o.getScore() ) {
            return 1;
        } else {
            return 0;
        }
    }
}
