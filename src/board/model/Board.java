package board.model;

/**
 *
 * @author Michael Kramer
 * @version .1
 */
public class Board {
    private static final int[][] boardArray = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                               {1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
                                               {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                                               {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                                               {1, 0, 1, 0, 0, 1, 0, 0, 1, 2, 1, 0, 0, 1, 0, 0, 1, 0, 1},
                                               {1, 0, 0, 0, 1, 1, 1, 0, 1, 4, 1, 0, 1, 1, 1, 0, 0, 0, 1},
                                               {1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1},
                                               {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                                               {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                                               {1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
                                               {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };
    
    public static int getWidth() {
        return boardArray[0].length;
    }
    
    public static int getHeight() {
        return boardArray.length;
    }

    public int[][] getSpaceArray() {
        return boardArray;
    }
    
}
