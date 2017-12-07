package game.model;

import board.model.Board;
import entity.model.Direction;
import entity.model.PacMan;
import javax.swing.Timer;

/**
 * This class contains all of the components for the game.
 * 
 * @author Michael Kramer
 * @version .1
 */
public class Game {
    private int score = 0;
    private PacMan pacman;
    private Board board;
    private Timer timer = new Timer(40, (ActionEvent) -> { 
        updateEntities();
    });
    
    public Game () {
        this.pacman = new PacMan(this, 1, 1);
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
            checkSpace();
        } else {
            // Skip Pacman Move
            // Do Nothing
            pacman.setDirection(Direction.NONE);
        }
    }

    private boolean validPacManMove() {
        int[][] spaceArray = board.getSpaceArray();
        switch(pacman.getDirection()) {
            case UP:
                //This needs to be handled different because (int) naturally floors
                if (spaceArray[ (int)Math.ceil(pacman.getYPos() - 1)][(int)pacman.getXPos()] != 1
                        || spaceArray[ (int)Math.ceil(pacman.getYPos() - 1)][(int)pacman.getXPos()] != 2) {
                    return true;
                }
                break;
            case DOWN:
                if(spaceArray[(int)pacman.getYPos() + 1][(int)pacman.getXPos()] != 1 
                        || spaceArray[ (int)Math.ceil(pacman.getYPos() - 1)][(int)pacman.getXPos()] != 2) {
                    return true;
                }
                break;
            case LEFT:
                //This needs to be handled different because (int) naturally floors
                if (spaceArray[(int)pacman.getYPos()][(int)Math.ceil(pacman.getXPos() - 1)] != 1 
                        || spaceArray[ (int)Math.ceil(pacman.getYPos() - 1)][(int)pacman.getXPos()] != 2) { 
                    return true;
                }
                break;
            case RIGHT:
                if (spaceArray[(int)pacman.getYPos()][(int)pacman.getXPos() + 1] != 1 
                        || spaceArray[ (int)Math.ceil(pacman.getYPos() - 1)][(int)pacman.getXPos()] != 2) {
                    return true;
                }
                break;
        }
        return false;
    }
    
    private void checkSpace() {
        int[][] spaceArray = board.getSpaceArray();
        if(spaceArray[(int)pacman.getYPos()][(int)pacman.getXPos()] == 0) {
            spaceArray[(int)pacman.getYPos()][(int)pacman.getXPos()] = 4;
            pelletEaten();
        } else if (spaceArray[(int)pacman.getYPos()][(int)pacman.getXPos()] == 3) {
            spaceArray[(int)pacman.getYPos()][(int)pacman.getXPos()] = 4;
            powerPelletEaten();
        }
        
    }
    
    private void pelletEaten() {
        score+=10;
    }
    
    private void powerPelletEaten() {
        score+=20;
        //Do Stuff
    }
}
