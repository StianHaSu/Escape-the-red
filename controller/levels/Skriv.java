package controller.levels;

import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
public class Skriv implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("e");
        FileWriter fw = null;
        Tegneknapp[][] t = LevelCreator.getTegning();
        try {fw = new FileWriter("controller/levels/custom.txt", false);}
        catch (Exception ee) {System.out.println(ee);}

        for (Tegneknapp[] linje : t){
            String linjeSkrift = "";
            for (Tegneknapp knapp : linje){
                if (knapp.trykket){
                    linjeSkrift+=" 1";
                    System.out.println("a");
                } else {linjeSkrift+= " 0";}
            }
            String klar = linjeSkrift.trim();
            try{
                fw.write(klar+"\n");
            } catch (Exception e2){System.out.println(e2);}
        }

        try {
            fw.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    
}
