package entity.model;

import game.model.Game;

/**
 * This abstract class is the class the different entities will extend. Where
 * entities are PacMan, and the enemies.
 * 
 * @author Michael Kramer
 * @version .1
 */
public abstract class Entity {
    private double frameCounter = 2;
    private Direction direction = Direction.DOWN;
    private double xPos = 0;
    private double yPos = 0;
    private double speed = .25;
    private Game parentGame;
    
    public Entity(Game parentGame, double xPos, double yPos) {
        this.parentGame = parentGame;
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public void move() {
        switch (direction) {
            case UP:
                yPos-=speed;
                xPos = (int) xPos;
                break;
            case DOWN:
                yPos+=speed;
                xPos = (int) xPos;
                break;
            case LEFT:
                xPos-=speed;
                yPos = (int) yPos;
                break;
            case RIGHT:
                xPos+=speed;
                yPos = (int) yPos;
                break;
        }
        frameCounter = 1;
    }
    
    public void incrementFrame() {
        
    }
    
    public double getFrameCounter() { 
        return this.frameCounter;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public double getXPos() {
        return this.xPos;
    }
    
    public double getYPos() {
        return this.yPos;
    }
    public double getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setXPos(int xPos) {
        this.xPos = xPos;
    }
    
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    
}
