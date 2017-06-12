package memory_game;

import javax.swing.Icon;

/**
 *
 * @author 2513 - 2539
 */

/**
 * Implements the cards used during the game.
 * Each object of this class is a different card used throughout the game.
 * 
 * @param icon The picture that the player sees when turning a card 
 * @param id Two cards with the same icon have the same ids, used to see when a pair of cards is matched
 * @param priority Used only for the Priority_Mode class, when the game only allows to open pairs in a specified order
 * 
 */

public class Cards 
{
    Icon icon;
    int id;
    int priority;
    
    /**
     * First Constructor of this Class
     */
    public Cards(Icon icon, int id)
    {
        this.icon=icon;
        this.id=id;
        priority=0;
    }
    
    /**
     * Second Constructor of this Class
     */
    
    public Cards(Icon icon, int id, int priority){
        this.icon=icon;
        this.id=id;
        this.priority=priority;
    }
    
    /**
     * 
     * @return The icon of the Cards object
     */
    public Icon getIcon(){
        return icon;
    }
    
    /**
     * 
     * Sets the icon on the Cards object
     */
    public void setIcon(Icon icon){
        this.icon=icon;
    }
    
    /**
     * 
     * @return The ID of the Cards object
     */
    public int getID(){
        return id;
    }
    
    /**
     * 
     * Sets the ID of the Cards object 
     */
    public void setID(int id){
        this.id=id;
    }
    
    /**
     * 
     * @return The priority of the Card object to be opened 
     */
    public int getPriority(){
        return priority;
    }
}
