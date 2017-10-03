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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Preferences extends JFrame {
    private static final long serialVersionUID = -7686319823287726250L;

    private JPanel windowPref, advancedPref;
    private JTabbedPane tp;

    public Preferences(){
        //Initialize panels
        this.windowPref = new JPanel();
        this.advancedPref = new JPanel();

        //Initialize JTabbedPane
        this.tp = new JTabbedPane();

        //Add panels to tp
        this.tp.add("Window", this.windowPref);
        this.tp.add("Advanced", this.advancedPref);

        //Add tp to this frame
        this.add(tp);

        //'Advanced' tab content
        FlowLayout advancedLayout = new FlowLayout();
        this.advancedPref.setLayout(advancedLayout);
        advancedLayout.setAlignment(FlowLayout.LEFT);
        JCheckBox hwa = new JCheckBox("Hardware acceleration", Settings.HWACCEL);
        hwa.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                Settings.HWACCEL=((JCheckBox)e.getSource()).isSelected();
                //Display message on new thread to prevent duplicate windows
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(getContentPane(),"This will take effect after you restart the program!");
                    }
                });
            }
        });
        this.advancedPref.add(hwa);

        //Set close operation
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Set size
        this.setSize(600, 600);
        this.setResizable(false);


        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}