/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 * @author Schoel
 */
public class WindowHandler 
{
    //Singleton?
    public static WindowHandler windowHandler;
    //List of windows
    public static ArrayList<JFrame> jayFrames;
    
    public WindowHandler()
    {
        if(windowHandler == null)
        {
            windowHandler = this;
            jayFrames = new ArrayList<JFrame>();
            WindowHandler.windowHandler.createWindow("Main Window", JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
    
    public JFrame createWindow(String title, int closeOp)
    {
        System.out.println("Creating new window.");
        JFrame f = new JFrame(title);
        //Add window(JFrame) to list
        jayFrames.add(f);
        f.setDefaultCloseOperation(closeOp);
        Canvas c = new Canvas();
        JMenuBar top = new TopBar(c);
        f.setJMenuBar(top);
        f.add(c);
        f.setSize(Settings.WINX, Settings.WINY);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        giveWindowListener(f);
        return(f);
    }
    
    private void giveWindowListener(JFrame jf)
    {
        jf.addWindowListener(
                new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        jf.dispose(); //Dispose of frame
                        System.out.println("Window closed: " + jf.getTitle());
                        jayFrames.remove(jf); //Remove frame from list
                        System.out.println("Number of windows left: " + jayFrames.size());
                        if(jayFrames.isEmpty())
                        {
                            System.out.println("Saving and Exiting program.");
                            Settings.save(); //Save settings
                            System.exit(0); //exit program
                        }
                    }
                });
    }
}
