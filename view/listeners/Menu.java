package view.listeners;

import java.awt.event.*;

import controller.Controller;
import view.PlayingBoard;

public class Menu implements ActionListener{
    private Controller controll;
    
    public Menu(Controller c){
        controll = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controll.openMenu();
    }
    
}
