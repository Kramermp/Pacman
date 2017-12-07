package board.model;

/**
 *
 * @author Michael Kramer
 * @version .1
 */
public class Board {
    private static final int PELLET = 0;
    private static final int POWER_PELLET = 3;
    private int pelletCount;
    private int pelletsEaten;
    private int[][] spaceArray;
    
//    private static final int[][] BOARD_ARRAY = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
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
    private static final int[][] BOARD_ARRAY =  {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                               {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                                               {1, 4, 1, 1, 1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1, 1, 1, 4, 1},
                                               {1, 4, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1},
                                               {1, 4, 1, 4, 4, 1, 4, 4, 1, 2, 1, 4, 4, 1, 4, 4, 1, 4, 1},
                                               {1, 4, 4, 4, 1, 1, 1, 4, 1, 4, 1, 4, 1, 1, 1, 4, 4, 4, 1},
                                               {1, 4, 1, 4, 4, 1, 4, 4, 1, 1, 1, 4, 4, 1, 4, 4, 1, 4, 1},
                                               {1, 4, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 4, 1},
                                               {1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1},
                                               {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 1},
                                               {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    
    
    public Board() {
        reset();
    }
    
    public void pelletEaten() {
        pelletsEaten++;
        System.out.println(pelletsEaten);
    }
    
    public static int getWidth() {
        return BOARD_ARRAY[0].length;
    }
    
    public static int getHeight() {
        return BOARD_ARRAY.length;
    }

    public int[][] getSpaceArray() {
        return this.spaceArray;
    }
    
    public boolean boardIsCleared() {
//        System.out.println("PelletsEaten: " + pelletsEaten);
//        System.out.println("PelletCount: " + pelletCount);
//        System.out.println(pelletsEaten);
        return this.pelletsEaten == this.pelletCount;
    }
    
    public void reset(){
        System.out.println("Reseting Board");
        this.spaceArray  = new int[][] {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                               {1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
                                               {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                                               {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                                               {1, 0, 1, 0, 0, 1, 0, 0, 1, 2, 1, 0, 0, 1, 0, 0, 1, 0, 1},
                                               {1, 0, 0, 0, 1, 1, 1, 0, 1, 4, 1, 0, 1, 1, 1, 0, 0, 0, 1},
                                               {1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1},
                                               {1, 0, 1, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                                               {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                                               {1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
                                               {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        this.pelletsEaten = 0;
        calculatePellets();
    }

    public void calculatePellets() {
        this.pelletCount = 0;
        for(int i = 0; i < this.spaceArray.length; i++) {
            for(int j = 0; j < this.spaceArray[0].length; j++) {
                if(this.spaceArray[i][j] == PELLET ||
                        this.spaceArray[i][j] == POWER_PELLET) {
                    
                    this.pelletCount++;
                }
            }
        }
        System.out.println("This Board has " + pelletCount + " pellets.");
    }
    
}
