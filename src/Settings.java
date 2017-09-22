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
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.lang.reflect.Field;
import java.io.*;

class InvalidFormatException extends Exception {
    private static final long serialVersionUID = -3812570707864472667L;    
}

public class Settings{

    public static int THICK; //Brush thicknes
    public static int RED; //Brush red color level
    public static int GREEN; //Brush green color level
    public static int BLUE; //Brush blue color level
    public static Boolean HWACCEL; //Hardware acceleration
    public static int WINX;        //Main window x size
    public static int WINY;        //Main window y size
    public static int CANX;        //Canvas x size
    public static int CANY;       //Canvas y size 


    public Settings(){
        Field[] f = this.getClass().getDeclaredFields();
        try{
            Scanner sc = new Scanner(new File("Settings.conf"));
            load(f, sc);
        }
        catch(FileNotFoundException e){
            loadFallback();
            createNew(f);
        }
        catch(InvalidFormatException e){
            loadFallback();
            JOptionPane.showMessageDialog(null, "The settings file is corrupted so default settings have been loaded.");
        }
        
        
    }
    private void load(Field[] f, Scanner sc) throws InvalidFormatException{
        if(f[0].getName().equals(sc.next())){
            THICK = sc.nextInt();
        }else{throw new InvalidFormatException();}
        
        if(f[1].getName().equals(sc.next())){
            RED = sc.nextInt();
        }else{throw new InvalidFormatException();}

        if(f[2].getName().equals(sc.next())){
            GREEN = sc.nextInt();
        }else{throw new InvalidFormatException();}

        if(f[3].getName().equals(sc.next())){
            BLUE = sc.nextInt();
        }else{throw new InvalidFormatException();}

        if(f[4].getName().equals(sc.next())){
            HWACCEL = sc.nextBoolean();
        }else{throw new InvalidFormatException();}

        if(f[5].getName().equals(sc.next())){
            WINX = sc.nextInt();
        }else{throw new InvalidFormatException();}

        if(f[6].getName().equals(sc.next())){
            WINY = sc.nextInt();
        }else{throw new InvalidFormatException();}

        if(f[7].getName().equals(sc.next())){
            CANX = sc.nextInt();
        }else{throw new InvalidFormatException();}

        if(f[8].getName().equals(sc.next())){
            CANY = sc.nextInt();
        }else{throw new InvalidFormatException();}
    }

    /**
     * Will load default settings
     */
    private void loadFallback(){
        THICK = 5;
        RED = 0;
        GREEN = 0;
        BLUE = 0;
        HWACCEL = false;
        WINX = 800;
        WINY = 800;
        CANX = 800;
        CANY = 800;
    }

    private void createNew(Field[] f){
        try{
            File file = new File("Settings.conf");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for(Field ff : f){
                bw.write(ff.getName() + " " + ff.get(null).toString());
                bw.newLine();
            }
            bw.newLine();
            bw.close();

        }catch(IllegalAccessException | IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        
    }

    
 }

