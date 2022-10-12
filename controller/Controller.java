package controller;

import view.PlayingBoard;
import view.PlayingSquare;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;

import controller.levels.LevelOne;
import model.*;


public class Controller {
    private PlayingBoard pb;
    private PlayingSquare[][] squares;
    private PacMan pac;
    private Ghost ghost1, ghost2, ghost3;
    private ArrayList<Ghost> ghosts = new ArrayList<>();
    private LinkedList<GameCharacter> characters = new LinkedList<>();
    private Thread gameClock;
    private boolean gameFinished = false;
    private int ticks = 0;
    private LevelOne lvl;
    private boolean visualise = false;
    private Color backgroundColor = new Color(0, 0, 56);

    private ArrayList<PlayingSquare> visual = new ArrayList<>();

    public Controller(){
        pb = new PlayingBoard(this);
        squares = pb.getBoard();

        lvl = new LevelOne(this);
        pb.drawObstacles(lvl.obstacles2());

        pac = new PacMan(20, 20, this);
        characters.add(pac);

        ghost1 = new Ghost(1, 1, this);
        characters.addLast(ghost1);

        //ghost2 = new Ghost(0, 37, this);
        //characters.addLast(ghost2);
//
        //ghost3 = new Ghost(29, 29, this);
        //characters.addLast(ghost3);

        ghosts.add(ghost1);
        //ghosts.add(ghost2);
        //ghosts.add(ghost3);

        pac.walk();

        gameClock = new Thread(new GameClock(this, 40));
        gameClock.start();

    }

    public void setBlank(int x, int y){
        pb.removeCharacter(squares[x][y]);
        squares[x][y].removePacman();
        squares[x][y].removeGhost();
    }

    public void putCharacter(int x, int y, GameCharacter gc){
        if (gc.getType().equals("Pac")){ 
            squares[x][y].setPacman();
        }
        else squares[x][y].setGhost();

        pb.putCharacter(squares[x][y], gc);
    }

    public void changeDirection(Direction d){
        pac.changeDirection(d);
    }

    public void newTick() throws InterruptedException{
        for (GameCharacter gc : characters){
            gc.walk();
        }
        ticks++;
    }

    public void ghostSearch(){
        for (Ghost g : ghosts){
            g.findPac();
        }
    }

    public int getTicks(){
        return ticks;
    }

    public boolean finished(){
        return gameFinished;
    }

    public boolean canIWalk(int x, int y){
        return squares[x][y].isWalkable();
    } 

    public void gameOver(){
        gameFinished = true;
    }

    public void newGame(){
        if (!gameFinished) return;

        int[] pacpos = pac.getPos();

        PlayingSquare pacsqr = squares[pacpos[0]][pacpos[1]];
        pb.removeCharacter(pacsqr);
        pacsqr.removePacman();

        int[] ghospos = ghost1.getPos();
        PlayingSquare ghosqr = squares[ghospos[0]][ghospos[1]];
        pb.removeCharacter(ghosqr);
        pacsqr.removePacman();

        characters.removeAll(characters);
        ghosts.removeAll(ghosts);

        pb.stop();
        pb = new PlayingBoard(this);
        pb.drawObstacles(lvl.obstacles2());
        squares = pb.getBoard();

        pac = new PacMan(20, 20, this);
        characters.add(pac);

        ghost1 = new Ghost(1, 1, this);
        characters.addLast(ghost1);
        ghosts.add(ghost1);

        pac.walk();

        gameFinished = false;
        visualise = true;
        gameClock = new Thread(new GameClock(this, 50));
        gameClock.start();

    }

    public Stack<int[]> search(int x, int y){
        if (squares[x][y].checkPacman()) {
            gameFinished = true;
            return null;
        }

        LinkedList<int[]> queue = new LinkedList<>();
        ArrayList<int[]> visited = new ArrayList<>();
        ArrayList<String> codes = new ArrayList<>();
        HashMap<int[], int[]> graph = new HashMap<>(); 
        Stack<int[]> path = new Stack<>();

        int[] pos = new int[]{x, y};
        int[] first = pos;
        
        queue.add(pos);
        visited.add(pos);
        codes.add(pos[0]+","+pos[1]);

        int[] last = null;
        while (!squares[pos[0]][pos[1]].checkPacman()){
            if (visualise){
                for (PlayingSquare s : visual){
                    s.setBackground(backgroundColor);
                }
                visual.removeAll(visual);
            }

            pos = queue.removeFirst();

            if (pos[0]-1 > 0 && squares[pos[0]-1][pos[1]].isWalkable()){
                int[] newV = new int[] {pos[0]-1,pos[1]};
                String code = (pos[0]-1)+","+pos[1];
                if (!codes.contains(code)){
                    codes.add(code);
                    visited.add(newV);
                    queue.addLast(newV);

                    graph.put(newV, pos);
                    last = newV;

                    if (squares[newV[0]][newV[1]].checkPacman()) break;
                    if (visualise) visual.add(squares[newV[0]][newV[1]]);
                }
            }

            if (pos[0]+1 < squares.length && squares[pos[0]+1][pos[1]].isWalkable()){
                int[] newV = new int[] {pos[0]+1 ,pos[1]};
                String code = (pos[0]+1)+","+pos[1];
                if (!codes.contains(code)){
                    codes.add(code);
                    visited.add(newV);
                    queue.addLast(newV);

                    graph.put(newV, pos);
                    last = newV;

                    if (squares[newV[0]][newV[1]].checkPacman()) break;
                    if (visualise) visual.add(squares[newV[0]][newV[1]]);
                }
            }

            if (pos[1]-1 > 0 && squares[pos[0]][pos[1]-1].isWalkable()){
                int[] newV = new int[] {pos[0] , pos[1]-1};
                String code = pos[0]+","+(pos[1]-1);
                if (!codes.contains(code)){
                    codes.add(code);
                    visited.add(newV);
                    queue.addLast(newV);

                    graph.put(newV, pos);
                    last = newV;

                    if (squares[newV[0]][newV[1]].checkPacman()) break;
                    if (visualise) visual.add(squares[newV[0]][newV[1]]);
                }
            }

            if (pos[1]+1 < squares[0].length && squares[pos[0]][pos[1]+1].isWalkable()){
                int[] newV = new int[] {pos[0],pos[1]+1};
                String code = pos[0]+","+(pos[1]+1);
                if (!codes.contains(code)){
                    codes.add(code);
                    visited.add(newV);
                    queue.addLast(newV);

                    graph.put(newV, pos);
                    last = newV;

                    if (squares[newV[0]][newV[1]].checkPacman()) break;
                    if (visualise) visual.add(squares[newV[0]][newV[1]]);
                }
            }

        }

        if (visualise){
            for (PlayingSquare s : visual){
                s.setBackground(Color.ORANGE);
            }
        }

        int[] next = last;
        while (next != first){
            path.push(next);
            next = graph.get(next);
            if (visualise) squares[next[0]][next[1]].setBackground(Color.GREEN);
        }

        path.push(first);

        if (path.empty()) gameFinished = true;
        
        return path;
    }

    public void visualiser(){
        visualise = true;
    }
}
