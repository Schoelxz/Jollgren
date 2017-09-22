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
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

