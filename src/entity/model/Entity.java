package entity.model;

import board.model.Board;
import board.model.Space;
import game.model.Game;

/**
 * This abstract class is the class the different entities will extend. Where
 * entities are PacMan, and the enemies.
 * 
 * @author Michael Kramer
 * @version .1
 */
public abstract class Entity {

    protected Board board;
    public static enum MovementState {MOVING, STOPPED, SPAWNED};
    private MovementState state = MovementState.SPAWNED;
    private static final Direction START_DIRECTION = Direction.UP;
    private Direction direction = Direction.DOWN;
    private Direction lastDirection = Direction.NONE;
    private double spawnX = 0.00;
    private double spawnY = 0.00;
    
    public static double maxX;
    public static double minX = -1;
    public static double maxY;
    public static double minY = -1;
    
    protected double xPos = 0;
    protected double yPos = 0;
    protected double speed = 55;
    private Game parentGame;
    
    public int moveOptions;
    
    public Entity() {
        
    }
    
    public Entity(Game parentGame, double xPos, double yPos) {
        this.parentGame = parentGame;
        this.board = parentGame.getBoard();
        this.xPos = xPos;
        this.yPos = yPos;
        this.spawnX = xPos;
        this.spawnY = yPos;
    }
    
    public void move() {
        if(board != null) {
            moveAcrossBoard();
        } else {
            //System.out.println(speed);
            //System.out.println("Moving without board");
            moveWithoutBoard();
        }
        
        if(yPos < minY && xPos < minY) {
            yPos = 0;
            xPos = 0;
        }
        
        if(yPos > maxY && xPos > maxX) {
            yPos = 0;
            xPos = 0;
        }
        
        if(yPos < minY ) {
            yPos = maxY;
        } else if (yPos >= maxY) {
            yPos = minY;
        } else {
            System.out.println("Y:" + yPos);
            System.out.println("maxY:" + maxY);
            System.out.println("minY:" + minY);
        }
        
        if(xPos < minX) {
            xPos = maxX;
        } else if (xPos > maxX){
            xPos = minX;
        } else {
            System.out.println("X:" + xPos);
            System.out.println("maxX: " + maxX);
            System.out.println("MinX: " + minX);
        }

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
        switch(direction) {
            case UP:
            case DOWN:
                xPos = (int) Math.round(xPos);
                break;
            case LEFT:
            case RIGHT:
                yPos = (int) Math.round(yPos);
                break;
        }
        this.lastDirection = this.direction;
        this.direction = direction;
    }
    
    public void setMovementState(MovementState state) {
        this.state = state;
        if(state == MovementState.STOPPED) {
            xPos = (int) xPos;
            yPos = (int) yPos;
        }
    }
    public MovementState getState() {
        return this.state;
    }
    
    
    private void moveAcrossBoard() {
        switch (direction) {
            case UP:
                if (board.getSpace((int)xPos, (int) Math.floor(yPos - speed)).spaceType != Space.WALL) {
                    yPos-=speed;
                    xPos = Math.round(xPos);
                } else {
                    this.setMovementState(MovementState.STOPPED);
                }
                break;
            case DOWN:
               if (board.getSpace((int)xPos, (int) Math.ceil(yPos + speed)).spaceType != Space.WALL) {
                    yPos+=speed;
                    xPos = Math.round(xPos);
                } else {
                   this.setMovementState(MovementState.STOPPED);
                }
                break;
            case LEFT:
                if (board.getSpace((int) Math.floor(xPos - speed), (int) yPos).spaceType != Space.WALL) {
                    xPos-=speed;
                    yPos = Math.round(yPos);
                } else {
                    this.setMovementState(MovementState.STOPPED);
                }
                break;
            case RIGHT:
                if (board.getSpace((int) Math.ceil(xPos + speed),(int) yPos ).spaceType != Space.WALL) {
                    xPos+=speed;
                    yPos = Math.round(yPos);
                } else {
                    this.setMovementState(MovementState.STOPPED);
                }
                break;
            case NONE:
        }
    }
        
    private void moveWithoutBoard() {
        switch (direction) {
            case UP:
                    yPos-=speed;
                    xPos = Math.round(xPos);
                break;
            case DOWN:
                    yPos+=speed;
                    xPos = Math.round(xPos);
                break;
            case LEFT:
                    xPos-=speed;
                    yPos = Math.round(yPos);
                break;
            case RIGHT:
                    xPos+=speed;
                    yPos = Math.round(yPos);
                break;
            case NONE:
        }
    }
            
}
