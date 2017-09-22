import javax.swing.*;


public class TopBar extends JMenuBar{
    
    private JMenu file, edit, settings, window;
    private JMenuItem _new, _open, _save, _save_as;
    private JMenuItem _undo, _redo, _cut, _copy, _paste;
    private JMenuItem _preferences;
    private JMenuItem _new_window;

    public TopBar(){
        //Create file menu object
        this.file = new JMenu("File");

        //Cretae edit menu object
        this.edit = new JMenu("Edit");

        //Create settings menu object
        this.settings = new JMenu("Settings");

         //Create window menu object
         this.window = new JMenu("Window");

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

        //Add "redo" item to 'edit' menu
        this._redo = new JMenuItem("Redo");
        this.edit.add(_redo);

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
        
        //Add "new window" item to Window menu
        this._new_window = new JMenuItem("New window");
        this.window.add(_new_window);

        //Add menus to this bar
        this.add(file); 
        this.add(edit); 
        this.add(settings);
        this.add(window);
    }
}