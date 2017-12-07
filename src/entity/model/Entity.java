package entity.model;

import com.sun.javafx.scene.traversal.Direction;

/**
 * This abstract class is the class the different entities will extend. Where
 * entities are PacMan, and the enemies.
 * 
 * @author Michael Kramer
 * @version .1
 */
public abstract class Entity {
    private Direction direction = Direction.DOWN;
    private int xPos = 0;
    private int yPos = 0;
    private int speed = 1;
    
    public Entity(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public void move() {
        switch (direction) {
            case UP:
                yPos-=speed;
                break;
            case DOWN:
                yPos+=speed;
                break;
            case LEFT:
                xPos-=speed;
                break;
            case RIGHT:
                xPos+=speed;
                break;
        }
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public int getXPos() {
        return this.xPos;
    }
    
    public int getYPos() {
        return this.yPos;
    }
    public int getSpeed() {
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
