/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Detection;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * It is the main class . 
 * Here We use getSystemLookAndFell to get system's interface
 * And display mainframe
 */
public class App {
    public static void main(String[] args)  {
        
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
            
         
          MainFrame mainFrame = new MainFrame();
          mainFrame.displayScreen();
       
    }
    
}
