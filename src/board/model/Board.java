package board.model;

/**
 *
 * @author Michael Kramer
 * @version .1
 */
public class Board {
    private static final int PELLET = 0;
    private static final int POWER_PELLET = 3;
    private static int pelletCount;
    private int pelletsEaten;
//    private static final int[][] boardArray = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//                                               {1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
//                                               {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
//                                               {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
//                                               {1, 0, 1, 0, 0, 1, 0, 0, 1, 2, 1, 0, 0, 1, 0, 0, 1, 0, 1},
//                                               {1, 0, 0, 0, 1, 1, 1, 0, 1, 4, 1, 0, 1, 1, 1, 0, 0, 0, 1},
//                                               {1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1},
//                                               {1, 0, 1, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 1, 1, 0, 1},
//                                               {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
//                                               {1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
//                                               {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//    };
    private static final int[][] boardArray =  {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                               {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                                               {1, 4, 1, 1, 1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1, 1, 1, 4, 1},
                                               {1, 4, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1},
                                               {1, 4, 1, 4, 4, 1, 4, 4, 1, 2, 1, 4, 4, 1, 4, 4, 1, 4, 1},
                                               {1, 4, 4, 4, 1, 1, 1, 4, 1, 4, 1, 4, 1, 1, 1, 4, 4, 4, 1},
                                               {1, 4, 1, 4, 4, 1, 4, 4, 1, 1, 1, 4, 4, 1, 4, 4, 1, 4, 1},
                                               {1, 4, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 4, 1},
                                               {1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 0, 1},
                                               {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 1},
                                               {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };
    private int[][] spaceArray;
    
    public Board() {
        spaceArray = boardArray.clone();
        for(int i = 0; i < boardArray.length; i++) {
            for(int j = 0; j < boardArray[0].length; j++) {
                if(boardArray[i][j] == PELLET |
                        boardArray[i][j] == POWER_PELLET) {
                    
                    pelletCount++;
                }
            }
        }
    }
    
    public void pelletEaten() {
        pelletsEaten++;
        System.out.println(pelletsEaten);
        System.out.println(pelletCount);
    }
    
    public static int getWidth() {
        return boardArray[0].length;
    }
    
    public static int getHeight() {
        return boardArray.length;
    }

    public int[][] getSpaceArray() {
        return spaceArray;
    }
    
    public boolean boardIsCleared() {
        return pelletsEaten == pelletCount;
    }
    
    public void reset(){
        spaceArray = boardArray.clone();
    }
    
}
