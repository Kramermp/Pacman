/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.model;

import game.model.Game;

/**
 *
 * @author Michael Kramer
 * @version .1
 */
public class Enemy extends Entity {
    private static int currentEnemyNumber = 1;
    private int enemyNumber = 1;
    
    public Enemy(Game parentGame, double xPos, double yPos, int level) {
        super(parentGame, xPos, yPos);
        super.setSpeed(.125 + .125 / level);
        this.enemyNumber = currentEnemyNumber;
        currentEnemyNumber++;
    }
    
    public int getEnemyNumber() {
        return this.enemyNumber;
    }
    
}
