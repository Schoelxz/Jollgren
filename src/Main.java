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

import java.awt.event.*;
import javax.swing.*;


public class Main {
    public static void main(String[] args){
        new Settings();

        //enable hardware acceleration
        //Use d3d for Windows and openGL for other
        if(System.getProperty("os.name").startsWith("Windows")){
            System.setProperty("sun.java2d.d3d", String.valueOf(Settings.HWACCEL));
        }else{
            System.setProperty("sun.java2d.opengl", String.valueOf(Settings.HWACCEL));
        }
        
        JFrame mainFrame = createWindow("Untitled", JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                Settings.save(); //Save settings
                mainFrame.dispose(); //Dispose of frame
                System.exit(0); //exit program
            }
        });
    }

    public static JFrame createWindow(String title ,int closeOp){
        JFrame f = new JFrame(title);
        f.setDefaultCloseOperation(closeOp);
        Canvas c = new Canvas();
        JMenuBar top = new TopBar(c);
        f.setJMenuBar(top);
        f.add(c);
        f.setSize(Settings.WINX, Settings.WINY);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        return(f);
    }
}

