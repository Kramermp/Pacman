package mainmenu.controller;

import entity.model.Enemy;
import entity.model.Entity;
import entity.model.PacMan;
import game.controller.GameCntl;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import mainmenu.ui.MainMenuUI;
import userinterface.UserInterface;

/**
 * This class creates and manages the MainMenuUI.
 * 
 * @author Michael Kramer <kramermp5@gmail.com>
 * @version .1
 */
public class MainMenuCntl {
    private UserInterface userInterface;
    private PacMan pacman;
    private Enemy[] enemies;
    
    private Timer enityTimer = new Timer(33, (ActionEvent ae) -> { 
        pacman.move();
        for(int i = 0; i < enemies.length; i++) {
            enemies[i].move();
        }
    });
    
    /**
     * Creates a MainMenuCntl, UserInterface, and puts a MainMenuUI inside
     */
    public MainMenuCntl() {
        pacman = new PacMan();
        
        Enemy.resetEnemyNumber();
        enemies = new Enemy[] {new Enemy(), new Enemy(), new Enemy(), new Enemy()};
        MainMenuUI childUI = new MainMenuUI(this, pacman, enemies);
        userInterface = UserInterface.getInstance();
        userInterface.setDisplay(childUI);
        userInterface.setVisible(true);
        childUI.start();
        enityTimer.start();
    }
    
    /**
     * This method is called to initialize a game.
     */
    public void startGame() {
        new GameCntl();
    }
}
