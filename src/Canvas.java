/**
 * Copyright (C) 2017 Jesper Derander
 * 
 * This file is part of Jollgren.
 *
 * Jollgren is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jollgren is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jollgren.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.lang.Math;


public class Canvas extends JPanel implements KeyListener, MouseListener, MouseMotionListener{
    private static final long serialVersionUID = 4865446981722813115L;

    //List for objects on canvas
    private ArrayList<Dot> dots;
    
    public Canvas(){
        this.dots = new ArrayList<>();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setFocusable(true);
        this.addKeyListener(this);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        int prevX = -1, prevY = -1;
        for(int i = 0; i < dots.size(); i++){
            g.setColor(dots.get(i).getColor());
            if(i>0){
                //Only fill gap if next Dot has an offset of more
                //than half of it's thickness from previous Dot.
                if(((Math.abs(dots.get(i).getX()-prevX)>(int)(Settings.THICK/2)) || (Math.abs(dots.get(i).getY()-prevY)>(int)(Settings.THICK/2))) &&
                     dots.get(i-1).inLine() == Dot.INLINE && dots.get(i).inLine() == Dot.INLINE ){
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setStroke(new BasicStroke((float) Settings.THICK));

                    //+(int)(this.THICK/2) used in order for line to 
                    // start from middle of previous Dot.
                    g2d.drawLine(prevX+(int)(Settings.THICK/2), prevY+(int)(Settings.THICK/2), 
                                 dots.get(i).getX()+(int)(Settings.THICK/2), dots.get(i).getY()+(int)(Settings.THICK/2));
                    g2d.dispose();
                    
                }
                else{
                    g.fillRect(dots.get(i).getX(), dots.get(i).getY(), Settings.THICK, Settings.THICK);
                }
            }
            else{
                g.fillRect(dots.get(i).getX(), dots.get(i).getY(), Settings.THICK, Settings.THICK);
            }
            prevX = dots.get(i).getX(); prevY = dots.get(i).getY();
            
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
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            Dot d = new Dot(new int[] {e.getX()-(int)(Settings.THICK/2),e.getY()-(int)(Settings.THICK/2)},
                            new Color(Settings.RED, Settings.GREEN, Settings.BLUE), Dot.NOLINE);
            dots.add(d);
            this.repaint();
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {}
    
    @Override 
    public void mouseDragged(MouseEvent e) {
        int curX = e.getX(), curY = e.getY();
        Dot d = new Dot(new int[] {curX-(int)(Settings.THICK/2),curY-(int)(Settings.THICK/2)},
                        new Color(Settings.RED, Settings.GREEN, Settings.BLUE), Dot.INLINE);
        dots.add(d);
        this.repaint();
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode() == KeyEvent.VK_Z && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0){
            this.undo();
       }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}

    public void undo(){
        if(this.dots.size() > 0){
            //Remove single dot
            this.dots.remove(this.dots.size()-1);
            //All lines end with a single dot, thus if a line follows after removal of dot,
            //then remove the line.
            if(this.dots.size() > 0 && this.dots.get(this.dots.size()-1).inLine()){
                while(this.dots.size() > 0 && this.dots.get(this.dots.size()-1).inLine()){
                    this.dots.remove(this.dots.size()-1);
                }
            }
        }
        this.validate();
        this.repaint();
        
    }
    public void redo(){

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
