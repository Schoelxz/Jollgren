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

import java.awt.Font;

import javax.swing.*;

public class About extends JFrame{
    private static final long serialVersionUID = -173859703961811969L;

    public About(){
        this.setTitle("About");
        this.setSize(450,220);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String text = "Version: 1.0 - dev build." + "<br>" + "<br>" 
                    + "Jollgren Copyright (C) 2017 Jesper Derander." + "<br>" + "<br>"
                    + "This program comes with ABSOLUTELY NO WARRANTY." + "<br>"
                    + "This is free software, and you are welcome to" + "<br>" 
                    + "redistribute it under certain conditions." + "<br>"
                    + "For more information see 'Help' -> 'View license'.";
        JLabel label = new JLabel("<html><div style='text-align:center;'>"+text+"</div></html>", SwingConstants.CENTER);
        label.setFont(new Font("sans-serif", Font.PLAIN, 13));
        this.add(label);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

 }