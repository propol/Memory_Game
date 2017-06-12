package memory_game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author 2513 - 2539
 * 
 * Implements the 5th game mode as given. Has exactly the same body as the Multi_Player class (without the "AI scenario" ) adding the creation and actionlistener of an "End Turn" button.
 * When one of the players has flipped only one card in their turn, he may hit the "End Turn" button to end his turn and flip over again the card without opening a second one.
 *
 * All params have previously been explained therefore they are not explained here.
 */
public class Multi_Player_EndTurnEdition extends JFrame
{
    private String Input;
    
    private String SecondInput;
    
    private int j=1;
    
    private JTextField text;
    
    private JLabel label;
    
    private int count = 0;
    
    private Button_Cards selectedCard;
    
    private Button_Cards c1;
    
    private Button_Cards c2;
    
    private Timer timer;
    
   // private ArrayList<JButton> buttons;
    
    private ArrayList<Button_Cards> cards;
    
    private HashMap<Integer, String> hs = new HashMap<Integer, String>();
    
    private ArrayList<Integer> ID;
    
    
    JPanel panel = new JPanel(new FlowLayout());
    JPanel panel2 = new JPanel (new GridLayout(4,6,7,7));
    
    public void NumberOfPlayers()
    {
        Input=JOptionPane.showInputDialog("Type the number of players", JOptionPane.YES_NO_OPTION);
        
        
        if(Input!=null)
        {
            int j = Converter(Input);   
            
            Start(j);
        }
       
       
    }
    
    public int Converter(String string)
    {
        
        if(string.equals("2"))
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
    
    public void Start(int k)
    {
        
        
        panel2.setSize(300,500);
        
        this.setLayout(new BorderLayout());
        
        
        panel.setSize(100, 500);
        
        label = new JLabel("Player 1");
        
        
        JButton button = new JButton("End Turn");
        
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if (c1 != null && c1 == selectedCard && c2 == null)
                {
                    c1.setIcon(new ImageIcon(getClass().getResource("cardBack2.jpg")));
                    
                    c1=null;
                    
                    setLabel();
                }
        
            }
        }); 
        
        
        panel.add(label);
        
        
        panel.add(button);
        
        
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
                JOptionPane.showMessageDialog(this, "Great! Game is over in " + count + " moves!");
                System.exit(0);
            }  
        }

        else
        {
            c1.setIcon(new ImageIcon(getClass().getResource("cardBack2.jpg")));
            c2.setIcon(new ImageIcon(getClass().getResource("cardBack2.jpg")));
            
            setLabel();
            
            //Swap(c1, c2);
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
