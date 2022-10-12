package controller.levels;


import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

public class Tegneknapp extends JButton{
    private int xpos;
    private int ypos;
    boolean trykket = false;

    public Tegneknapp(int x, int y){
        xpos = x;
        ypos = y;
    }
    
}
