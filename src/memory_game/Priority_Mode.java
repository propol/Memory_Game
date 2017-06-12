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
 * 
 * Implements the Priority Mode of the game(function 7) where the player must match a pair/trio/quarteto of cards in a specific sequence as indicated from the numbers on the cards' icons
 * @param mode Keeps the mode(double-card pair, trio-card pair, quartet-card pair) of this particular game mode of the game that the user wants to play.
 * @param priorityFound Keeps the priority value that must be found next. For example if priorityFound is 1 it means that the pair of cards with the number 1 written on their icons must be matched; when matched, priorityFound will become 2 and so the cards with 2 written on their icons must be matched
 * @param priorityFound if priorityFound is 1 and the player opens two cards with 7 written on their icons, then these cards will close as 7 will be the wrong priority at that time
 */

public class Priority_Mode extends JFrame {
    
    String mode;
    private int count;
    private int priorityFound;
    private Icon cardback;
    private int[] idList;
    private boolean[] foundIDs={false,false,false,false,false,false,false,false,
        false,false,false,false,false};
    private HashMap<JButton, Cards> buttons;
    private String[] icons;
    private String[] images={"edbob.jpg", "edcarl.jpg","eddog.jpg","eddude.jpg","edfat.jpg",
        "edhood.jpg","edpokerFace.jpg","edprettyOne.jpg","edsad_Lary.jpg","edstickMan.jpg",
        "edskil.jpg","edtsour.jpg"};
    
    /**
     * Constructor of this Class - Initializes all the params and creates JButton and their relative Cards objects; also asks the player which mode of Priority_Mode he wants to play
     */
    public Priority_Mode(){
        
        super("Priority Mode");
        setLayout(new GridLayout(6 , 8 , 5, 5));
        setExtendedState(MAXIMIZED_BOTH);
        mode = JOptionPane.showInputDialog("Which Priority mode do you want to play? Press 1 for the double-card pair mode, 2 for the trio-card pair mode,"
                + " 3 for the quartet-card pair mode: ");
        cardback = new ImageIcon(getClass().getResource("cardBack2.jpg"));
        if(mode.equals("1")){
            count=0;
            priorityFound=1;
            
            idList=new int[2];
            idList[0]=-1;
            idList[1]=-1;
            
            
            
            icons = new String[24];
            int i=0;
            for(int j=0 ; j<24; j++){
               
                icons[j]=images[i];
                i++;
                if(i==12)
                    i=0;
            }
            
            buttons = new HashMap<JButton, Cards>(24);
            i=0;
            for (int j =0;j<24;j++)
            {
                
                JButton b=new JButton();
                b.setIcon(cardback);
                Icon icon = new ImageIcon(getClass().getResource(icons[j]));
                i++;
                Cards card = new Cards(icon,i,i);
            
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
                entry.getKey().addActionListener(new Priority_Mode.JButtonHandler(1));
            }
            
        }else if (mode.equals("2")){
            count=0;
            priorityFound=1;
            
            idList=new int[3];
            idList[0]=-1;
            idList[1]=-1;
            idList[2]=-1;
            
            
            
            icons = new String[36];
            int i=0;
            for(int j=0 ; j<36; j++){
               
                icons[j]=images[i];
                i++;
                if(i==12)
                    i=0;
            }
            
            buttons = new HashMap<JButton, Cards>(36);
            i=0;
            for (int j =0;j<36;j++)
            {
                
                JButton b=new JButton();
                b.setIcon(cardback);
                Icon icon = new ImageIcon(getClass().getResource(icons[j]));
                i++;
                Cards card = new Cards(icon,i,i);
            
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
                entry.getKey().addActionListener(new Priority_Mode.JButtonHandler(2));

            }
        }else if (mode.equals("3")){
            count=0;
            priorityFound=1;
            
            idList=new int[4];
            idList[0]=-1;
            idList[1]=-1;
            idList[2]=-1;
            idList[3]=-1;
            
            
            icons = new String[48];
            int i=0;
            for(int j=0 ; j<48; j++){
               
                icons[j]=images[i];
                i++;
                if(i==12)
                    i=0;
            }
            
            buttons = new HashMap<JButton, Cards>(36);
            i=0;
            for (int j =0;j<48;j++)
            {
                
                JButton b=new JButton();
                b.setIcon(cardback);
                Icon icon = new ImageIcon(getClass().getResource(icons[j]));
                i++;
                Cards card = new Cards(icon,i,i);
            
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
                entry.getKey().addActionListener(new Priority_Mode.JButtonHandler(3));

            }
        }
    }
    
