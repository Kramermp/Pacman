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
    public static enum MovementState {MOVING, STOPPED, SPAWNED};
    private MovementState state = MovementState.SPAWNED;
    private static final Direction START_DIRECTION = Direction.DOWN;
    private Direction direction = Direction.DOWN;
    private double spawnX = 0.00;
    private double spawnY = 0.00;
    private double xPos = 0;
    private double yPos = 0;
    private double speed = .25;
    private Game parentGame;
    
    public Entity(Game parentGame, double xPos, double yPos) {
        this.parentGame = parentGame;
        this.xPos = xPos;
        this.yPos = yPos;
        this.spawnX = xPos;
        this.spawnY = yPos;
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
        setState(MovementState.MOVING);
    }
    
    public void returnToSpawn() {
        this.xPos = spawnX;
        this.yPos = spawnY;
        this.direction = START_DIRECTION;
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
    
    public void setSpeed(double speed) {
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
    
    public void setState(MovementState state) {
        this.state = state;
    }
    public MovementState getState() {
        return this.state;
    }
    
    
}
