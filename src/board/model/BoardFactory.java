/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board.model;

import java.util.Random;

/**
 *
 * @author Michael Kramer
 */
public class BoardFactory {
    private static final int BOARD_HEIGHT = 20;
    private static final int BOARD_WIDTH = 20;
    
    
    public BoardFactory() {
        
        
    }
    
    public Board getEmptyBoard() {
        
        return new Board(getEmptySpaceArray());
    }
    
    public Board getBoard() {
        return getRealBoard();
    }
    
    
    public Board getRealBoard() {
        
        return new Board(getNewSpaceArray());
    }
    
    
    
    
//    Random rng = new Random();
//        Space[][] sourceSpaceArray = new Space[BOARD_HEIGHT][BOARD_WIDTH];
//        for(int i = 0; i < 10; i++) {
//            for(int j = 0; j < 10; j++) {
//                int spaceType = 0;
//                if (rng.nextInt(10) > 7) {
//                    spaceType = 1;
//                }
//                sourceSpaceArray[i][j] = new Space(spaceType, j, i);
//                
//            }
//        }
//        return new Board(sourceSpaceArray);

    public Space[][] getEmptySpaceArray() {
        Space[][] sourceSpaceArray = new Space[BOARD_HEIGHT][BOARD_WIDTH];
        for(int i = 0; i < BOARD_HEIGHT; i++) {
            for(int j = 0; j < BOARD_WIDTH; j++) {
                int spaceType = 4;
                if((i == 0 || i == BOARD_HEIGHT- 1) && j != BOARD_WIDTH / 2) {
                    spaceType = Space.WALL;
                }
                if((j == 0 || j == BOARD_WIDTH - 1) && i != BOARD_HEIGHT / 2) {
                    spaceType = Space.WALL;
                }    
                
                if(i == j && i == BOARD_WIDTH / 2) {
                    spaceType = 0;
                }
                sourceSpaceArray[i][j] = new Space(spaceType, j, i);
                
            }
        }
        
        return sourceSpaceArray;
    }
    
    public Space[][] getNewSpaceArray() {
        int[][] spaceTypeArray = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                  {1, 3, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 3, 1},
                                  {1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
                                  {1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
                                  {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                  {1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1},
                                  {1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1},
                                  {1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1},
                                  {1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1},
                                  {1, 0, 1, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 1, 1, 0, 1},
                                  {0, 0, 0, 0, 0, 0, 0, 1, 8, 1, 0, 0, 0, 0, 0, 0, 0},
                                  {1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
                                  {1, 0, 1, 1, 1, 1, 0, 1, 9, 1, 0, 1, 1, 1, 1, 0, 1},
                                  {1, 0, 0, 0, 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0, 1},
                                  {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
                                  {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
                                  {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                  {1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
                                  {1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1},
                                  {1, 3, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 3, 1},
                                  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}                    
        };
        
        Space[][] sourceSpaceArray = new Space[spaceTypeArray.length][spaceTypeArray[0].length];
        
        
        for(int i = 0; i < spaceTypeArray.length; i++) {
            for(int j = 0; j < spaceTypeArray[i].length; j++) {    
                sourceSpaceArray[i][j] = new Space(spaceTypeArray[i][j], j, i);        
            }
        }
        return sourceSpaceArray;
    }
}
