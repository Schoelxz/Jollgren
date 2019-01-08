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
class TypeNotImplementedException extends Exception{
    private static final long serialVersionUID = 3332190948251899122L;
}

public class Settings
{
    /**
     * Important! In order for this to work all variables below must be of primitive type
     * and be applicable to String.valueOf().
     */

    public static int     THICK;    //Brush thicknes
    public static int     STYLE;    //Brush style
    public static int     RED;      //Brush red color level
    public static int     GREEN;    //Brush green color level
    public static int     BLUE;     //Brush blue color level
    public static boolean HWACCEL;  //Hardware acceleration
    public static int     WINX;     //Main window x size
    public static int     WINY;     //Main window y size
    public static int     CANX;     //Canvas x size
    public static int     CANY;     //Canvas y size 


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
        catch(IllegalAccessException e){
            //Casued by above variables not being accessible, i.e. they're not public or declared final
            e.printStackTrace();
            System.exit(1);
        }
        catch(TypeNotImplementedException e){
            //Caused by one of the above primitive types not being considered in load() function
            e.printStackTrace();
            System.exit(1);
        }
        
        
    }
    private static void load(Field[] f, Scanner sc) throws InvalidFormatException, IllegalAccessException, TypeNotImplementedException{
        for(Field ff : f){
            if(sc.hasNext() && ff.getName().equals(sc.next())){
                if(ff.getType().equals(int.class)){
                    ff.setInt(null,sc.nextInt());
                }
                else if (ff.getType().equals(boolean.class)){
                    ff.setBoolean(null, sc.nextBoolean());
                }
                else{
                    throw new TypeNotImplementedException();
                }
            }
            else{
                throw new InvalidFormatException();
            }         
        }
    }

    /**
     * Will load default settings
     */
    private static void loadFallback(){
        //Put default values here
        THICK = 5;
        STYLE = 0;
        RED = 0;
        GREEN = 0;
        BLUE = 0;
        HWACCEL = false;
        WINX = 800;
        WINY = 800;
        CANX = 800;
        CANY = 800;
    }

    private static void createNew(Field[] f){
        try{
            File file = new File("Settings.conf");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for(Field ff : f){
                if(ff.getType().equals(int.class)){
                    bw.write(ff.getName() + " " + String.valueOf((int)ff.get(null)));
                    bw.newLine();
                }
                else if(ff.getType().equals(boolean.class)){
                    bw.write(ff.getName() + " " + String.valueOf((boolean)ff.get(null)));
                    bw.newLine();
                }
                else{
                    bw.close();
                    throw new TypeNotImplementedException();
                }
                
            }
            bw.newLine();
            bw.close();

        }catch(IllegalAccessException | IOException | TypeNotImplementedException e){
            e.printStackTrace();
            System.exit(1);
        }
        
    }

    public static void save(){
        Settings.createNew(Settings.class.getDeclaredFields());
    }
    
 }

