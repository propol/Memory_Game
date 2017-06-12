package memory_game;

import javax.swing.JFrame;

/**
 *
 * @author Jay
 */
public class Memory_game {

    public static void main(String[] args) 
    {
        MenuGui gui = new MenuGui();
        
        //Multi_Player mp = new Multi_Player();
        //mp.setSize(500,500);
        //mp.setLocationRelativeTo(null);
        //mp.setVisible(true);
        //mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gui.setVisible(true);
        
        gui.setSize(500, 500);
        
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       /* Single_Mode sm = new Single_Mode();
        sm.test(); */
    }
    
}
