package view;

import controller.Controller;

public class Obstacle extends PlayingSquare{
    

    public Obstacle(Controller cntrl) {
        super(cntrl);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean isWalkable(){
        return false;
    }
}
