/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory_game;

import javax.swing.JFrame;
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
import javax.swing.JOptionPane;

/**
 *
 * @author 2513 - 2539
 */
/**
 * 
 * Implements the Quartet Mode of the game(function 3) where four cards with the same ID must be matched.
 * Again the parameters are the same as explained in Double_Mode with one change.
 * @param idList Keeps four IDs of the cards opened by the player as the game requires four cards with the same ID to be matched
 * 
 */
public class Quartet_Mode extends JFrame {
    
    private int count;
    private Icon cardback;
    private int[] idList={-1,-1,-1,-1};
    private boolean[] foundIDs={false,false,false,false,false,false,false,false,
        false,false,false,false,false};
    private HashMap<JButton, Cards> buttons;
    private String[] icons={"bob.png", "carl.jpg","dog.jpg","dude.jpg","fat.jpg",
        "hood.jpg","pokerFace.jpg","prettyOne.jpg","sad_Lary.jpg","stickMan.jpg",
        "skil.jpg","tsour.jpg","bob.png", "carl.jpg","dog.jpg","dude.jpg","fat.jpg",
        "hood.jpg","pokerFace.jpg","prettyOne.jpg","sad_Lary.jpg","stickMan.jpg",
        "skil.jpg","tsour.jpg","bob.png", "carl.jpg","dog.jpg","dude.jpg","fat.jpg",
        "hood.jpg","pokerFace.jpg","prettyOne.jpg","sad_Lary.jpg","stickMan.jpg",
        "skil.jpg","tsour.jpg","bob.png", "carl.jpg","dog.jpg","dude.jpg","fat.jpg",
        "hood.jpg","pokerFace.jpg","prettyOne.jpg","sad_Lary.jpg","stickMan.jpg",
        "skil.jpg","tsour.jpg"};
    
    /**
     * Constructor of this Class - Initializes all the params and creates JButton and their relative Cards objects;
     */
    
    public Quartet_Mode(){
        super("Quartet Mode");
        setLayout(new GridLayout(6 , 8 , 5, 5));
        setExtendedState(MAXIMIZED_BOTH);
        count=0;
        cardback = new ImageIcon(getClass().getResource("cardBack2.jpg"));
        buttons= new HashMap<JButton, Cards>(48);
        int i=0;
        for (int j =0;j<48;j++)
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
            entry.getKey().addActionListener(new Quartet_Mode.JButtonHandler());

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
                   if(count<5){
                       idList[count-1]=card.getID();
                   }
                   
                   entry.getKey().setIcon(card.getIcon());
                   
                   if((idList[0]==idList[1]) && (idList[1]==idList[2] && (idList[2]==idList[3])))
                   {
                       count=0;
                       temp=card.getID();
                       foundIDs[idList[0]]=true;
                       idList[0]=temp;
                       
                   }
                   
                   
                   if(count==5 && (idList[0]!=idList[1] || idList[1]!=idList[2] || idList[2]!=idList[3]) && (foundIDs[idList[0]]==false || foundIDs[idList[1]]==false || foundIDs[idList[2]]==false || foundIDs[idList[3]]==false))
                   {
                       
                       for(Map.Entry<JButton, Cards> entr : buttons.entrySet()){
                           if(entr.getValue().getID()==idList[0] || entr.getValue().getID()==idList[1] || entr.getValue().getID()==idList[2] || entr.getValue().getID()==idList[3]){
                               entr.getKey().setIcon(cardback);
                           }
                       }
                       
                       count=1;
                       temp=card.getID();
                       idList[1]=idList[2]=idList[3]=-1;
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
