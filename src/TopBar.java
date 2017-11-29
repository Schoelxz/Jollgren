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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Desktop;
import java.net.URI;


public class TopBar extends JMenuBar implements ActionListener{
    private static final long serialVersionUID = 6832903853877065807L;

    private Canvas canvas;
    private JMenu file, edit, settings, window, help;
    private JMenuItem _new, _open, _save, _save_as;
    private JMenuItem _undo, _redo, _cut, _copy, _paste;
    private JMenuItem _preferences;
    private JMenuItem _new_window;
    private JMenuItem _view_license, _about;

    public TopBar(Canvas c){
        //Bind this canvas to given canvas
        this.canvas = c;

        //Create file menu object
        this.file = new JMenu("File");

        //Cretae edit menu object
        this.edit = new JMenu("Edit");

        //Create settings menu object
        this.settings = new JMenu("Settings");

        //Create window menu object
        this.window = new JMenu("Window");

        //Create Help menu object
        this.help = new JMenu("Help");

        //Add "new" item to file menu
        this._new = new JMenuItem("New file");
        this.file.add(_new);
        
        //Add "open" item to file menu
        this._open = new JMenuItem("Open file");
        this.file.add(_open);

        //Add "save" item to file menu
        this._save = new JMenuItem("Save file");
        this.file.add(_save);

        //Add "save as" item to file menu
        this._save_as = new JMenuItem("Save file as"); 
        this.file.add(_save_as);

        //Add "undo" item to 'edit' menu
        this._undo = new JMenuItem("Undo");
        this.edit.add(_undo);
        this._undo.addActionListener(this);

        //Add "redo" item to 'edit' menu
        this._redo = new JMenuItem("Redo");
        this.edit.add(_redo);
        this._redo.addActionListener(this);

        //Add "cut" item to 'edit' menu
        this._cut = new JMenuItem("Cut");
        this.edit.add(_cut);

        //Add "copy" item to 'edit' menu
        this._copy = new JMenuItem("Copy");
        this.edit.add(_copy);

        //Add "paste" item to 'edit' menu
        this._paste = new JMenuItem("Paste");
        this.edit.add(_paste);

        //Add "Preferences" item to settings menu
        this._preferences = new JMenuItem("Preferences");
        this.settings.add(_preferences);
        this._preferences.addActionListener(this);
        
        //Add "new window" item to Window menu
        this._new_window = new JMenuItem("New window");
        this._new_window.addActionListener(this);
        this.window.add(_new_window);

        //Add "view license" item to Help menu
        this._view_license = new JMenuItem("View license");
        this._view_license.addActionListener(this);
        this.help.add(_view_license);

        //Add "about" item to Help menu
        this._about = new JMenuItem("About");
        this._about.addActionListener(this);
        this.help.add(_about);

        //Add menus to this bar
        this.add(file); 
        this.add(edit); 
        this.add(settings);
        this.add(window);
        this.add(help);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this._new_window){
            Main.createWindow("Untitled",JFrame.DISPOSE_ON_CLOSE);
        }
        else if(e.getSource() == this._view_license){
            try{
                Desktop.getDesktop().browse(new URI("https://www.gnu.org/licenses/gpl-3.0.en.html"));
            }catch(Exception ex){
                ex.printStackTrace();
                System.exit(1);
            }
            
        }
        else if(e.getSource() == this._about){
            new About();
        }
        else if (e.getSource() == this._preferences){
            new Preferences();
        }
        else if(e.getSource() == this._undo){
            this.canvas.undo();
        }
        else  if(e.getSource() == this._redo){
            this.canvas.redo();
        }
    }
}