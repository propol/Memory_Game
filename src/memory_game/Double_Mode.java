/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory_game;

import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
 * 
 * Implements the Double Mode of the game(function 2 as given) where there are 24 different pairs.
 * 
 * @param count Counts how many cards a player opened each turn, it is used in order to know when to open or close cards
 * @param cardback It is the icon shown when a card is closed
 * @param idList Keeps the IDs of the two cards that the player opens each turn
 * @param foundIDs Keeps the IDs of the pairs that are matched by the player, each number cell of the array represents one ID number, for example foundIDs[1] stands for the pair with ID 1,if its value is false it means that the pair isn't matched; when matched the value will become true
 * @param buttons A map which relates the JButtons(key) used in the JFrame to the Cards(value) objects. Each JButton represents a specific card and this Map helps identifying this relationship
 * @param icons A String array that keeps the names of the icons used for the Cards
 * 
 */

public class Double_Mode extends JFrame {
    
    private int count;
    private Icon cardback;
    private int[] idList={-1,-1};
    private boolean[] foundIDs={false,false,false,false,false,false,false,false,
        false,false,false,false,false,false,false,false,false,false,false,false,
        false,false,false,false,false};
    private HashMap<JButton, Cards> buttons;
    private String[] icons={"bob.png", "carl.jpg","dog.jpg","dude.jpg","fat.jpg",
        "hood.jpg","pokerFace.jpg","prettyOne.jpg","sad_Lary.jpg","stickMan.jpg",
        "skil.jpg","tsour.jpg","donald.jpg","duck.jpg","garfield.jpg","goofy.jpg",
        "mario.jpg","mickey.jpg","mouse.jpg","peter.jpg","pluto.jpg","popeye.jpg",
        "simsons.jpg","stewie.jpg","bob.png", "carl.jpg","dog.jpg","dude.jpg","fat.jpg",
        "hood.jpg","pokerFace.jpg","prettyOne.jpg","sad_Lary.jpg","stickMan.jpg",
        "skil.jpg","tsour.jpg","donald.jpg","duck.jpg","garfield.jpg","goofy.jpg",
        "mario.jpg","mickey.jpg","mouse.jpg","peter.jpg","pluto.jpg","popeye.jpg",
        "simsons.jpg","stewie.jpg"};
    
    /**
     * Constructor of this Class - Initializes all the params and creates JButton and their relative Cards objects;
     * 
     */
    public Double_Mode(){
        super("Double Mode");
        setLayout(new GridLayout(6 , 8 , 5 , 5));
        setExtendedState(MAXIMIZED_BOTH);
        cardback = new ImageIcon(getClass().getResource("cardback2.jpg"));
        count=0;
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
            if(i==24)
                i=0;
            
            
        }
        
        ArrayList<JButton> list = new ArrayList<JButton>(buttons.keySet());
        Collections.shuffle(list);
        
        for(JButton button : list){
            add(button);
        }
        
        for(Map.Entry<JButton, Cards> entry: buttons.entrySet()){
            entry.getKey().addActionListener(new JButtonHandler());

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
                   if(count<3){
                       idList[count-1]=card.getID();
                   }
                   
                   entry.getKey().setIcon(card.getIcon());
                   
                   if(idList[0]==idList[1])
                   {
                       count=0;
                       temp=card.getID();
                       foundIDs[idList[0]]=true;
                       idList[0]=temp;
                   }
                   
                   
                   if(count==3 && idList[0]!=idList[1] && foundIDs[idList[0]] == false && foundIDs[idList[1]] == false)
                   {
                       
                       for(Map.Entry<JButton, Cards> entr : buttons.entrySet()){
                           if(entr.getValue().getID()==idList[0] || entr.getValue().getID()==idList[1]){
                               entr.getKey().setIcon(cardback);
                           }
                       }
                       
                       count=1;
                       temp=card.getID();
                       idList[0]=temp;
                       idList[1]=-1;
                       
                   }
                   
                   
                   break;
               }
           }
           int k=0;
           for(k=0;k<25;k++){
               if(foundIDs[k]==false){
                   break;
               }
           }
           if(k==25){
               JOptionPane.showMessageDialog(null, "Congratulations! You won the game!");
           }
            
        }
    }
    
}
