/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board.model;

/**
 *
 * @author Michael Kramer
 */
public class Space {
    public static final int NORMAL = 0;
    public static final int WALL = 1;
    public static final int ENEMY_SPAWN = 8;
    public static final int PLAYER_SPAWN = 9;
    
    public int spaceType = NORMAL;
    public int xPos = 0;
    public int yPos = 0;
    
    public boolean hasPacMan = false;
    
    public Space(int spaceType, int xPos, int yPos) {
        this.spaceType = spaceType;
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public boolean isTraversable() {
        return spaceType != WALL;
    }

}
