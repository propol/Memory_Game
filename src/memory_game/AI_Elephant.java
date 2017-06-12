package memory_game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author 2513 - 2539
 */
/**
 * 
 * Implements the Elephant AI level as given. This level "holds in memory" every card being opened, either from human player or AI
 * just as someone spectating the game could have done. 
 * If no cards in its "memory" match and not all cards have been seen, a card that has not been clicked by anyone yet is selected so that its "memory" is filled faster
 * Its "memory" relies on the Cards hasBeenSelected param, which is set true if a card has been clicked by any way, false otherwise.
 * 
 * @param w Integer that takes a random value to open the first card, in case no cards match in memory
 * @param k Integer that takes a random value to open the second card, in case no cards match in memory
 * @param count_3 Integer that helps end the loop for first card open, in case no cards match in memory
 * @param count_4 Integer that helps end the loop for second card open, in case no cards match in memory
 * 
 * The rest of the params have already been explained in AI class
 */
public class AI_Elephant implements Runnable
{
    private Icon icon = new ImageIcon(getClass().getResource("cardBack2.jpg"));
    
   // private int i;
    
    private int w;
    
    private int k;
    
   // private int count;
    
    private int count_3;
    
    private int count_4;

    private Button_Cards selectedCard;
    
    private String Title;
    
    
    private ArrayList<Button_Cards> cards;
    
    
    public AI_Elephant(String aTitle, ArrayList<Button_Cards> c)
    {
        this.Title=aTitle;
        
        this.cards = c;
       
        
      
    }
    
    /**
     * The run method of this Runnable class. Before every card flip, checkAll() method is called to check if there are matched cards in memory.
     * If not, click is performed the same way as in the AI class, avoiding clicking on cards that already have been opened once.
     */
    
    @Override
    public void run() 
    {
        Random random = new Random();
        
           
            if(!checkAll())
            {
            
                
                
                do
                {
                    
                    System.out.println("για πρωτη καρτα");
                    
                    count_3=0;

                    w = random.nextInt(12);
                    
                    
                    
                    for(Button_Cards c : cards)
                    {
                        if(c.getId()==w && !c.getMatched() && !c.getHasBeenSelected())
                        {
                         
                            
                            count_3++;
                            
                            selectedCard=c;
                            
                            c.doClick();
                            
                            break;
                        }
                    }

                }while(count_3==0);    
           
                if(!checkAll())
                {
                    do
                    {
                        
                        count_4=0;

                        k=random.nextInt(12);

                        for(Button_Cards b : cards)
                        {   

                            if(b.getId()==k && b.getMatched()==false && b!=selectedCard && !b.getHasBeenSelected())
                            {
                             
                                count_4++;

                                b.doClick();
                                break;
                            }

                        }

                    }while(count_4==0);
                }
                
            }
        }
       
  /**
   * Method that checks if any cards in "memory" are matched. 
   * Parses through the ArrayList c for every card. If 2 cards that have been opened once have the same id, click is performed on both of them.
   * 
   * @return true if matched cards are "stored in memory", false otherwise.
   */
    
    public boolean checkAll()
    { 
        
      
        for(Button_Cards i : cards)
        {
           
            
            for(Button_Cards j : cards)
            {
                
               
                
                if(i.getId()==j.getId() && i.getHasBeenSelected() && j.getHasBeenSelected() && i.getMatched()==false && j.getMatched()==false && i!=j )
                {
                   
                    i.doClick();
                    
                    j.doClick();
                    
                    return true;
                }
            }
        }
        return false;
    }
}
