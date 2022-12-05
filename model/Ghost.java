package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import controller.Controller;



public class Ghost extends GameCharacter{

    private Stack<int[]> path = null;
    public Ghost(int x, int y, Controller ctrl) {
        super(x, y, ctrl);
    }
    

    //Width first search
    public void findPac(){
        path = controll.search(xpos, ypos);
    }


    @Override
    public String getType() {
        return "Ghost";
    }   

    @Override
    public void walk(){
        if (path == null || path.isEmpty()){ 
            return;
        }


        int oldx = xpos;
        int oldy = ypos;

        controll.setBlank(oldx, oldy);  

        int[] nextStep = path.pop();
        xpos = nextStep[0];
        ypos = nextStep[1];

        controll.putCharacter(xpos, ypos, this);
    } 
}
