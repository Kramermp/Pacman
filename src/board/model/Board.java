package board.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private Space[][] spaceArray;
    private Space[][] backUpArray;
    
//    private static final int[][] BOARD_ARRAY = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//                                               {1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
//                                               {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
//                                               {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
//                                               {1, 0, 1, 0, 0, 1, 0, 0, 1, 2, 1, 0, 0, 1, 0, 0, 1, 0, 1},
//                                               {1, 0, 0, 0, 1, 1, 1, 0, 1, 4, 1, 0, 1, 1, 1, 0, 0, 0, 1},
//                                               {1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1},
//                                               {1, 0, 1, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 1, 1, 0, 1},
//                                               {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
//                                               {1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
//                                               {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//    };
    
    public Board(Space[][] sourceSpaceArray) {
        this.spaceArray = sourceSpaceArray;
        calculatePellets();
    }
    
    public void pelletEaten() {
        pelletsEaten++;
    }
    
    public int getWidth() {
        return spaceArray[0].length;
    }
    
    public int getHeight() {
        return spaceArray.length;
    }

    public Space[][] getSpaceArray() {
        return this.spaceArray;
    }
    
    public boolean isCleared() {
        System.out.println("PelletsEaten: " + pelletsEaten);
        System.out.println("PelletCount: " + pelletCount);
        System.out.println(pelletsEaten);
        return pelletsEaten >= pelletCount;
    }
    
    public void reset(){
        pelletsEaten = 0;
        this.spaceArray = new BoardFactory().getNewSpaceArray();
        calculatePellets();
        System.out.println("Pellets Eaten: " + pelletsEaten);
    }

    public void calculatePellets() {
        pelletCount = 0;
        for(int i = 0; i < this.spaceArray.length; i++) {
            for(int j = 0; j < this.spaceArray[0].length; j++) {
                if(this.spaceArray[i][j].spaceType == PELLET ||
                        this.spaceArray[i][j].spaceType == POWER_PELLET) {
                    pelletCount++;
                }
            }
        }
        System.out.println("This Board has " + pelletCount + " pellets.");
    }
    
    public Space getSpace(int x, int y) {
        int realY = 0;
        int realX = 0;
        
        if(y < 0 ) {
            realY = spaceArray.length - 1;
        } else if (y > spaceArray.length - 1) {
            realY = 0;
        } else {
            realY = y;
        }
        
        if(x < 0 ) {
            realX = spaceArray[0].length - 1;
        } else if (x > spaceArray[0].length - 1) {
            realX = 0;
        } else {
            realX = x;
        }
        
        return spaceArray[realY][realX];
    }
    
    public Point getPlayerSpawn() {
        ArrayList<Point> spawnPoints = new ArrayList<>();
        for(int i = 0; i < spaceArray.length; i++) {
            for(int j = 0; j < spaceArray[i].length; j++) {
                //System.out.println("Looped");
                if(spaceArray[i][j].spaceType == Space.PLAYER_SPAWN) {
                    spawnPoints.add(new Point(j, i));
                }
            }
        }
        
        if(spawnPoints.isEmpty()) {
            return new Point(1, 1);
        }
        
        return spawnPoints.get(new Random().nextInt(spawnPoints.size()));
    }
    
    public Point getEnemySpawn() {
        ArrayList<Point> spawnPoints = new ArrayList<>();
        for(int i = 0; i < spaceArray.length; i++) {
            for(int j = 0; j < spaceArray[i].length; j++) {
                if(spaceArray[i][j].spaceType == Space.ENEMY_SPAWN) {
                    spawnPoints.add(new Point(j, i));
                }
            }
        }
        
        if(spawnPoints.isEmpty()) {
            return new Point(1, 1);
        }
        return spawnPoints.get(new Random().nextInt(spawnPoints.size()));
    }
    
}
