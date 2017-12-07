package game.model;

import board.model.Board;
import entity.model.PacMan;
import javax.swing.Timer;

/**
 * This class contains all of the components for the game.
 * 
 * @author Michael Kramer
 * @version .1
 */
public class Game {
    private PacMan pacman;
    private Board board;
    private Timer timer = new Timer(250, (ActionEvent) -> { 
        updateEntities();
    });
    
    public Game () {
        this.pacman = new PacMan(1, 1);
        //this.pacman.setSpeed(10);
        this.board = new Board();
        timer.start();
    }
    
    
    
    
    
    
    
    
    public PacMan getPacMan() {
        return this.pacman;
    }
    
    public Board getBoard() {
        return this.board;
    }
    
    
    private void updateEntities() {
        if (validPacManMove()) {
            pacman.move();
        } else {
            // Skip Pacman Move
            // Do Nothing
        }
        
    }

    private boolean validPacManMove() {
        int[][] spaceArray = board.getSpaceArray();
        switch(pacman.getDirection()) {
            case UP:
                if (spaceArray[pacman.getYPos() - 1][pacman.getXPos()] != 1) {
                    return true;
                }
                break;
            case DOWN:
                if(spaceArray[pacman.getYPos() + 1][pacman.getXPos()] != 1) {
                    return true;
                }
                break;
            case LEFT:
                if (spaceArray[pacman.getYPos()][pacman.getXPos() - 1] != 1) {
                    return true;
                }
                break;
            case RIGHT:
                if (spaceArray[pacman.getYPos()][pacman.getXPos() + 1] != 1) {
                    return true;
                }
                break;
        }
        return false;
    }
}
