package view;

import java.awt.*;
import javax.swing.*;

import controller.Controller;
import model.GameCharacter;
import view.listeners.Buttons;
import view.listeners.Menu;
import view.listeners.Restart;

public class PlayingBoard{
    private PlayingSquare[][] board = new PlayingSquare[30][38];   
    private JFrame frame = new JFrame("Pacman");
    
    private JPanel boardP = new JPanel();
    private JPanel skjerm = new JPanel();
    private JPanel top = new JPanel();
    
    private Controller controll;
    private Color backgroundColor = new Color(0, 0, 56);
    
    private JButton restart = new JButton("Restart");
    private JButton menu = new JButton("Menu");

    public PlayingBoard(Controller controll){
        this.controll = controll;

        try{
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception e){System.out.println(e);}

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 1000));

        skjerm.setLayout(new BorderLayout());

        boardP.setLayout(new GridLayout(30, 38));
        boardP.setPreferredSize(new Dimension(1000, 850));

        restart.addActionListener(new Restart(controll));
        restart.setPreferredSize(new Dimension(500, 50));
        restart.setBackground(Color.BLACK);
        restart.setForeground(Color.WHITE);
        restart.setOpaque(true);

        menu.addActionListener(new Menu(controll));
        menu.setPreferredSize(new Dimension(500, 50));
        menu.setOpaque(true);
        menu.setBackground(Color.BLACK);
        menu.setForeground(Color.WHITE);
     

        for (int i = 0; i < board.length; i++){
            for (int e = 0; e < board[0].length; e++){
                PlayingSquare newField = new PlayingSquare(controll);
                
                //newField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                newField.setOpaque(true);
                newField.setBackground(backgroundColor);
                newField.setPreferredSize(new Dimension(30,30));
                board[i][e] = newField;
                boardP.add(newField);
            }
        }

        frame.addKeyListener(new Buttons(controll));

        top.setLayout(new BorderLayout());
        top.add(restart, BorderLayout.EAST);
        top.add(menu, BorderLayout.WEST);

        skjerm.add(boardP, BorderLayout.CENTER);
        skjerm.add(top, BorderLayout.NORTH);

        frame.add(skjerm);
        frame.pack();
        frame.setVisible(true);

        frame.requestFocusInWindow();
        
    }

    public PlayingSquare[][] getBoard(){
        //Returns the board, so that the controller can change it.
        return board;
    }

    public void removeCharacter(PlayingSquare sqr){
        sqr.removePacman();
        sqr.removeGhost();
        sqr.setBackground(backgroundColor);
        sqr.setIcon(null);
    }

    public void putCharacter(PlayingSquare sqr, GameCharacter gc){
        //Temporary for testing:
        if (gc.getType().equals("Pac")) {
            sqr.setPacman();
            sqr.setBackground(Color.YELLOW);

        }

        else {
            sqr.setGhost();
            sqr.setBackground(Color.RED);
        }
        //When i have added icons:
        //sqr.setIcon(gc.getIcon());
    }

    public void drawObstacles(int[][] obstacles){
        int counter1 = 0;
        for (int[] e : obstacles){
            int counter2 = 0;
            for (int i : e){
                if (i == 1){
                    PlayingSquare sqr = board[counter1][counter2];
                    sqr.makeObstacle();
                    sqr.setOpaque(true);
                    sqr.setBackground(Color.WHITE);

                    if (counter1 == 0 || counter2 == 0 || counter1 == 29 || counter2 == 37) sqr.setBackground(Color.BLACK);
                }
                
                counter2++;
            }

            counter2 = 0;
            counter1++;
        } 
    }

    public void restart(){
        frame.requestFocusInWindow();
    }

    public void stop(){
        frame.dispose();
    }

    public JFrame getWindow(){
        return frame;
    }
}
