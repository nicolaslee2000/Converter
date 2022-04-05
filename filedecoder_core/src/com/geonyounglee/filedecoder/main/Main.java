package main;

import gui.MainFrame;

/**
 *
 * @author Nick Lee
 */
public class Main {
    public static void main(String args[]) {
        /*Look and Feel setup*/
        com.formdev.flatlaf.FlatLightLaf.setup();
        javax.swing.UIManager.put( "TabbedPane.selectedBackground", java.awt.Color.white );
        /*End of setup*/
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
