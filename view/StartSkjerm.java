package view;

import controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StartSkjerm {
    PlayingBoard gui;
    Controller kontroll;
    JFrame startRamme = new JFrame("Meny");
    JPanel startVindu = new JPanel();
    JPanel vanskelighetsgrader = new JPanel();
    JPanel highScoreSkjerm = new JPanel();
    JPanel ruteSkjerm = new JPanel();
    JButton startSpill, vanskelighet, avslutt, highScores, antallRuter, tilbake,
    atteRuter, tolvRuter, sekstenRuter, tjuefireRuter;
    JLabel meny;
    boolean show = true;

    public StartSkjerm(PlayingBoard g, Controller k){
        gui = g;
        kontroll = k;

        try{
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception e){System.out.println(e);}

        startRamme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startSpill = new JButton("Start");
        vanskelighet = new JButton("Vanskelighetsgrad");
        antallRuter = new JButton("Antall Ruter");
        avslutt = new JButton("Avslutt");
        highScores = new JButton("Highscores");
        tilbake = new JButton("Tilbake");

        meny = new JLabel("Meny");

        class startSpillet implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                kontroll.newGame();
                startRamme.setVisible(false);
                startRamme.dispose();    
            }
        }

        class vanskelighetsValg implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {   
                visVanskelighetsgrader();
            }
        }

        class endreVanskelighet implements ActionListener{
            int tid;

            public endreVanskelighet(int tid){
                this.tid = tid;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroll.newGame();
                startRamme.setVisible(false);
                startRamme.dispose();
            }
            
        }

        class Avslutt implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                kontroll.gameOver();
                
            } 
        }
        /* 
        class HighScoreKnapp implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                kontroll.hentHighScores(highScoreSkjerm);
                visHighScores();
                
            }
            
        }

        */

        class TilbakeTilMeny implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                visStartMeny();
                
            }
            
        }

        class RuteKnapp implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                visRuteValg();
            }
        }


        meny.setOpaque(true);
        meny.setHorizontalAlignment(JLabel.CENTER);
        meny.setBackground(Color.BLACK);
        meny.setForeground(Color.WHITE);
        meny.setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));
        meny.setPreferredSize(new Dimension(250, 100));

        startSpill.addActionListener(new startSpillet());
        startSpill.setPreferredSize(new Dimension(250, 80));
        startSpill.setBackground(Color.WHITE);
        startSpill.setForeground(Color.BLACK);
        startSpill.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));

        vanskelighet.addActionListener(new vanskelighetsValg());
        vanskelighet.setPreferredSize(new Dimension(250, 80));
        vanskelighet.setBackground(Color.WHITE);
        vanskelighet.setForeground(Color.BLACK);
        vanskelighet.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

        antallRuter.setPreferredSize(new Dimension(250, 80));
        antallRuter.setBackground(Color.WHITE);
        antallRuter.setForeground(Color.BLACK);
        antallRuter.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        antallRuter.addActionListener(new RuteKnapp());

        //highScores.addActionListener(new HighScoreKnapp());
        highScores.setPreferredSize(new Dimension(250, 80));
        highScores.setBackground(Color.WHITE);
        highScores.setForeground(Color.BLACK);
        highScores.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));

        avslutt.addActionListener(new Avslutt());
        avslutt.setPreferredSize(new Dimension(250, 80));
        avslutt.setBackground(Color.WHITE);
        avslutt.setForeground(Color.BLACK);
        avslutt.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));

        startVindu.setLayout(new GridLayout(6,1));

        startVindu.add(meny);
        startVindu.add(startSpill);
        startVindu.add(vanskelighet);
        startVindu.add(antallRuter);
        startVindu.add(highScores);
        startVindu.add(avslutt);
        startVindu.setPreferredSize(new Dimension(300, 500));
        
        Point plassering = gui.getWindow().getLocationOnScreen();

        startRamme.setLocation((int)plassering.getX()+350, (int)plassering.getY()+225);
        startRamme.setPreferredSize(new Dimension(300, 500));

        startRamme.add(startVindu);

        startRamme.pack();
        startRamme.setVisible(true);

        //Knapper for valg av vanskelighetsgrad
        JButton lett = new JButton("Lett");
        lett.setPreferredSize(new Dimension(250, 50));
        lett.setBackground(Color.WHITE);
        lett.setForeground(Color.BLACK);
        lett.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        lett.addActionListener(new endreVanskelighet(2000));

        JButton middels = new JButton("Middels");
        middels.setPreferredSize(new Dimension(250, 50));
        middels.setBackground(Color.WHITE);
        middels.setForeground(Color.BLACK);
        middels.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        middels.addActionListener(new endreVanskelighet(300));


        JButton vanskelig = new JButton("Vanskelig");
        vanskelig.setPreferredSize(new Dimension(250, 50));
        vanskelig.setBackground(Color.WHITE);
        vanskelig.setForeground(Color.BLACK);
        vanskelig.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        vanskelig.addActionListener(new endreVanskelighet(100));

        highScoreSkjerm.setLayout(new GridLayout(11,1));
        tilbake.addActionListener(new TilbakeTilMeny());
        tilbake.setPreferredSize(new Dimension(250, 30));
        tilbake.setBackground(Color.WHITE);
        tilbake.setForeground(Color.BLACK);
        tilbake.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));

        vanskelighetsgrader.setLayout(new GridLayout(4,1));

        vanskelighetsgrader.add(lett);
        vanskelighetsgrader.add(middels);
        vanskelighetsgrader.add(vanskelig);

        atteRuter = new JButton("8 x 8");
        tolvRuter = new JButton("12 x 12");
        sekstenRuter = new JButton("16 x 16");
        tjuefireRuter = new JButton("24 x 24");

        //atteRuter.setPreferredSize(new Dimension(250, 50));
        //atteRuter.setBackground(Color.WHITE);
        //atteRuter.setForeground(Color.BLACK);
        //atteRuter.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        ////atteRuter.addActionListener(new DimensjonsEndrer(8));
        //
        //tolvRuter.setPreferredSize(new Dimension(250, 50));
        //tolvRuter.setBackground(Color.WHITE);
        //tolvRuter.setForeground(Color.BLACK);
        //tolvRuter.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        ////tolvRuter.addActionListener(new DimensjonsEndrer(12));
