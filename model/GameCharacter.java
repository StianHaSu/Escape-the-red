package model;

import javax.swing.Icon;
import controller.Controller;

public abstract class GameCharacter {
    protected Direction direction = Direction.RIGHT;
    protected int xpos;
    protected int ypos;
    protected Controller controll;
    //protected Icon icon;

    public GameCharacter(int x, int y, Controller ctrl){
        xpos = x;
        ypos = y;
        controll = ctrl;
        //this.icon = icon;
    }

    public abstract void walk();
    /*  When icons have been added
    public Icon getIcon(){
        return icon;
    }
    */
    public void changeDirection(Direction d){
        int nxpos = xpos;
        int nypos = ypos;

        switch (d){
            case LEFT:
                nypos -= 1;
                break;

            case DOWN:
                nxpos += 1;
                break;

            case RIGHT:
                nypos += 1;
                break;
                
            case UP:
                nxpos -= 1;
                break;    
        }

        if (controll.canIWalk(nxpos, nypos))direction = d;
    }

    public int[] getPos(){
        return new int[]{xpos, ypos};
    }

    public abstract String getType();
}
