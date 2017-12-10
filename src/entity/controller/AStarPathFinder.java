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
import entity.model.PacMan;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * @author Michael Kramer
 */
public class AStarPathFinder extends PathFinder {
    public static final int TRAVEL_COST = 10;
    
    private static int pathFinderCount = 1;
    private int pathFinderId = 1;
    
    private int startX = 0;
    private int startY = 0;
    
    private int endX = 0;
    private int endY = 0;
    
    private int gridHeight = 0;
    private int gridWidth = 0;  
    
    private ArrayList<Cell> availableCells = new ArrayList<Cell>();
    private ArrayList<Cell> closedCells = new ArrayList<Cell>();
    private ArrayList<Cell> path = new ArrayList<Cell>();
    
    Cell selectedCell;
    Cell currentCell;
    
    private boolean foundPath = false;
    private boolean hasPath = true;
    
    public AStarPathFinder(Board board, Enemy enemy, PacMan pacman) {
        super(board, enemy, pacman);
        this.pathFinderId = pathFinderCount;
        pathFinderCount++;
        
        gridHeight = board.getHeight();
        gridWidth = board.getWidth();
    }
    
    public Direction calculateDirection() {

        if(Math.abs(pacman.getYPos() - enemy.getYPos()) <= 1
                && Math.abs(pacman.getXPos() - enemy.getXPos()) <= 1) {
            System.out.println("Off By One");
            if(pacman.getYPos() < enemy.getYPos()) {
                return Direction.UP;
            } else if (pacman.getYPos() > enemy.getYPos()) {
                return Direction.DOWN;
            } else {
                // pacman.getYPos() == enemy.getYPos()
                // Do Nothing
            }
            
            if(pacman.getXPos() < enemy.getXPos()) {
                return Direction.LEFT;
            } else if (pacman.getXPos() > enemy.getXPos()) {
                return Direction.RIGHT;
            } else {
                // pacman.getXPos() == enemy.getXPos()
                // Do Nothing
            }
            
            return Direction.NONE;
            
        }
        
        startX = (int) enemy.getXPos();
        startY = (int) enemy.getYPos();
        
        endX = (int) pacman.getXPos();
        endY = (int) pacman.getYPos();
        switch (pathFinderId % 2) {
            case 0:
                if (pacman.getDirection() == Direction.LEFT) {
                    if (board.getSpace(endX - 1, endY).spaceType != 1) {
                        endX-=1;
                    }
                } else if (pacman.getDirection() == Direction.RIGHT) {
                    if (board.getSpace(endX + 1, endY).spaceType != 1) {
                        endX+=1;
                    }
                } else if (pacman.getDirection() == Direction.UP) {
                     if (board.getSpace(endX, endY + 1).spaceType != 1) {
                        endY+=1;
                    }
                } else if (pacman.getDirection() == Direction.DOWN) {
                     if (board.getSpace(endX, endY - 1).spaceType != 1) {
                        endY-=1;
                    }
                }
                break;
            case 1:
                if (pacman.getDirection() == Direction.LEFT) {
                    if (board.getSpace(endX + 1, endY).spaceType != 1) {
                        endX+=1;
                    }
                } else if (pacman.getDirection() == Direction.RIGHT) {
                    if (board.getSpace(endX - 1, endY).spaceType != 1) {
                        endX-=1;
                    }
                } else if (pacman.getDirection() == Direction.UP) {
                     if (board.getSpace(endX, endY - 1).spaceType != 1) {
                        endY-=1;
                    }
                } else if (pacman.getDirection() == Direction.DOWN) {
                     if (board.getSpace(endX, endY +1).spaceType != 1) {
                        endY+=1;
                    }
                }
                break;
            default:
                // Did Math Break????
                            
        }
        
        currentCell = null;
        selectedCell = null;
        foundPath = false;
        hasPath = true;
        availableCells.clear();
        closedCells.clear();
        path.clear();
        
        availableCells.add(new Cell(startX, startY));
        do {
            pathFind();
        } while (!foundPath && hasPath);
        
        if(foundPath && !path.isEmpty()) {
//            System.out.println("Found a Path");
            int desiredY = path.get(path.size() - 1).y;
            int desiredX = path.get(path.size() - 1).x;
            
            if (startY > desiredY) {
                return Direction.UP;
            } else if (startY < desiredY) {
                return Direction.DOWN;
            }

            if(startX > desiredX) {
                return Direction.LEFT;
            } else if(startX < desiredX) {
                return Direction.RIGHT;
            }
        }
        
        return Direction.NONE;
    } 
    
