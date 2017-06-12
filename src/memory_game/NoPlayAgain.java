package memory_game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.State.TERMINATED;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author 2539 - 2513
 */
/**
 * 
 * Implements the first game mode as given. Has the same body as Multi_Player with the addition of one setLabel() when two cards are matched.
 */
public class NoPlayAgain extends JFrame
{
    Thread t1;
    
    Thread t2;
    
    Thread t3;
    
    private String Input;
    
    private String SecondInput;
    
    private int j=1;
    
    private JLabel label;
    
    private int count = 0;
    
    private Button_Cards selectedCard;
    
    private Button_Cards c1;
    
    private Button_Cards c2;
    
    private Timer timer;
    
    private ArrayList<Button_Cards> cards;
    
    private HashMap<Integer, String> hs = new HashMap<Integer, String>();
    
    private ArrayList<Integer> ID;
    
    
    JPanel panel = new JPanel(new FlowLayout());
    JPanel panel2 = new JPanel (new GridLayout(4,6,7,7));
    
    
   /**
    * Empty Constructor of the class.
    */ 
    public NoPlayAgain()
    {
        
    }
    
    /**
     * Method that indicates the beginning of this mode. A dialog box asks for the number of players and another dialog box
     * asks for the number of AI players participating. Outputs error message if AI players are equal or more than the given number of players.
     * Calls method Start() to start the actual game mode.
     */
    
    public void NumberOfPlayers()
    {
        Input=JOptionPane.showInputDialog("Type the number of players", JOptionPane.YES_NO_OPTION);
        
        SecondInput = JOptionPane.showInputDialog("How many of those "+ Input +" players will be AI ?", JOptionPane.YES_NO_OPTION);
        
        while(Converter(SecondInput) >= Converter(Input))
        {
            if(Converter(SecondInput) >= Converter(Input))
            {
                JOptionPane.showMessageDialog(this,"AI players can't be equal or more than the original number of players!",
                "ERROR",
                JOptionPane.ERROR_MESSAGE);

                SecondInput = JOptionPane.showInputDialog("How many of those "+ Input +" players will be AI ?", JOptionPane.YES_NO_OPTION);
            }
        
        }
        
        if(Input!=null)
        {
            int j = Converter(Input);   
            
            Start();
        }
       
    }
    
    /**
     * Converts the String returned from the dialog boxes into the number matched.
     * 
     * @param string The String stored either in Input or SecondInput
     * @return an Integer with the value the string represents.
     */
    
    public int Converter(String string)
    {
        if(string.equals("0"))
            return 0;
        else
            if(string.equals("1"))
                return 1;
        else if(string.equals("2"))
        {
            return 2;
        }else if(string.equals("3"))
        {
            return 3;
        }else if(string.equals("4"))
        {
            return 4;
        }
        
        return 1;
    }
    
    /**
     * Method used to change the text of the label displayed on top of the frame when this game mode is on.
     * Integer i holds the number returned from Converter(Input), that indicates the number of players.
     * Integer j is set to 1 every time "Player 1" is on and is increased when another player is on, helping the method's procedure.
     * 
     */
    public void setLabel()
    {
        int i = Converter(Input);
        
        if(j<i && label.getText().equals("Player 1"))
        {
            j++;
            label.setText("Player "+j);
        
        }else if(j<i && label.getText().equals("Player 2"))
        {
            j++;
            label.setText("Player "+j);
        }else if(j<i && label.getText().equals("Player 3"))
        {
            j++;
            label.setText("Player "+j);
        }else if(j==i && label.getText().equals("Player "+j))
        {
            j=1;
            label.setText("Player "+j);
        }
        
        
    }
    
    /**
     * 
     * Indicates the beginning of this game mode when called (almost acts as a Constructor).
     * Has the same body as the constructor of the Swap_Mode class except the initialization and forming of the label, layouts and borders used in this mode
     * as well as the scenario where some of the players are AI.
     */
    
    public void Start()
    {
        
        
        panel2.setSize(300,500);
        
        this.setLayout(new BorderLayout());
        
        
        panel.setSize(100, 500);
        
        label = new JLabel("Player 1");
        
        
        panel.add(label);
        
        
        Border compound = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder());
        panel.setBorder(compound);
        //panel2.setBorder(compound);
        
        
        
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
            panel2.add(c);
        }
        
        this.add(panel,BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
    }
    
    /**
     * Is the same method as the one in Swap_Mode (and other classes) except the "AI scenario" part.
     * Also checks if any number of AI players between one and 3 is given. If so, initializes and calls the Thread applied for each one of the AI players
     * when it is their turn to play.
     */
    
    public void Check()
    {
        if (c1.getId() == c2.getId())
        {
            c1.setEnabled(false); 
            c2.setEnabled(false);
            
            c1.setMatched(true); 
            c2.setMatched(true);
            
            setLabel();
           
            
            if (this.isGameWon())
            {
                JOptionPane.showMessageDialog(this, "Great! Game is over in " + count + " moves!");
                System.exit(0);
            }  
        }

        else
        {
            c1.setIcon(new ImageIcon(getClass().getResource("cardBack2.jpg")));
            c2.setIcon(new ImageIcon(getClass().getResource("cardBack2.jpg")));
            
            setLabel();
            
            
            if(Converter(SecondInput)!=0)
            {
                if(Converter(SecondInput)==1 && label.getText().equals("Player 2"))
                {
                    t1 = new Thread(new AI("Player 2", cards));
                    
                    t1.start();
                }
                
                if(Converter(SecondInput)==2 && (label.getText().equals("Player 2") || label.getText().equals("Player 3")))
                {
                    t1 = new Thread(new AI("Player 2", cards));
                    
                    t1.start();
                    
                    t2 = new Thread(new AI("Player 3", cards));
                    
                    t2.start();
                }
                
                if(Converter(SecondInput)==3 && (label.getText().equals("Player 2") || label.getText().equals("Player 3") || label.getText().equals("Player 4")))
                {
                    t1 = new Thread(new AI("Player 2", cards));
                    
                    t1.start();
                    
                    t2 = new Thread(new AI("Player 3", cards));
                    
                    t2.start();
                    
                    t3 = new Thread(new AI("Player 3", cards));
                    
                    t3.start();
                }
            }
            
        }
        c1 = null;
        c2 = null;
    }
    
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
}
