package view.listeners;
import java.awt.event.*;

import controller.Controller;
import model.Direction;

public class Buttons implements KeyListener{
    Controller controll;

    public Buttons(Controller controll){
        this.controll = controll;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            controll.changeDirection(Direction.UP);
        } 
        
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            controll.changeDirection(Direction.DOWN);
        }

        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            controll.changeDirection(Direction.LEFT);
        }

        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            controll.changeDirection(Direction.RIGHT);
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
