package game.model;

import entity.model.PacMan;
import javax.swing.Timer;

/**
 * This class contains all of the components for the game.
 * 
 * @author Michael Kramer
 * @version .1
 */
public class Game {
    private PacMan pacman;
    private Timer timer = new Timer(125, (ActionEvent) -> { 
        updateEntities();
    });
    
    public Game () {
        this.pacman = new PacMan(0, 0);
        this.pacman.setSpeed(10);
        timer.start();
    }
    
    
    
    
    
    
    
    
    public PacMan getPacMan() {
        return this.pacman;
    }
    
    
    private void updateEntities() {
        pacman.move();
    }
}
