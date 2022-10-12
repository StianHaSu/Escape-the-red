package view.listeners;

import java.awt.event.*;

import controller.Controller;

public class Restart implements ActionListener{
    private Controller controll;
    
    public Restart(Controller cntrl){
        controll = cntrl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controll.newGame();
    }
}
