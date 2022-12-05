package controller;

import java.util.concurrent.CountDownLatch;

import model.Ghost;

public class Clock implements Runnable{
    private Controller controll;
    private Ghost ghost;
    private CountDownLatch count;
    
    public Clock(Controller controll, Ghost g, CountDownLatch cdl){
        this.controll = controll;
        ghost = g;
        count = cdl;
    }

    @Override
    public void run() {
        ghost.findPac();
    }  
}
