import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;


class Panel extends JPanel implements MouseListener, MouseMotionListener{
    //List for objects on canvas
    ArrayList<ArrayList<Integer>> coords = new ArrayList<>();
    //Thickness for brush > 0 !
    public final int THICK = 5;
    //Color for brush
    public final Color COLOR = Color.BLACK;
    public Panel(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        g.setColor(this.COLOR);
        for(ArrayList<Integer> coord : coords){
            g.fillRect(coord.get(0), coord.get(1), this.THICK, this.THICK);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        ArrayList<Integer> a = new ArrayList<>(2);
        a.add(e.getX()-(int)(this.THICK/2)); a.add(e.getY()-(int)(this.THICK/2));
        coords.add(a);
        this.repaint();
    }
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
        ArrayList<Integer> a = new ArrayList<>(2);
        if(prevX != curX || prevY != curY){
            a.add(curX-(int)(this.THICK/2)); a.add(curY-(int)(this.THICK/2));
            coords.add(a);
            this.repaint();
        }       
    }
}


public class Main {
    public static void main(String[] args){
        JFrame f = new JFrame("Window");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel p = new Panel();
        f.add(p);
        f.setSize(800, 800);
        f.setVisible(true);
    }
}

