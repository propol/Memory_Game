/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory_game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author 2513 - 2539
 */
/**
 * Implements the Trio Mode of the game(function 3) where not a pair of cards but a trio of cards must be matched
 * The parameters of this Class are the same as in Double_Mode, Quartet_Mode, Priority_Mode and RandomLocation as they have the exact same use; There are only a few changes
 * @param idList Now keeps 3 IDs of cards opened by the player as the game now requires matching three cards with the same ID
 * @param foundIDs Have less cells than the one in Double_Mode as there are fewer(12) different cards.
 * 
 */

public class Trio_Mode extends JFrame{
    
    private int count;
    private Icon cardback;
    private int[] idList={-1,-1,-1};
    private boolean[] foundIDs={false,false,false,false,false,false,false,false,
        false,false,false,false,false};
    private HashMap<JButton, Cards> buttons;
    private String[] icons={"bob.png", "carl.jpg","dog.jpg","dude.jpg","fat.jpg",
        "hood.jpg","pokerFace.jpg","prettyOne.jpg","sad_Lary.jpg","stickMan.jpg",
        "skil.jpg","tsour.jpg","bob.png", "carl.jpg","dog.jpg","dude.jpg","fat.jpg",
        "hood.jpg","pokerFace.jpg","prettyOne.jpg","sad_Lary.jpg","stickMan.jpg",
        "skil.jpg","tsour.jpg","bob.png", "carl.jpg","dog.jpg","dude.jpg","fat.jpg",
        "hood.jpg","pokerFace.jpg","prettyOne.jpg","sad_Lary.jpg","stickMan.jpg",
        "skil.jpg","tsour.jpg"};
    
    
    /**
     * Constructor of this Class - Initializes all the params and creates JButton and their relative Cards objects;
     * 
     */
    
    public Trio_Mode(){
        super("Trio Mode");
        setLayout(new GridLayout(6 , 8 , 5, 5));
        setExtendedState(MAXIMIZED_BOTH);
        count=0;
        cardback = new ImageIcon(getClass().getResource("cardBack2.jpg"));
        buttons= new HashMap<JButton, Cards>(36);
        int i=0;
        for (int j =0;j<36;j++)
        {
            JButton b=new JButton();
            b.setIcon(cardback);
            Icon icon = new ImageIcon(getClass().getResource(icons[j]));
            i++;
            Cards card = new Cards(icon,i);
            
            buttons.put(b, card);
            if(i==12)
                i=0;
            
            
        }
        
        ArrayList<JButton> list = new ArrayList<JButton>(buttons.keySet());
        Collections.shuffle(list);
        
        for(JButton button : list){
            add(button);
        }
        
        for(Map.Entry<JButton, Cards> entry: buttons.entrySet()){
            entry.getKey().addActionListener(new Trio_Mode.JButtonHandler());

        }
    }
    
    /**
     * ActionListener of the JButtons created above 
     * Counts the number of cards opened by the player and when he should not open any more cards, checks if the already opened cards are a pair; if yes, then the opened cards are matched and remain open; else the opened cards close
     * After that, the counter(param count) of the opened cards restarts and the player is allowed to open more cards and the same process continues until the game is won.
     */
    private class JButtonHandler implements ActionListener{
        
        
        @Override
        public void actionPerformed(ActionEvent event){
                 
           
           
           for(Map.Entry<JButton, Cards> entry : buttons.entrySet()){
               int temp=0;
               
               if(event.getSource()==entry.getKey())
               {
                   count++;
                   
                   Cards card = entry.getValue();
                   if(count<4){
                       idList[count-1]=card.getID();
                   }
                   
                   entry.getKey().setIcon(card.getIcon());
                   
                   if((idList[0]==idList[1]) && (idList[1]==idList[2]))
                   {
                       count=0;
                       temp=card.getID();
                       foundIDs[idList[0]]=true;
                       idList[0]=temp;
                       
                   }
                   
                   
                   if(count==4 && (idList[0]!=idList[1] || idList[1]!=idList[2]) && (foundIDs[idList[0]]==false || foundIDs[idList[1]]==false || foundIDs[idList[2]]==false))
                   {
                       
                       for(Map.Entry<JButton, Cards> entr : buttons.entrySet()){
                           if(entr.getValue().getID()==idList[0] || entr.getValue().getID()==idList[1] || entr.getValue().getID()==idList[2]){
                               entr.getKey().setIcon(cardback);
                           }
                       }
                       
                       count=1;
                       temp=card.getID();
                       idList[1]=idList[2]=-1;
                       idList[0]=temp;
                   }
                   entry.getKey().setIcon(card.getIcon());
                   
                   
               }
           }
           int k=0;
           for(k=0;k<13;k++){
               if(foundIDs[k]==false){
                   break;
               }
           }
           if(k==13){
               JOptionPane.showMessageDialog(null, "Congratulations! You won the game!");
           }
            
        }
    }
}
