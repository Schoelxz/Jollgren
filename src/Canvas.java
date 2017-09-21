import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.lang.Math;


public class Canvas extends JPanel implements MouseListener, MouseMotionListener{
    //List for objects on canvas
    ArrayList<Dot> dots = new ArrayList<>();
    //Thickness for brush > 0 !
    public final int THICK = 5;
    //Color for brush
    public Color COLOR = Color.BLACK;
    public Canvas(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        int prevX = -1, prevY = -1;
        for(int i = 0; i < dots.size(); i++){
            g.setColor(dots.get(i).getColor());
            if(i>0){
                if(((Math.abs(dots.get(i).getX()-prevX)>(int)(this.THICK/2)) || (Math.abs(dots.get(i).getY()-prevY)>(int)(this.THICK/2))) &&
                     dots.get(i-1).inLine() == Dot.INLINE && dots.get(i).inLine() == Dot.INLINE ){
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setStroke(new BasicStroke((float) this.THICK));
                    g2d.drawLine(prevX, prevY, dots.get(i).getX(), dots.get(i).getY());
                    g2d.dispose();
                    
                }
                else{
                    g.fillRect(dots.get(i).getX(), dots.get(i).getY(), this.THICK, this.THICK);
                }
            }
            else{
                g.fillRect(dots.get(i).getX(), dots.get(i).getY(), this.THICK, this.THICK);
            }
            prevX = dots.get(i).getX(); prevY = dots.get(i).getY();
            
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1){
            Dot d = new Dot(new int[] {e.getX()-(int)(this.THICK/2),e.getY()-(int)(this.THICK/2)},this.COLOR, Dot.NOLINE);
            dots.add(d);
            this.repaint();
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            Dot d = new Dot(new int[] {e.getX()-(int)(this.THICK/2),e.getY()-(int)(this.THICK/2)},this.COLOR, Dot.NOLINE);
            dots.add(d);
            this.repaint();
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {}
    
    @Override 
    public void mouseDragged(MouseEvent e) {
        int curX = e.getX(), curY = e.getY();
        Dot d = new Dot(new int[] {curX-(int)(this.THICK/2),curY-(int)(this.THICK/2)},this.COLOR, Dot.INLINE);
        dots.add(d);
        this.repaint();
    
    }

}

class Dot {
    private int[] a;
    private Color c;
    private Boolean inLine;

    public static final Boolean INLINE = true;
    public static final Boolean NOLINE = false;

    public Dot (int[] a, Color c, Boolean inLine){
        this.a =new int[2];
        this.a[0] = a[0]; this.a[1] = a[1];
        this.c = c;
        this.inLine = inLine;
    }

    public Boolean inLine(){
        return inLine;
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
