import javax.swing.*;


public class Main {
    public static void main(String[] args){
        //enable hardware acceleration
        System.setProperty("sun.java2d.opengl", "true");
        
        JFrame f = new JFrame("Window");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel c = new Canvas();
        f.add(c);
        f.setSize(800, 800);
        f.setVisible(true);
    }
}

