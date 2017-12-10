package game.model;

import board.model.Board;
import board.model.BoardFactory;
import board.model.Space;
import entity.controller.PathFinder;
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
    private static Point pacmanSpawn = new Point (9, 7);
    private static Point enemySpawn = new Point (0, 0);
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
    
    private PathFinder pathFinder;
    
    
    public Game (GameCntl parentCntl, int level) {
        this.parentCntl = parentCntl;
        this.board = new BoardFactory().getBoard();
        Entity.maxX = board.getWidth();
        Entity.maxY = board.getHeight();
        
        
        pacmanSpawn = board.getPlayerSpawn();
        enemySpawn = board.getEnemySpawn();
        this.level = level;
        this.pacman = new PacMan(this, pacmanSpawn.x, pacmanSpawn.y);
        Enemy.resetEnemyNumber();
        this.enemies = new Enemy[] {new Enemy(this, enemySpawn.x, enemySpawn.y, level),
                new Enemy(this, enemySpawn.x, enemySpawn.y, level),
                new Enemy(this, enemySpawn.x, enemySpawn.y, level),
                new Enemy(this, enemySpawn.x, enemySpawn.y, level)               
        };
        
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
        }
        
        for(int i = 0; i < enemies.length; i++) {
            enemies[i].move();
        }
        
        //checkCollision();
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
        return true;
    }
    
    private void checkSpace() {
        Space pacmanSpace = board.getSpace((int)pacman.getXPos(), (int)pacman.getYPos());
        if( pacmanSpace.spaceType == 0) {
            pacmanSpace.spaceType = 4;
            pelletEaten();
        } else if (pacmanSpace.spaceType == 3) {
            pacmanSpace.spaceType = 4;
            powerPelletEaten();
        }
    }
    
    private void pelletEaten() {
        score+=10;
        board.pelletEaten();
        if(board.isCleared()) {
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
