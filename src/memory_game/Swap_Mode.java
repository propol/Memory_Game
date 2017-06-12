package memory_game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author 2513 - 2539
 */
/**
 * 
 * Implements the Swap Mode of the game (function 6 as given) where cards swap places
 * if they are not matched
 * 
 * @param count Counts the moves done until the game is over. Displayed when game is finished.
 * @param selectedCard Button_Cards object that indicates witch card is opened every time.
 * @param c1 temporary Button_Cards object which "holds" the first card opened every turn so it can be used in various Methods.
 * @param c2 same task as c1, for the second card opened every turn.
 * @param timer timer used to delay the start of each turn and check if cards are matched or not.
 * @param cards ArrayList of Button_Cards objects used to access all buttons (cards) when needed.
 * @param hs HashMap of Integers and Strings used to apply an icon to every unique card.
 * @param ID ArrayList of Integers used to apply integers to every card, necessary for numerous methods.
 */
public class Swap_Mode extends JFrame
{
    private int count = 0;
    
    private Button_Cards selectedCard;
    
    private Button_Cards c1;
    
    private Button_Cards c2;
    
    private Timer timer;
    
   // private ArrayList<JButton> buttons;
    
    private ArrayList<Button_Cards> cards;
    
    private HashMap<Integer, String> hs = new HashMap<Integer, String>();
    
    private ArrayList<Integer> ID;
    
   /**
    * Constructor. Initializes all Lists and Maps, applying images to every card and creates the frame for this game mode.
    */
    
    public Swap_Mode()
    {
        super("Swap Mode");
        
        setLayout(new GridLayout(4,6,7,7));
        
       // buttons= new ArrayList<JButton>(24);
        
        cards = new ArrayList<Button_Cards>();
        
        ID = new ArrayList<Integer>();
        
        hs = new HashMap<Integer, String>();
        
     /*   private String[] icons={"bob.png", "carl.jpg","dog.jpg","dude.jpg","fat.jpg",
        "hood.jpg","pokerFace.jpg","prettyOne.jpg","sad_Lary.jpg","stickMan.jpg",
        "skil.jpg","tsour.jpg","bob.png", "carl.jpg","dog.jpg","dude.jpg","fat.jpg",
        "hood.jpg","pokerFace.jpg","prettyOne.jpg","sad_Lary.jpg","stickMan.jpg",
        "skil.jpg","tsour.jpg"};  */
      
        hs.put(0, "bob.png");
        hs.put(1, "carl.jpg");
        hs.put(2, "dog.jpg");
        hs.put(3, "dude.jpg");
        hs.put(4, "fat.jpg");
        hs.put(5, "hood.jpg");
        hs.put(6, "pokerFace.jpg");
        hs.put(7, "prettyOne.jpg");
        hs.put(8, "sad_Lary.jpg");
        hs.put(9, "stickMan.jpg");
        hs.put(10, "skil.jpg");
        hs.put(11, "tsour.jpg");
        
        for (int j =0;j<12; j++)
        {
            ID.add(j);
            ID.add(j);
        }
        
        Collections.shuffle(ID);
        
        ImageIcon icon  ;
        icon = new ImageIcon(getClass().getResource("cardBack2.png"));
        
        for (int i : ID)
        {
            Button_Cards c = new Button_Cards();
            
            c.setId(i);
            
            c.setIcon(icon);
            
            c.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    selectedCard = c ; 
                    TurnCard();
                }
            });
            
            cards.add(c);
        }
        
        timer = new Timer(500, new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                Check();
            }
        });
        
        timer.setRepeats(false);
        
        for (Button_Cards c : cards)
        {
            add(c);
        }
    }
    
    /**
     *  Checks if cards are matched (have the same icon). If so, sets them out of use, else, sets the ImageIcon to the initial one and swaps cards.
     *  Also checks if the game is over.
     */
    
    public void Check()
    {
        if (c1.getId() == c2.getId())
        {
            c1.setEnabled(false); 
            c2.setEnabled(false);
            
            c1.setMatched(true); 
            c2.setMatched(true);
            
            if (this.isGameWon())
            {
                JOptionPane.showMessageDialog(this, "You win with " + count + " moves!");
                System.exit(0);
            }  
        }

        else
        {
            c1.setIcon(new ImageIcon(getClass().getResource("cardBack2.png")));
            c2.setIcon(new ImageIcon(getClass().getResource("cardBack2.png")));
            
            Swap(c1, c2);
        }
        c1 = null;
        c2 = null;
    }
    
    /**
     * Method used to swap images if not matched.
     * 
     * @param c1 Already explained
     * @param c2 Already explained
     */
    
    public void Swap(Button_Cards c1, Button_Cards c2)
    {
        int temp = c1.getId();
        
        c1.setId(c2.getId());
        
        c2.setId(temp);
    }
    
    /**
     * Method used to "turn" a card over after it is clicked. Sets c1 (c2) as the selectedCard when clicked and sets their applied icon.
     * When the second card is flipped, increases the counter displaying the moves done by one and starts the timer.
     */
    
    public void TurnCard()
    {
        if (c1 == null && c2 == null){
            c1 = selectedCard;
            //ImageIcon 
            c1.setIcon(new ImageIcon(getClass().getResource(hs.get(c1.getId()))));
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setIcon(new ImageIcon(getClass().getResource(hs.get(c2.getId()))));
            
            count++;
            
            timer.start();

        }
    }
    
    /**
     * Checks if the game is over. If every card is marked as "matched", indicates the end of the game.
     * 
     * @return true if every card has been matched, false otherwise.
     */
    
    public boolean isGameWon()
    {
        for(Button_Cards c: this.cards)
        {
            if (c.getMatched() == false)
            {
                return false;
            }
        }
        return true;
    
    }
    
}