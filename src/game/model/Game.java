package game.model;

import board.model.Board;
import entity.model.Direction;
import entity.model.Enemy;
import entity.model.PacMan;
import java.util.Random;
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
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Board board;
    private Timer timer = new Timer(40, (ActionEvent) -> { 
        updateEntities();
    });
    
    public Game () {
        this.pacman = new PacMan(this, 1, 1);
        this.enemy1 = new Enemy(this, 1, 1);
        this.enemy2 = new Enemy(this, 1, 2);
        this.enemy3 = new Enemy(this, 1, 3);
        
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
        
        if(validEnemyMove(enemy1)) {
            enemy1.move();
        } else {
            calculateEnemyDirection(enemy1);
        }
        
        if(validEnemyMove(enemy2)) {
            enemy2.move();
        } else {
            calculateEnemyDirection(enemy2);
        }
        
        if(validEnemyMove(enemy3)) {
            enemy3.move();
        } else {
            System.out.println("Not Valid Move for enemy3");
            calculateEnemyDirection(enemy3);
        }
    }

    private boolean validPacManMove() {
        int[][] spaceArray = board.getSpaceArray();
        switch(pacman.getDirection()) {
            case UP:
                //This needs to be handled different because (int) naturally floors
                if (spaceArray[ (int)Math.ceil(pacman.getYPos() - 1)][(int)pacman.getXPos()] != 1) {
                    return true;
                }
                break;
            case DOWN:
                if(spaceArray[(int)pacman.getYPos() + 1][(int)pacman.getXPos()] != 1
                        && spaceArray[(int)pacman.getYPos() + 1][(int)pacman.getXPos()] != 2) {
                    return true;
                }
                break;
            case LEFT:
                //This needs to be handled different because (int) naturally floors
                if (spaceArray[(int)pacman.getYPos()][(int)Math.ceil(pacman.getXPos() - 1)] != 1) { 
                    return true;
                }
                break;
            case RIGHT:
                if (spaceArray[(int)pacman.getYPos()][(int)pacman.getXPos() + 1] != 1) {
                    return true;
                }
                break;
        }
        return false;
    }
    
    private boolean validEnemyMove(Enemy enemyToMove) {
        int[][] spaceArray = board.getSpaceArray();
        switch(enemyToMove.getDirection()) {
            case UP:
                //This needs to be handled different because (int) naturally floors
                if (spaceArray[ (int)Math.ceil(enemyToMove.getYPos() - 1)][(int)enemyToMove.getXPos()] != 1) {
                    return true;
                }
                break;
            case DOWN:
                if(spaceArray[(int)enemyToMove.getYPos() + 1][(int)enemyToMove.getXPos()] != 1
                        && spaceArray[(int)enemyToMove.getYPos() + 1][(int)enemyToMove.getXPos()] != 2) {
                    return true;
                }
                break;
            case LEFT:
                //This needs to be handled different because (int) naturally floors
                if (spaceArray[(int)enemyToMove.getYPos()][(int)Math.ceil(enemyToMove.getXPos() - 1)] != 1) { 
                    return true;
                }
                break;
            case RIGHT:
                if (spaceArray[(int)enemyToMove.getYPos()][(int)enemyToMove.getXPos() + 1] != 1) {
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
    
    public Enemy getEnemy1() {
        return enemy1;
    }
    
    public Enemy getEnemy2() {
        return enemy2;
    }
    
    public Enemy getEnemy3() {
        return enemy3;
    }

    private void calculateEnemyDirection(Enemy enemyToMove) {
        do {
            int number = new Random().nextInt(4);
            if(number == 0) {
                enemyToMove.setDirection(Direction.UP);
            } else if (number == 1) {
                enemyToMove.setDirection(Direction.DOWN);
            } else if (number == 2) {
                enemyToMove.setDirection(Direction.LEFT);
            } else {
                enemyToMove.setDirection(Direction.RIGHT);
            }
        } while (!validEnemyMove(enemyToMove));
    }
}
