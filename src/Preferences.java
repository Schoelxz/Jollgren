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

public class Preferences extends JFrame implements FocusListener{
    private static final long serialVersionUID = -7686319823287726250L;

    private JPanel windowPref, advancedPref, hwPanel, winxLabelPanel, winxTextPanel, winyLabelPanel, winyTextPanel;
    private JLabel winxLabel, winyLabel;
    protected JTextField winWidth, winHeight;
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
        this.hwPanel = new JPanel();
        this.hwPanel.setLayout(rowLayout);
        this.hwPanel.add(hwa);
        this.advancedPref.add(this.hwPanel);

        //'Window' tab content
        this.windowPref.setLayout(tabLayout);
        
        //Window width label
        this.winxLabelPanel = new JPanel();
        this.winxLabelPanel.setLayout(rowLayout);
        this.winxLabel = new JLabel("Start width for main window");
        this.winxLabelPanel.add(this.winxLabel);

        //Window width textfield
        this.winxTextPanel = new JPanel();
        this.winxTextPanel.setLayout(rowLayout);
        this.winWidth = new JTextField(Integer.toString(Settings.WINX), 15);
        this.winWidth.setHorizontalAlignment(JTextField.RIGHT);
        this.winWidth.addFocusListener(this);
        this.winxTextPanel.add(winWidth);

        //Window height label
        this.winyLabelPanel = new JPanel();
        this.winyLabelPanel.setLayout(rowLayout);
        this.winyLabel = new JLabel("Start height for main window");
        this.winyLabelPanel.add(this.winyLabel);

        //Window width textfield
        this.winyTextPanel = new JPanel();
        this.winyTextPanel.setLayout(rowLayout);
        this.winHeight = new JTextField(Integer.toString(Settings.WINY), 15);
        this.winHeight.setHorizontalAlignment(JTextField.RIGHT);
        this.winHeight.addFocusListener(this);
        this.winyTextPanel.add(winHeight);

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
    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == this.winWidth){
            Settings.WINX = Integer.valueOf(this.winWidth.getText());
        }
        else if(e.getSource() == this.winHeight){
            Settings.WINY = Integer.valueOf(this.winHeight.getText());
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        
    }

}