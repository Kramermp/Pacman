package game.model;

import board.model.Board;
import entity.model.Direction;
import entity.model.Enemy;
import entity.model.PacMan;
import game.controller.GameCntl;
import java.awt.Point;
import java.util.Random;
import javax.swing.Timer;

/**
 * This class contains all of the components for the game.
 * 
 * @author Michael Kramer
 * @version .1
 */
public class Game {
    private static final Point pacmanSpawn = new Point (9, 7);
    private static final Point enemySpawn = new Point (9, 5);
    private GameCntl parentCntl;
    private int score = 0;
    private PacMan pacman;
    private Enemy[] enemies;
    private Board board;
    private Timer timer = new Timer(40, (ActionEvent) -> { 
        updateEntities();
    });
    
    
    public Game (GameCntl parentCntl, int level) {
        this.parentCntl = parentCntl;
        this.pacman = new PacMan(this, pacmanSpawn.x, pacmanSpawn.y);
        this.enemies = new Enemy[] {new Enemy(this, enemySpawn.x, enemySpawn.y, level),
                new Enemy(this, enemySpawn.x, enemySpawn.y, level),
                new Enemy(this, enemySpawn.x, enemySpawn.y, level),
                new Enemy(this, enemySpawn.x, enemySpawn.y, level)
        };
        
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
        
        for(int i = 0; i < enemies.length; i++) {
            if(validEnemyMove(enemies[i])) {
            enemies[i].move();
            } else {
                calculateEnemyDirection(enemies[i]);
            }
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
        board.pelletEaten();
        if(board.boardIsCleared()) {
            System.out.println("Board Cleared");
        } else {
            // Do Nothing
        }
    }
    
    private void checkBoardClear() {
        
    }
    
    private void powerPelletEaten() {
        score+=20;
        board.pelletEaten();
        //Do Stuff
    }
    
    public Enemy[] getEnemies() {
        return enemies;
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
