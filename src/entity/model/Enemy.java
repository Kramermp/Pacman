/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.model;

import entity.controller.AStarPathFinder;
import entity.controller.PathFinder;
import game.model.Game;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/**
 *
 * @author Michael Kramer
 * @version .1
 */
public class Enemy extends Entity  {
    public enum PursuitType {CHASE, FLEE}
    private PursuitType pursuitType = PursuitType.CHASE;
    private PathFinder pathFinder;
    private static int currentEnemyNumber = 1;
    private int enemyNumber = 1;
    private int numberOfValidMoves = 0;
    private Timer timer = new Timer(200, (ActionEvent ae) -> { 
        this.setDirection(pathFinder.calculateDirection());
    });
    
    private long fleeStart = 0;
    
    public Enemy() {
        super();
        super.setSpeed(10);
        super.setDirection(Direction.RIGHT);
        this.enemyNumber = currentEnemyNumber;
        currentEnemyNumber++;
    }
    
    public Enemy(Game parentGame, double xPos, double yPos, int level) {
        super(parentGame, xPos, yPos);
        super.setSpeed(.125);
        this.enemyNumber = currentEnemyNumber;
        switch (enemyNumber) {
            case 1:
                this.pathFinder = new AStarPathFinder(parentGame.getBoard(), this, parentGame.getPacMan());
                break;
            case 2:
                this.pathFinder = new AStarPathFinder(parentGame.getBoard(), this, parentGame.getPacMan());
                break;
            case 3:
                // Fall Through
            case 4:
                // Fall Through
            default:
                this.pathFinder = new PathFinder(parentGame.getBoard(), this, parentGame.getPacMan());
        }
        currentEnemyNumber++;
        timer.start();
    }
    
    public int getEnemyNumber() {
        return this.enemyNumber;
    }
    
    // This is how enemies move
    @Override
    public void move() {
        if(this.pursuitType == PursuitType.FLEE
                && System.currentTimeMillis() - fleeStart > 5000) {
            setPursuitType(pursuitType.CHASE);
        }
        if(pathFinder != null) {
            if(pathFinder.isValidDirection(super.getDirection())) {
            //System.out.println(super.getDirection()+ " is valid.");
            super.move();
            } else {
                this.setDirection(pathFinder.calculateDirection());
                timer.restart();
            }
        } else {
            
        }
                
        super.move();
        //Calculate next direction
        
    }
    
    public static void resetEnemyNumber() {
        Enemy.currentEnemyNumber = 1;
    }
    
    public void setPursuitType(PursuitType type) {
        if(type == PursuitType.FLEE) {
            fleeStart = System.currentTimeMillis();
        }
        this.pursuitType = type;
    }
    
    public PursuitType getPursuitType() {
        return this.pursuitType;
    }
    
}
