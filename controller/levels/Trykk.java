package controller.levels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

public class Trykk implements ActionListener{
    private Tegneknapp knappen;
    private JPanel tegn;
    public Trykk(Tegneknapp knapp, JPanel p){
        knappen = knapp;
        tegn = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (knappen.trykket != true){
            knappen.setBackground(Color.BLACK);
            knappen.trykket = true;
            tegn.repaint();
        } else {
            knappen.setBackground(Color.white);
            knappen.trykket = false;
        }
    }
}
