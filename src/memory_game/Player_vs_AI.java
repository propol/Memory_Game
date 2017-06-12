package memory_game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.State.NEW;
import static java.lang.Thread.State.RUNNABLE;
import static java.lang.Thread.State.TERMINATED;
import static java.lang.Thread.State.WAITING;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author Jay
 */
public class Player_vs_AI extends JFrame
{
    JPanel panel2 = new JPanel (new GridLayout(4,6,7,7));
    JPanel panel3 = new JPanel(new FlowLayout());
    
    Thread t1;
    
    private int count;
    
    private int count_2;
    
    private int k;
    
    private int i;
    
    private int j=1;
    
    private JTextField text;
    
    private JLabel label;
    
   // private int count = 0;
    
    private Button_Cards selectedCard;
    
    private Button_Cards c1;
    
    private Button_Cards c2;
    
    private Timer timer;
    
   // private ArrayList<JButton> buttons;
    
    private ArrayList<Button_Cards> cards;
    
    private ArrayList<Integer> IDs;
    
    private HashMap<Integer, String> hs = new HashMap<Integer, String>();
    
    private ArrayList<Integer> ID;
    
    JLabel label2 ;
    
    JRadioButton AI = new JRadioButton("AI");
    JRadioButton Human = new JRadioButton("Human");
    
    public void NumOfPlayers()
    {
        JPanel panel = new JPanel();
        
        
        panel.setLayout(new FlowLayout());
        
        JLabel label = new JLabel("Give number of players: ");
        //label.setBounds(200, 50, 300, 50);
        
        text = new JTextField(4);
        //text.setBounds(350, 65, 60, 25);
        
        panel.add(label);
        panel.add(text);
        
        label2 = new JLabel("Player 2 :");
        label2.setVisible(false);
        
        AI.setVisible(false);
        Human.setVisible(false);
        
        JButton b = new JButton("Start game");
        
        b.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                panel.setVisible(false);
                Start(k);
            }

        });
        
        panel.add(label2);
        panel.add(AI);
        panel.add(Human);
        panel.add(b);
        
        text.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(text.getText().equals("2"))
                {
                    //label2 = new JLabel("Player 2 :");
                    label2.setVisible(true);
                    
                    
                    //AI = new JRadioButton("AI");
                    //Human = new JRadioButton("Human");
                    AI.setVisible(true);
                    Human.setVisible(true);
                    
                    ButtonGroup group = new ButtonGroup();
                    
                    group.add(AI);
                    group.add(Human);
                    
                    k = Converter(text.getText());
                }
            }

        });
        
        /*if(AI.isSelected())
        {
            t1 = new Thread(new AI("Player 2", cards));
        }*/
        
        this.add(panel);
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
        int i = Converter(text.getText());
        
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
        
        
        panel3.setSize(100, 500);
        
        label = new JLabel("Player 1");
        
        
        panel3.add(label);
        
        
        Border compound = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder());
        panel3.setBorder(compound);
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
                public void actionPerformed(ActionEvent e)
                {
                    selectedCard = c ; 
                    
                    c.setHasBeenSelected();
                    
                    //IDs.add(c.getId());
                    
                    TurnCard();
                }
            });
            
            cards.add(c);
        }
        
        timer = new Timer(1000, new ActionListener()
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
        
        //t1 = new Thread(new AI_Elephant("Player 2", cards));
        
        this.add(panel3,BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
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
    
    /*synchronized void StartWait(Thread t)
    {
        try {
            while(true) t.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Player_vs_AI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
   // @SuppressWarnings("empty-statement")
    public void Check()
    {
        if (c1.getId() == c2.getId())
        {
            c1.setEnabled(false); 
            c2.setEnabled(false);
            
            c1.setMatched(true); 
            c2.setMatched(true);
            
            if(label.getText().equals("Player 2") && t1.getState()==TERMINATED)
            {
                t1 = new Thread(new AI_Elephant("Player 2", cards));
                t1.start();
                
            }
            
            
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
            
            if(label.getText().equals("Player 2"))
            {
                t1=new Thread(new AI_Elephant("Player 2", cards));
                
                t1.start(); 
                
                
               
            }
               
        }
        
        c1 = null;
        c2 = null;
    }
    
    public void ai()
    {
        Random random = new Random();
        
        do
        {
            count=0;
            
            i=random.nextInt(12);
        
            for(Button_Cards c : cards)
            {
            
                // System.out.println("εξω απο την if "+ i +   " το id της καρτας: " +c.getId());
            
                if(c.getId()==i && !c.getMatched())
                {
                   // System.out.println("το id της πρωτης: "+c.getId());
                    
                   // System.out.println("πρωτη καρτα");
                
                    selectedCard=c;
                    
                    count++;
                    
                    c.doClick();
                    break;
                }
            
            
            }
        
        }while(count==0);
        
       // int k=random.nextInt(13);
        
        do
        {
            count_2=0;
        
            k=random.nextInt(12);
            
            for(Button_Cards b : cards)
            {   
                
               // System.out.println(i + "  " + k + " το id της καρτας: " +b.getId());
            
                if(b.getId()==k && b.getMatched()==false && b!=selectedCard)
                {
                   // System.out.println("μεσα στην if "+i + "  " + k +" το id της δευτερης: "+b.getId());
                
                   // System.out.println("δευτερη καρτα");
                
                    count_2++;
                    
                    b.doClick();
                    break;
                }
       
            }
        
        }while(count_2==0);
        
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
