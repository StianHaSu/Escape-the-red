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
            if (controll.getTicks() % 5 == 0) controll.ghostSearch();
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
