package model;

import controller.*;

public class PacMan extends GameCharacter{
    
    public PacMan(int x, int y, Controller ctrl){
        super(x, y, ctrl);
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Pac";
    }

    @Override
    public void walk(){
        int oldx = xpos;
        int oldy = ypos; 
        
        switch (direction){
            case LEFT:
                ypos -= 1;
                break;

            case DOWN:
                xpos += 1;
                break;

            case RIGHT:
                ypos += 1;
                break;
                
            case UP:
                xpos -= 1;
                break;    
        }

        if (controll.canIWalk(xpos, ypos)){
            //Removes Character from the old posistion
            controll.setBlank(oldx, oldy);  

            //Adds PacMan to the new position;
            controll.putCharacter(xpos, ypos, this);
        } else {
            xpos = oldx;
            ypos = oldy;
        }
    }

    
}
