package controller.levels;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class LevelCreator {
    static JFrame ramme = new JFrame();
    static JPanel panel = new JPanel();
    static JPanel tegne = new JPanel();
    static JButton tegn = new JButton("Lag bane");
    static Tegneknapp[][] verdier = new Tegneknapp[30][38];


    public static void main(String[] args) {

        ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(new BorderLayout());

        tegne.setLayout(new GridLayout(30, 38));

        tegne.setPreferredSize(new Dimension(1200, 800));

        tegn.addActionListener(new Skriv());

        for (int i = 0; i < 30; i++){
            for (int e = 0; e < 38; e++){
                Tegneknapp ny  = new Tegneknapp(i, e);
                ny.setOpaque(true);
                ny.addActionListener(new Trykk(ny, tegne));

                if (i == 0 || i == 29 || e == 0 || e == 37){
                    ny.trykket = true;
                    ny.setBackground(Color.BLACK);
                }

                ny.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tegne.add(ny);
                verdier[i][e] = ny;
            }
        }

        panel.add(tegn, BorderLayout.NORTH);
        panel.add(tegne, BorderLayout.SOUTH);
        ramme.add(panel);
        ramme.pack();
        ramme.setVisible(true);


    }

    public static Tegneknapp[][] getTegning(){
        return verdier;
    }


}
