package game.model;

import board.model.Board;
import entity.model.Direction;
import entity.model.Enemy;
import entity.model.Entity;
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
    public static enum GameState {PLAYING, WAITING, OVER};
    private GameState state = GameState.WAITING;
    private static final Point pacmanSpawn = new Point (9, 7);
    private static final Point enemySpawn = new Point (9, 5);
    private GameCntl parentCntl;
    private int score = 0;
    private int level = 0;
    private PacMan pacman;
    private Enemy[] enemies;
    private Board board;
    private Timer timer = new Timer(40, (ActionEvent) -> { 
        updateEntities();
    });
    
    private int playerLives = 3;
    
    
    public Game (GameCntl parentCntl, int level) {
        this.parentCntl = parentCntl;
        this.level = level;
        this.pacman = new PacMan(this, pacmanSpawn.x, pacmanSpawn.y);
        this.enemies = new Enemy[] {new Enemy(this, enemySpawn.x, enemySpawn.y, level),
                new Enemy(this, enemySpawn.x, enemySpawn.y, level),
                new Enemy(this, enemySpawn.x, enemySpawn.y, level),
                new Enemy(this, enemySpawn.x, enemySpawn.y, level)
        };
        
        //this.pacman.setSpeed(10);
        this.board = new Board();
        this.startLevel();
    }
    
    private void startLevel() {
        System.out.println("Starting Level " + level);
        this.setState(GameState.WAITING);
    }
    
    public PacMan getPacMan() {
        return this.pacman;
    }
    
    public Board getBoard() {
        return this.board;
    }
    
    private void updateEntities() {
        if (validMove(pacman)) {
            pacman.move();
            checkSpace();
        } else {
            // Skip Pacman Move
            // Do Nothing
            //pacman.setDirection(Direction.NONE);
            pacman.setState(Entity.MovementState.STOPPED);
        }
        
        for(int i = 0; i < enemies.length; i++) {
            if(validMove(enemies[i])) {
            enemies[i].move();
            } else {
                enemies[i].setState(Entity.MovementState.STOPPED);
                calculateEnemyDirection(enemies[i]);
            }
        }
        checkCollision();
    }
    
    private void checkCollision() {
        for(int i = 0; i < enemies.length; i++){
            if(Math.abs(pacman.getXPos() - enemies[i].getXPos()) < .5) {
                if(Math.abs(pacman.getYPos() - enemies[i].getYPos()) < .5) {
                    System.out.println("Collision");
                    pacman.returnToSpawn();
                    playerLives--;
                    if(playerLives == 0){
                        gameOver();
                    }
                }
            }  
        }
    }
    
    private void gameOver() {
        parentCntl.gameOver(score);
    }

    private boolean validMove(Entity entityToMove) {
        int[][] spaceArray = board.getSpaceArray();
        
        switch(entityToMove.getDirection()) {
            case UP:
                //This needs to be handled different because (int) naturally floors
                if (spaceArray[ (int)Math.round(entityToMove.getYPos() - 1)][(int)entityToMove.getXPos()] != 1) {
                    return true;
                }
                break;
            case DOWN:
                if(spaceArray[(int)entityToMove.getYPos() + 1][(int)entityToMove.getXPos()] != 1
                        && spaceArray[(int)entityToMove.getYPos() + 1][(int)entityToMove.getXPos()] != 2) {
                    return true;
                }
                break;
            case LEFT:
                //This needs to be handled different because (int) naturally floors
                if (spaceArray[(int)entityToMove.getYPos()][(int)Math.ceil(entityToMove.getXPos() - 1)] != 1) { 
                    return true;
                }
                break;
            case RIGHT:
                if (spaceArray[(int)entityToMove.getYPos()][(int)entityToMove.getXPos() + 1] != 1) {
                    return true;
                }
                break;
            case NONE:
                //Do Nothing
                return true;
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
            levelCleared();
        } else {
            // Do Nothing
        }
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
        } while (!validMove(enemyToMove));
    }

    private void levelCleared() {
       pacman.returnToSpawn();
       for(int i = 0; i < enemies.length; i++) {
           enemies[i].returnToSpawn();
       }
       board.reset();
       level++;
       startLevel();
    }
    
    public void setState(GameState state) {
        this.state = state;
        switch(state) {
            case PLAYING:
                timer.restart();
                break;
            case WAITING:
                timer.stop();
                break;
            case OVER:
                
                break;      
        }
    }
    
    public GameState getState() {
        return this.state;
    }
    
    public int getLevel() {
        return level;
    }
        
}