//
        //sekstenRuter.setPreferredSize(new Dimension(250, 50));
        //sekstenRuter.setBackground(Color.WHITE);
        //sekstenRuter.setForeground(Color.BLACK);
        //sekstenRuter.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        //sekstenRuter.addActionListener(new DimensjonsEndrer(16));
//
        //tjuefireRuter.setPreferredSize(new Dimension(250, 50));
        //tjuefireRuter.setBackground(Color.WHITE);
        //tjuefireRuter.setForeground(Color.BLACK);
        //tjuefireRuter.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        //tjuefireRuter.addActionListener(new DimensjonsEndrer(24));
//
        //ruteSkjerm.setLayout(new GridLayout(5, 1));
        //ruteSkjerm.add(atteRuter);
        //ruteSkjerm.add(tolvRuter);
        //ruteSkjerm.add(sekstenRuter);
        //ruteSkjerm.add(tjuefireRuter);
    }

    public void visVanskelighetsgrader(){
        startRamme.setVisible(false);
        startRamme.remove(startVindu);
        vanskelighetsgrader.add(tilbake);
        startRamme.add(vanskelighetsgrader);
        startRamme.pack();
        startRamme.setVisible(true);

    }

    public void visHighScores(){
        startRamme.setVisible(false);
        startRamme.remove(startVindu);
        highScoreSkjerm.add(tilbake);
        startRamme.add(highScoreSkjerm);
        startRamme.pack();
        startRamme.setVisible(true);
    }

    public void visStartMeny(){
        startRamme.setVisible(false);
        startRamme.remove(highScoreSkjerm);
        startRamme.remove(ruteSkjerm);
        startRamme.remove(vanskelighetsgrader);
        startRamme.add(startVindu);
        startRamme.pack();
        startRamme.setVisible(true);
    }

    public void visRuteValg(){
        startRamme.setVisible(false);
        startRamme.remove(startVindu);
        ruteSkjerm.add(tilbake);
        startRamme.add(ruteSkjerm);
        startRamme.pack();
        startRamme.setVisible(true);
    }

    public void notShow(){
        show = false;
    }
}
