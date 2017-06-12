package memory_game;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author 2513 - 2539
 */
/**
 * 
 * Implements cards used during the game. 
 * Each object of this class is a different card, as it extends JButton
 * 
 * @param id Integer used to mark the icons of the cards. Cards with the same id will have the same icon applied to them when clicked.
 * @param isMatched boolean used to mark the cards. If two cards with the same icons are flipped, isMatched is set true for both. Remains false otherwise.
 * @param hasBeenSelected boolean used to mark the cards for the AI memory only. If a card is clicked, hasBeenSelected is set true. Remains false otherwise.
 * 
 */

public class Button_Cards extends JButton
{
    //private Icon icon;
    
    private int id;
    
    private boolean isMatched = false;
    
    private boolean hasBeenSelected = false;
    
    
    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }


    public void setMatched(boolean matched){
        this.isMatched = matched;
    }

    
    public boolean getMatched(){
        return this.isMatched;
    }
    
    public void setHasBeenSelected()
    {
        System.out.println(id);
        hasBeenSelected = true;
    }
    
    public boolean getHasBeenSelected()
    {
        return hasBeenSelected;
    }
}
