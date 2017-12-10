/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astartest;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Michael Kramer
 */
public class AStarController {
    private Random rng = new Random();
    int TRAVEL_COST = 10;
    
    private int gridWidth = 100;
    private int gridHeight = 100;
    private Point startPoint = new Point(0, 0);
    private Point endPoint = new Point(gridWidth - 1, gridHeight - 1);
    
    private Point currentPoint = startPoint;
    
    private ArrayList<Cell> availableCells = new ArrayList<Cell>();
    private ArrayList<Cell> closedCells = new ArrayList<Cell>();
    
    private Cell currentCell;
    
    private Timer timer = new Timer(10, (ActionEvent ae) ->  { 
        pathFind();
    });
    private boolean foundPath = false;
    
    private int[][] sourceArray = new int[gridHeight][gridWidth];
    private JPanel[][] panelArray = new JPanel[gridHeight][gridWidth];
    
    public AStarController() {
        AStarUI ui = new AStarUI(this);
        ui.setVisible(true);
        ui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        generatePanels();
        long startTime = System.currentTimeMillis();
        while(!foundPath) {
            pathFind();
        }
        long endTime = System.currentTimeMillis();
        
        System.out.println("Path Found After " + (endTime - startTime) + "ms");
    }
    
    public AStarController(int delay) {
        AStarUI ui = new AStarUI(this);
        ui.setVisible(true);
        ui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        generatePanels();
        timer.setDelay(delay);
        timer.start();
    }

    int getGridWidth() {
        return this.gridWidth;
    }

    int getGridHeight() {
        return this.gridHeight;
    }

    Point getStartPoint() {
        return this.startPoint;
    }

    Point getEndPoint() {
        return this.endPoint;
    }
    
    JPanel[][] getPanelArray() {
        return this.panelArray;
    }
    
    private void generatePanels() {
        for(int i = 0; i < sourceArray.length; i++) {
            for(int j = 0; j < sourceArray[i].length; j++) {
                if(i == endPoint.y && endPoint.x == j) {
                    sourceArray[i][j] = 0;
                } else {
                    if(rng.nextInt(10) >= 7) {
                   sourceArray[i][j] = 1;
                } else {
                    sourceArray[i][j] = 0;
                    }
                } 
            }
        }
        paintPanels();
    }
    private void paintPanels() {
        panelArray[startPoint.y][startPoint.x].setBackground(Color.GREEN);
        panelArray[endPoint.y][endPoint.x].setBackground(Color.MAGENTA);
        for(int i = 0; i < sourceArray.length; i++) {
            for(int j = 0; j < sourceArray[i].length; j++) {
                switch(sourceArray[i][j]) {
                    case 0:
                        
                        break;
                    case 1:
                        panelArray[i][j].setBackground(Color.BLACK);
                        break;
                }
            }
        }
        availableCells.add(new Cell(startPoint.x, startPoint.y));
    }
    
    private void pathFind() {
        if (currentCell != null) {
            panelArray[currentCell.y][currentCell.x].removeAll();
//            panelArray[currentCell.y][currentCell.x].setLayout(new FlowLayout());
//            panelArray[currentCell.y][currentCell.x].add( new JLabel(String.valueOf(currentCell.getFinalCost())));
            panelArray[currentCell.y][currentCell.x].setBackground(Color.RED);
            panelArray[currentCell.y][currentCell.x].updateUI();
            availableCells.remove(currentCell);
            closedCells.add(currentCell);
        } 
        if(availableCells.isEmpty()) {
            System.err.println("No Available Cells");
            foundPath = true;
            return;
        }
        
        currentCell = getLowestAvailableCell();
        panelArray[currentCell.y][currentCell.x].setBackground(Color.BLUE);
        if(currentCell.x == endPoint.x && currentCell.y == endPoint.y) {
            System.out.println("At End Point");
            timer.stop();
            boolean hasParent = true;
            Cell parentCell = currentCell;
            do {
                hasParent = parentCell.parent != null;
                panelArray[parentCell.y][parentCell.x].setBackground(Color.MAGENTA);
                parentCell = parentCell.parent;
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
                    panelArray[currentCell.y - 1][currentCell.x].setBackground(Color.yellow);
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
                    panelArray[currentCell.y + 1][currentCell.x].setBackground(Color.yellow);
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
                    panelArray[currentCell.y][currentCell.x - 1].setBackground(Color.yellow);
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
                    panelArray[currentCell.y][currentCell.x + 1].setBackground(Color.yellow);
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
            this.distanceToStart = 0;
            this.x = x;
            this.y = y;
            spaceType = sourceArray[y][x];
        }
        
        public Cell(Cell parent, int x, int y) {
            this.parent = parent;
            this.distanceToStart = parent.distanceToStart + TRAVEL_COST;
            this.x = x;
            this.y = y;
            this.spaceType = sourceArray[y][x];
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
            return distanceToStart + (int) (Math.abs(endPoint.x - x) * TRAVEL_COST * 50) + (int) (Math.abs(endPoint.y - y) * TRAVEL_COST * 50);
        }

        @Override
        public int compareTo(Object t) {
            return getFinalCost() - ((Cell)t).getFinalCost();
        }
        
    }
}
