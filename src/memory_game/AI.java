package memory_game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author 2513 - 2539
 * 
 * Implements the "Goldfish" AI level as given. This class implements Runnable so as it can be called via thread when needed
 * 
 * @param i Integer that takes a random value when the AI opens the first card on its turn.
 * @param k Integer that takes a random value when the AI opens the second card on its turn.
 * @param count Integer that helps finish the loop necessary for finding a non-matched card to open for the first card each turn.
 * @param count_2 Does the same thing as count, for the second card during each of the AI's turns.
 * @param icon ImageIcon that represents the image displayed when the cards are not flipped.
 * 
 */
public class AI implements Runnable
{
    
    private int i;
    
    private int k;
    
    private int count;
    
    private int count_2;
    
    private Button_Cards selectedCard;
    
    private String Title;
    
    private ArrayList<Button_Cards> cards;
    
    private Icon icon = new ImageIcon(getClass().getResource("cardBack2.jpg"));
    
    /**
     * Constructor of the AI class. Sets param c as the given ArrayList<Button_Cards> that holds the cards displayed (ArrayList cards has already been explained)
     * 
     * 
     * @param aTitle
     * @param c a hold of the given ArrayList<Button_cards> when an AI object is created
     */
    
    public AI(String aTitle, ArrayList<Button_Cards> c)
    {
        this.Title=aTitle;
        this.cards = c;
    }
    
    /**
     * Implements the run method of the Runnable AI class.
     * Loops until a random, non matched card is found, sets it as the selectedCard and performs a click.
     * Loops again until a random, non matched and different from the first, card is found, and performs a click on it as well.
     */
    
    @Override
    public void run() 
    {
        Random random = new Random();
        
        do
        {
            count=0;
            
            i=random.nextInt(12);
        
            for(Button_Cards c : cards)
            {

                if(c.getId()==i && !c.getMatched())
                {
                   
                    selectedCard=c;
                    
                    count++;
                    
                    c.doClick();
                    break;
                }
  
            }
        
        }while(count==0);
       
        
        do
        {
            count_2=0;
        
            k=random.nextInt(12);
            
            for(Button_Cards b : cards)
            {   
            
                if(b.getId()==k && b.getMatched()==false && b!=selectedCard)
                {
                    count_2++;
                    
                    b.doClick();
                    break;
                }
       
            }
        
        }while(count_2==0);
        
        
        
    }
    
}
