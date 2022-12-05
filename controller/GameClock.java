package controller;

public class GameClock implements Runnable{
    private Controller controll;
    private long tid;

    public GameClock(Controller cntrl, long tid){
        controll = cntrl;
        this.tid = tid;
    }

    @Override
    public void run() {
        while (!controll.finished()){
            //Makes ghosts search every other tick
            if (controll.getTicks() % 2 == 0) controll.ghostSearch();

            //Makes a new ghost every 100 tick
            if (controll.getTicks() % 100 == 0) controll.newGhost();

            //Speeds up every 200 ticks
            if (controll.getTicks() % 200 == 0 && tid > 10) tid -= 5; 
            
            try {
                Thread.sleep(tid);
                controll.newTick();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
    }
    
}
