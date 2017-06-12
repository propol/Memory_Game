package memory_game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author 2513 - 2539
 */
/**
 * 
 * Implements the Duel mode of the game, game mode 8 as given.
 * Two panels are added in the frame, each one containing one copy of the cards. 
 * Each of the two players flips one of their cards at a time. If the one who goes second matches the cards, gets a point.
 * The playing turn is altered after each turn.
 * 
 * @param label JLabel that displays the match score in the center of the frame.
 * @param count1 Integer that holds the score for Player 1.
 * @param count2 Integer that holds the score for player 2.
 * @param goesFirst boolean that indicates which player goes first at a time. True for Player 1, false for Player 2.
 * @param panel1 JPanel that holds the cards for player 1
 * @param panel2 JPanel that holds the cards for player 2
 * @param cards2 ArrayList<Button_Cards> used to fill the second panel with cards.
 * 
 * The remaining params have already been explained in previous classes therefore they are not explained here.
 */
public class Duel extends JFrame
{
    private JLabel label;
    
    private int count1=0;
    private int count2=0;
    
    //private int count=0;
    
    private boolean goesFirst = true;
    
    private JPanel panel1;
    private JPanel panel2;
    
    private Button_Cards c1;
    
    private Button_Cards c2;
    
    private Timer timer;
    
    private ArrayList<Button_Cards> cards;
    
    ArrayList <Button_Cards> cards2 ;
    
    private HashMap<Integer, String> hs = new HashMap<Integer, String>();
    
    private ArrayList<Integer> ID;
    
    private Button_Cards selectedCard;
    
   /**
    * Constructor of the class, Initializes params and creates the panels and frame for the mode.
    * Also initializes and fills ArrayList cards2 to fill the second panel, as a Gui form cannot be contained in more than one containers.
    * When Player 1 is on, player's 2 panel is disabled so that no mistakes are made.
    * When a player clicks one of his cards, the rest of them are disabled to prevent mistakes and the other player's cards are enabled for his turn.
    * 
    */
    
    public Duel()
    {
        super("Duel Mode");
        
        Font font = new Font("Serif", BOLD , 50);
        
        label = new JLabel("                          "
         + count1 + " : " + count2);
        
        label.setFont(font);
        
        panel1 = new JPanel();
        panel2 = new JPanel();
       
        
        panel1.setLayout(new GridLayout(4,6,7,7));
        panel2.setLayout(new GridLayout(4,6,7,7));
        
        setExtendedState(MAXIMIZED_BOTH);
        
        this.setSize(500,500);
       
        
        this.setLayout(new BorderLayout());
      
        
        this.setVisible(true);
      
        
        this.setLocationRelativeTo(null);
      
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     
        
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
          
        }
        
        Collections.shuffle(ID);
        
        ImageIcon icon  ;
        icon = new ImageIcon(getClass().getResource("cardback2.jpg"));
        
        for (int i : ID)
        {
            Button_Cards c = new Button_Cards();
            
            c.setId(i);
            
            c.setIcon(icon);
            
            c.setEnabled(false);
            
            c.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    selectedCard = c ; 
                    TurnCard();
                }
            });
            
            cards.add(c);
        }
        
        Collections.shuffle(ID);
        
        cards2 = new ArrayList<Button_Cards>();
        
        for (int i : ID)
        {
            Button_Cards c = new Button_Cards();
            
            c.setId(i);
            
            c.setIcon(icon);
            
            c.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    selectedCard = c ; 
                    
                    c.setHasBeenSelected();
                    
                    TurnCard();
                }
            });
            
            cards2.add(c);
            
        }
        
        
        
        
        
        timer = new Timer(500, new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                
                Check();
  
                if(goesFirst)
                {
                    for(Button_Cards c : cards)
                        c.setEnabled(false);
                    
                    for(Button_Cards c : cards2)
                    {
                        if(c.getMatched()==false)
                            c.setEnabled(true);
                    }
                    
                }else
                {
                    for(Button_Cards c : cards2)
                        c.setEnabled(false);
                    
                    for(Button_Cards c : cards)
                    {
                        if(c.getMatched()==false)
                            c.setEnabled(true);
                    }
                    
                }
                
                label.setText("                          "
         + count1 + " : " + count2);
                
                
            }
            
        });
        
        timer.setRepeats(false);
        
        for (Button_Cards c : cards)
        {
            panel1.add(c);
           
        }
        
        
        for (Button_Cards c : cards2)
        {
            //frame1.add(c);
            panel2.add(c);
            
        }
        
     
        add(panel1, BorderLayout.EAST);
        add(panel2, BorderLayout.WEST);
        add(label, BorderLayout.CENTER);
    }
    
    /**
     * Has almost the same body as every Check() method up to now.
     * In either match or no-match, the value of the goesFirst param changes so that the one who goes first is altered every turn.
     * In case of card match, the player for whom the goesFirst param is true gets his counter increased by one.
     * Also, if the game is over, displays the winner and score in dialog box (different dialog box in case of tie).
     */
    
    public void Check()
    {
        if (c1.getId() == c2.getId())
        {
            c1.setEnabled(false); 
            c2.setEnabled(false);
            
            c1.setMatched(true); 
            c2.setMatched(true);
            
            
            if(goesFirst==true)
            {
                count2++;
                
                goesFirst=false;
            }
            else
            {
                count1++;
                
                goesFirst=true;
            }
            
            
            if (this.isGameWon())
            {
                if(count1>count2)
                {
                    JOptionPane.showMessageDialog(this, "Player 1 wins!  "+ count1 + " - "+ count2);
                }else if(count2>count1)
                {
                    JOptionPane.showMessageDialog(this, "Player 2 wins!  "+ count1 + " - "+ count2);
                }else
                {
                    JOptionPane.showMessageDialog(this, "Tie!  "+ count1 + " - "+ count2);
                }
                
                System.exit(0);
            }  
        }

        else
        {
            c1.setIcon(new ImageIcon(getClass().getResource("cardback2.jpg")));
            c2.setIcon(new ImageIcon(getClass().getResource("cardback2.jpg")));
            
            
            if(goesFirst==true)
            {
                goesFirst=false;
            }
            else
            {  
                goesFirst=true;
            }
           
            
        }
        c1 = null;
        c2 = null;
    }
    
    public void TurnCard()
    {
        if (c1 == null && c2 == null)
        {
            c1 = selectedCard;
            //ImageIcon 
            c1.setIcon(new ImageIcon(getClass().getResource(hs.get(c1.getId()))));
            
            if(goesFirst==true)
            {
                for(Button_Cards c : cards2)
                {
                    if(c!=c1)
                    c.setEnabled(false);   
                }
                
                for(Button_Cards c : cards)
                {
                    if(c.getMatched()==false)
                        c.setEnabled(true);   
                }
               
                
            }else
            {
                for(Button_Cards c : cards)
                {
                    if(c!=c1)
                    c.setEnabled(false);
                }
              
                for(Button_Cards c : cards2)
                {
                    if(c.getMatched()==false)
                        c.setEnabled(true);   
                }
                
               
            }
            
            
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setIcon(new ImageIcon(getClass().getResource(hs.get(c2.getId()))));
            
            
            if(goesFirst==true)
            {
                
                for(Button_Cards c : cards)
                {
                    if(c!=c2)
                    c.setEnabled(false);
                }
                
            }else
            {
              for(Button_Cards c : cards2)
                {
                    if(c!=c2)
                        c.setEnabled(false);
                }  
            }
            
           // count++;
            
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
    
    
}
