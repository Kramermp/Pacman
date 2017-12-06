package game.ui;

import game.controller.GameCntl;
import javax.swing.JPanel;

/**
 * The class displays the Game
 * 
 * @author Michael Kramer
 * @version .1
 */
public class GameUI extends JPanel {
    private GameCntl parentCntl;
    
    /**
     * This is the default constructor for GameUI
     * 
     * @param parentCntl the GameCntrl to manage this UI
     */
    public GameUI(GameCntl parentCntl) {
        this.parentCntl = parentCntl;
    }
}
