import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;


class Panel extends JPanel implements MouseListener, MouseMotionListener{
    //List for objects on canvas
    ArrayList<Dot> dots = new ArrayList<>();
    //Thickness for brush > 0 !
    public final int THICK = 5;
    //Color for brush
    public Color COLOR = Color.BLACK;
    public Panel(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        for(Dot d : dots){
            g.setColor(d.getColor());
            g.fillRect(d.getX(), d.getY(), this.THICK, this.THICK);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e){}
    
    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void mouseMoved(MouseEvent e) {}
    
    @Override 
    public void mouseDragged(MouseEvent e) {
        int prevX = -1, prevY = -1;
        int curX = e.getX(), curY = e.getY();
        if(prevX != curX || prevY != curY){
            Dot d = new Dot(new int[] {curX-(int)(this.THICK/2),curY-(int)(this.THICK/2)},this.COLOR);
            dots.add(d);
            this.repaint();
        }       
    }
}

class Dot {
    private int[] a;
    private Color c;

    public Dot (int[] a, Color c){
        this.a =new int[2];
        this.a[0] = a[0]; this.a[1] = a[1];
        this.c = c;
    }

    public int getX(){
        return this.a[0];
    }

    public int getY(){
        return this.a[1];
    }

    public Color getColor(){
        return this.c;
    }
}


public class Main {
    public static void main(String[] args){
        //enable hardware acceleration
        System.setProperty("sun.java2d.opengl", "true");
        
        JFrame f = new JFrame("Window");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel p = new Panel();
        f.add(p);
        f.setSize(800, 800);
        f.setVisible(true);
    }
}