    /**
     * ActionListener of the JButtons created above - According to the mode selected by the player the actionPerformed utilizes the correct "if" statement.
     * Counts the number of cards opened by the player and when he should not open any more cards, checks if the already opened cards are a pair; if yes, then the opened cards are matched and remain open; else the opened cards close
     * After that, the counter(param count) of the opened cards restarts and the player is allowed to open more cards and the same process continues until the game is won.
     */
    
    private class JButtonHandler implements ActionListener{
        
        private int mode;
        
        public JButtonHandler(int mode){
            this.mode=mode;
        }
        
        
        @Override
        public void actionPerformed(ActionEvent event){
                 
           if(mode==1){
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

                    if(idList[0]==idList[1] && entry.getValue().getPriority()==priorityFound)
                    {
                        
                        count=0;
                        priorityFound++;
                        temp=card.getID();
                        foundIDs[idList[0]]=true;
                        idList[0]=temp;
                        
                    }else if(idList[0]==idList[1] && entry.getValue().getPriority()!=priorityFound){
                        
                        
                        for(Map.Entry<JButton, Cards> entr : buttons.entrySet()){
                            if(entr.getValue().getID()==idList[0]){
                                entr.getKey().setIcon(cardback);
                            }
                        }
                        

                        count=1;
                        
                        idList[1]=-1;
                        idList[0]=card.getID();
                        
                    }


                    if(count==3 && idList[0]!=idList[1])
                    {
                        
                        for(Map.Entry<JButton, Cards> entr : buttons.entrySet()){
                            if(entr.getValue().getID()==idList[0] || entr.getValue().getID()==idList[1]){
                                entr.getKey().setIcon(cardback);
                            }
                        }

                        count=1;
                        temp=card.getID();
                        idList[1]=-1;
                        idList[0]=temp;
                        
                    }
                    entry.getKey().setIcon(card.getIcon());
                    

               }
             }
           }else if(mode==2){
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

                        if(idList[0]==idList[1] && idList[1]==idList[2] && entry.getValue().getPriority()==priorityFound)
                        {
                            
                            count=0;
                            priorityFound++;
                            temp=card.getID();
                            foundIDs[idList[0]]=true;
                            idList[0]=temp;
                            

                        }else if(idList[0]==idList[1] && idList[1]==idList[2] && entry.getValue().getPriority()!=priorityFound){
                            

                            for(Map.Entry<JButton, Cards> entr : buttons.entrySet()){
                                if(entr.getValue().getID()==idList[0]){
                                    entr.getKey().setIcon(null);
                                }
                            }


                            count=1;
                            
                            idList[1]=idList[2]=-1;
                            idList[0]=card.getID();
                        }


                        if(count==4 && (idList[0]!=idList[1] || idList[2]!=idList[1]) && (foundIDs[idList[0]]==false || foundIDs[idList[1]]==false || foundIDs[idList[2]]==false))
                        {
                            
                            for(Map.Entry<JButton, Cards> entr : buttons.entrySet()){
                                if(entr.getValue().getID()==idList[0] || entr.getValue().getID()==idList[1] || entr.getValue().getID()==idList[2]){
                                    entr.getKey().setIcon(null);
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
           }else if(mode==3){
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

                        if(idList[0]==idList[1] && idList[1]==idList[2] && idList[2]==idList[3] && entry.getValue().getPriority()==priorityFound)
                        {
                            
                            count=0;
                            priorityFound++;
                            temp=card.getID();
                            foundIDs[idList[0]]=true;
                            idList[0]=temp;
                            

                        }else if(idList[0]==idList[1] && idList[1]==idList[2] && idList[2]==idList[3] && entry.getValue().getPriority()!=priorityFound){
                            

                            for(Map.Entry<JButton, Cards> entr : buttons.entrySet()){
                                if(entr.getValue().getID()==idList[0]){
                                    entr.getKey().setIcon(null);
                                }
                            }


                            count=1;
                            
                            idList[1]=idList[2]=idList[3]=-1;
                            idList[0]=card.getID();
                        }


                        if(count==5 && (idList[0]!=idList[1] || idList[1]!=idList[2] || idList[2]!=idList[3]) )
                        {
                            
                            for(Map.Entry<JButton, Cards> entr : buttons.entrySet()){
                                if(entr.getValue().getID()==idList[0] || entr.getValue().getID()==idList[1] || entr.getValue().getID()==idList[2] || entr.getValue().getID()==idList[3]){
                                    entr.getKey().setIcon(null);
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
