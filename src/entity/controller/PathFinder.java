/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import board.model.Board;
import board.model.Space;
import entity.model.Direction;
import entity.model.Enemy;
import entity.model.Entity;
import entity.model.PacMan;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Michael Kramer
 * @version .1
 */
public class PathFinder {
    private static Random rng = new Random();
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final int NONE = 4;
    
    protected Board board;
    protected Enemy enemy;
    protected PacMan pacman;   
    
    public PathFinder(Board board, Enemy enemy, PacMan pacman) {
        this.board = board;
        this.enemy= enemy;
        this.pacman = pacman;
    }
    
    public Direction calculateDirection() {
        switch(enemy.getEnemyNumber()) {
            case 1:
                return pickFlankMove();
                
            case 4:
                return pickRandomMoveType();
                
            default:
                return pickRandomMove(); 
        } 
    }
    
    private Direction pickFlankMove() {
        int currentPacManX = (int) pacman.getXPos();
        int currentPacManY = (int) pacman.getYPos();
        Direction pacmanDirection = pacman.getDirection();
        
        int desiredX = 0;
        int desiredY = 0;
        switch(pacmanDirection) {
            case UP:
                desiredY = currentPacManY + 1;
                desiredX = currentPacManX;
                break;
            case DOWN:
                desiredY = currentPacManY - 1;
                desiredX = currentPacManX;
                break;
            case LEFT:
                desiredY = currentPacManY;
                desiredX = currentPacManX - 1;
                break;
            case RIGHT:
                desiredY = currentPacManY;
                desiredX = currentPacManX + 1;
                break;
            default:
                desiredY = currentPacManY;
                desiredX = currentPacManX;
        }
        
        System.out.println(desiredX + ", " + desiredY);
        return calculateDirectionToSpace(desiredX, desiredY);
    }
    
    private Direction calculateDirectionToSpace(int desiredX, int desiredY) {
        System.out.println((int)enemy.getXPos());
        System.out.println((int) enemy.getYPos());
        Space[][] spaceArray = board.getSpaceArray();
        for(int i = 0; i < spaceArray.length; i++){
            for(int j = 0; j < spaceArray[i].length; j++) {
                if(spaceArray[i][j].isTraversable()) {
                    //System.out.println("Possible");
                }
            }
        }
        
        
        return pickRandomMove(); 
    }
    
    // Moving randomly is having the enemy select a random direction
    // Then moving in that direction. However, we need to check that the
    // Direction moving is not a wall
    private Direction pickRandomMove() {
        ArrayList<Direction> validDirections = getValidDirections();
        
        return validDirections.get(rng.nextInt(validDirections.size()));
    }
    
    private Direction pickRandomMoveType() {
        switch(rng.nextInt(3)) { 
            case 0:   
                
            default:
                return pickRandomMove(); 
        } 
    }
    
    private Direction generateRandomDirection() {;
        int randomDirectionId = rng.nextInt(5);
        Direction randomDirection = Direction.NONE;
        
        switch(randomDirectionId) {
            case UP:
                randomDirection = Direction.UP;
                break;
            case DOWN:
                randomDirection = Direction.DOWN;
                break;
            case LEFT:
                randomDirection = Direction.LEFT;
                break;
            case RIGHT:
                randomDirection = Direction.RIGHT;
                break;
            default:
                randomDirection = Direction.NONE;
        }
        
        return randomDirection;
    }
    
    // A Direction can be said to be valid if it meets to criteria
    // 1. It Does not Take the Enemy Off of the board
    // 2. It does not Take the Enemy onto an invalid space
    // Additionally the enemy should be not accept the inverse of its current
    // direction as valid, unless that is the only valid direction
    private ArrayList<Direction> getValidDirections() {
        boolean upIsValid = false;
        boolean downIsValid = false;
        boolean leftIsValid = false;
        boolean rightIsValid = false;
        ArrayList<Direction> validDirections = new ArrayList<Direction>();
        
        int currentXPos = (int) Math.round(enemy.getXPos());
        int currentYPos = (int) Math.round(enemy.getYPos());
        double speed = enemy.getSpeed();
        
        Space[][] spaceArray = board.getSpaceArray();
        // Checking if a direction is valid is two part
        //First is check if the directions would take the user off of the board
        
        // This branch checks the Up Directions
        // To ensure that the the space is a valid movement space
        if(board.getSpace(currentXPos, (int)(currentYPos - speed)).isTraversable()) {
            upIsValid = true;
        }    
        
        // If the move would not take the enemy off of the board we now need
        // To ensure that the the space is a valid movement space
        if(board.getSpace(currentXPos, (int) Math.ceil(currentYPos + speed)).isTraversable()) {
            downIsValid = true;   
        }
        
        // If the move would not take the enemy off of the board we now need
        // To ensure that the the space is a valid movement space
        if (board.getSpace((int) Math.floor(currentXPos - speed), currentYPos).isTraversable()) {
            leftIsValid = true;
        } 

        //This branch checks the right direction
//         If the move would not take the enemy off of the board we now need;
//         To ensure that the the space is a valid movement space
        rightIsValid = board.getSpace((int) Math.ceil(currentXPos + speed), (currentYPos)).isTraversable();
        
        switch (enemy.getDirection()) {
            case UP:
                if(upIsValid) {
                    validDirections.add(Direction.UP);
                }
                if (!upIsValid && downIsValid) {
                    validDirections.add(Direction.DOWN);
                }
                if (leftIsValid) {
                    validDirections.add(Direction.LEFT);
                }
                if(rightIsValid) {
                    validDirections.add(Direction.RIGHT);
                }
                break;
            case DOWN:
               if(upIsValid && !downIsValid) {
                    validDirections.add(Direction.UP);
                }
                if (downIsValid) {
                    validDirections.add(Direction.DOWN);
                }
                if (leftIsValid) {
                    validDirections.add(Direction.LEFT);
                }
                if(rightIsValid) {
                    validDirections.add(Direction.RIGHT);
                }
                break;
            case LEFT:
                if(upIsValid) {
                    validDirections.add(Direction.UP);
                }
                if (downIsValid) {
                    validDirections.add(Direction.DOWN);
                }
                if (leftIsValid) {
                    validDirections.add(Direction.LEFT);
                }
                if(rightIsValid && !leftIsValid) {
                    validDirections.add(Direction.RIGHT);
                }
                break;
            case RIGHT:
                if(upIsValid) {
                    validDirections.add(Direction.UP);
                }
                if (downIsValid) {
                    validDirections.add(Direction.DOWN);
                }
                if (leftIsValid && !rightIsValid && !upIsValid && !downIsValid) {
                    validDirections.add(Direction.LEFT);
                }
                if(rightIsValid) {
                    validDirections.add(Direction.RIGHT);
                }
                break;
            }
        if(validDirections.isEmpty()) {
            validDirections.add(Direction.NONE);
        }
        
        
        return validDirections;
    }
    
    public boolean isValidDirection(Direction direction) {
        
        return getValidDirections().contains(direction);
    }
  
}
