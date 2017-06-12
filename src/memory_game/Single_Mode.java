package memory_game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static jdk.nashorn.internal.objects.NativeDebug.getClass;

/**
 *
 * @author 2513 - 2539
 */
/**
 * 
 * Implements the simple mode of the game, where a player plays by himself.
 * 
 * All params have been previously explained in Swap_Mode class.
 * 
 * This class has exactly the same "body" as the Swap_Mode class except for the Swap() method which is found only in Swap_Mode.
 * All methods have already been explained therefore the explanations are not repeated here.
 */
public class Single_Mode extends JFrame
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
    
    
    public Single_Mode()
    {
        super("Single Mode");
        
        setLayout(new GridLayout(4,6,7,7));
        
       // buttons= new ArrayList<JButton>(24);
        
        cards = new ArrayList<Button_Cards>();
        
        ID = new ArrayList<Integer>();
        
        hs = new HashMap<Integer, String>();
       
      
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
        icon = new ImageIcon(getClass().getResource("cardBack2.jpg"));
        
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
            
            //Swap(c1, c2);
        }
        c1 = null;
        c2 = null;
    }
    
    /*public void Swap(Cards c1, Cards c2)
    {
        int temp = c1.getId();
        
        c1.setId(c2.getId());
        
        c2.setId(temp);
    }*/
    
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
    
    /*public void test()
    {
        for(JButton b : buttons)
            {
               System.out.println(b.getIcon().toString());
                
            }*/
    }  


