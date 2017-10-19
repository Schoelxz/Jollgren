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

        //Create Layouts
        GridLayout tabLayout = new GridLayout(20,0,2,2);
        FlowLayout rowLayout = new FlowLayout();
        //'Advanced' tab content
        this.advancedPref.setLayout(tabLayout);
        rowLayout.setAlignment(FlowLayout.LEFT);
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
        JPanel hwPanel = new JPanel();
        hwPanel.setLayout(rowLayout);
        hwPanel.add(hwa);
        this.advancedPref.add(hwPanel);

        //'Window' tab content
        this.windowPref.setLayout(tabLayout);
        
        //Window width label
        JPanel winxLabelPanel = new JPanel();
        winxLabelPanel.setLayout(rowLayout);
        JLabel winxLabel = new JLabel("Start width for main window");
        winxLabelPanel.add(winxLabel);

        //Window width textfield
        JPanel winxTextPanel = new JPanel();
        winxTextPanel.setLayout(rowLayout);
        JTextField winWidth = new JTextField(Integer.toString(Settings.WINX), 15);
        winWidth.setHorizontalAlignment(JTextField.RIGHT);
        winWidth.addFocusListener(new FocusListener(){
        
            @Override
            public void focusLost(FocusEvent e) {
                Settings.WINX = Integer.valueOf(winWidth.getText());
            }
        
            @Override
            public void focusGained(FocusEvent e) {
                
            }
        });
        winxTextPanel.add(winWidth);

        //Window height label
        JPanel winyLabelPanel = new JPanel();
        winyLabelPanel.setLayout(rowLayout);
        JLabel winyLabel = new JLabel("Start height for main window");
        winyLabelPanel.add(winyLabel);

        //Window width textfield
        JPanel winyTextPanel = new JPanel();
        winyTextPanel.setLayout(rowLayout);
        JTextField winHeight = new JTextField(Integer.toString(Settings.WINY), 15);
        winHeight.setHorizontalAlignment(JTextField.RIGHT);
        winHeight.addFocusListener(new FocusListener(){
            
                @Override
                public void focusLost(FocusEvent e) {
                    Settings.WINY = Integer.valueOf(winHeight.getText());
                }
            
                @Override
                public void focusGained(FocusEvent e) {
                    
                }
            });
        winyTextPanel.add(winHeight);

        //Add items in order
        this.windowPref.add(winxLabelPanel);
        this.windowPref.add(winxTextPanel);
        this.windowPref.add(winyLabelPanel);
        this.windowPref.add(winyTextPanel);

        //Set close operation
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Set size
        this.setSize(600, 600);
        this.setResizable(false);

        //Set title
        this.setTitle("Preferences");


        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}