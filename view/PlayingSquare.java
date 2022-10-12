package view;

import javax.swing.JLabel;

import controller.Controller;
import model.GameCharacter;

public class PlayingSquare extends JLabel{
    private boolean hasPacman = false;
    private boolean hasTreasure = false;
    private boolean hasGhost = false;

    private boolean walkable = true;

    private Controller control;

    public PlayingSquare(Controller cntrl){
        control = cntrl;
    }

    //Should add an array or similar in the case that multiple characters are on the same square

    public boolean checkPacman(){
        return hasPacman;
    }

    public boolean checkGhost(){
        return hasGhost;
    }

    public boolean hasTreasure(){
        return hasTreasure;
    }

    public void setPacman(){
        if (hasGhost) control.gameOver();
        hasPacman = true;
    }

    public void setGhost(){
        if (hasPacman) control.gameOver();
        hasGhost = true;
    }

    public void setTreasure(){
        hasTreasure = true;
    }

    public void removeTeasure(){
        hasTreasure = false;
    }

    public void removePacman(){
        hasPacman = false;
    }

    public void removeGhost(){
        hasGhost = false;
    }

    public boolean isWalkable(){
        return walkable;
    }

    public void makeObstacle(){
        walkable = false;
    }

}