    private void pathFind() {
 
        // If there is a current cell selected for inspection that means we
        // already inspected it and it should be removed
        if (currentCell != null) {
            availableCells.remove(currentCell);
            closedCells.add(currentCell);
        }
        
        // If there are no avaiable cells then there is no path
        if(availableCells.isEmpty()) {
            //System.err.println("No Path detected");
            hasPath = false;
            return;
        }
        
        currentCell = getLowestAvailableCell();
        if(currentCell.x == endX && currentCell.y == endY) {
            //System.out.println("At End Point");
            boolean hasParent = true;
            selectedCell = currentCell;
            do {
                hasParent = selectedCell.parent != null;
                if(hasParent) {
                    path.add(selectedCell);
                    selectedCell = selectedCell.parent;
                }
            } while(hasParent);
            foundPath = true;
            
            return;
        }
        
        
        if(currentCell.y > 0) {
            Cell upCell = new Cell (currentCell, currentCell.x, currentCell.y - 1);
            if (!closedCells.contains((Cell) upCell) && upCell.spaceType != 1) {
                if (upCell.distanceToStart > currentCell.distanceToStart + TRAVEL_COST) {
                    upCell.distanceToStart = currentCell.distanceToStart + TRAVEL_COST;
                    upCell.parent = currentCell;
                }
                if(!availableCells.contains((Cell) upCell)) {
                    availableCells.add(upCell);
                }
            } else {
                // System.out.println("The Up Cell was already closed.");
            }
           
        }
        
        if(currentCell.y < gridHeight - 1) {
            Cell downCell = new Cell (currentCell, currentCell.x, currentCell.y + 1);
            if(!closedCells.contains((Cell) downCell)) {
                if (downCell.distanceToStart > currentCell.distanceToStart + TRAVEL_COST) {
                    downCell.distanceToStart = currentCell.distanceToStart + TRAVEL_COST;
                    downCell.parent = currentCell;
                }
                if (!availableCells.contains((Cell) downCell) && downCell.spaceType != 1) {
                    availableCells.add(downCell);
                }
                
            } else {
                // System.out.println("The Down cell was closed already.");
            }
        }
        
        if(currentCell.x > 0) {
            Cell leftCell = new Cell(currentCell, currentCell.x - 1, currentCell.y);
            if(!closedCells.contains((Cell) leftCell)) {
                if (leftCell.distanceToStart > currentCell.distanceToStart + TRAVEL_COST) {
                    leftCell.distanceToStart = currentCell.distanceToStart + TRAVEL_COST;
                    leftCell.parent = currentCell;
                }
                if (!availableCells.contains((Cell) leftCell) && leftCell.spaceType != 1) {
                availableCells.add(leftCell);
                }
                
            } else {
                // System.out.println("The Left cell was closed already.");
            }
        }
        
        if(currentCell.x < gridWidth - 1) {
            Cell rightCell = new Cell(currentCell, currentCell.x + 1, currentCell.y);
            if(!closedCells.contains((Cell) rightCell) && rightCell.spaceType != 1) {
                if (rightCell.distanceToStart > currentCell.distanceToStart + TRAVEL_COST) {
                    rightCell.distanceToStart = currentCell.distanceToStart + TRAVEL_COST;
                    rightCell.parent = currentCell;
                }
                if (!availableCells.contains((Cell) rightCell)) {
                availableCells.add(rightCell);
                }
                
            } else {
                // System.out.println("The right cell was closed already.");
            }
        } 
    }
    
    private Cell getLowestAvailableCell() {
        Collections.sort(availableCells);
        return availableCells.get(0);
    }
    
    private class Cell implements Comparable {
        Cell parent;
        
        int distanceToStart = Integer.MAX_VALUE;
        int x;
        int y;
        int spaceType = 0;
        
        public Cell(int x, int y) {
            this.parent = null;
            this.distanceToStart = 0;
            this.x = x;
            this.y = y;
            spaceType = board.getSpace(x, y).spaceType;
        }
        
        public Cell(Cell parent, int x, int y) {
            this.parent = parent;
            this.distanceToStart = parent.distanceToStart + TRAVEL_COST;
            this.x = x;
            this.y = y;
            this.spaceType = board.getSpace(x, y).spaceType;
        }

        @Override
        public boolean equals(Object object) {
            return this.hashCode() == ((Cell)object).hashCode();
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 61 * hash + this.x;
            hash = 61 * hash + this.y;
            return hash;
        }

        
        int getFinalCost() {
            return distanceToStart + (int) (Math.abs(endX - x) * TRAVEL_COST * 50) + (int) (Math.abs(endY - y) * TRAVEL_COST * 50);
        }

        @Override
        public int compareTo(Object t) {
            return getFinalCost() - ((Cell)t).getFinalCost();
        }
        
    }
}

