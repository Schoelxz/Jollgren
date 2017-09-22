import java.awt.BorderLayout;

import javax.swing.*;


public class Main {
    public static void main(String[] args){
        //enable hardware acceleration
        System.setProperty("sun.java2d.opengl", "true");
        createWindow("Window");
        
    }

    public static void createWindow(String title){
        JFrame f = new JFrame(title);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel c = new Canvas();
        JMenuBar top = new TopBar();
        f.setJMenuBar(top);
        f.add(c);
        f.setSize(800, 800);
        f.setVisible(true);
    }
}

