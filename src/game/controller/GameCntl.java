package game.controller;

import game.ui.GameUI;
import userinterface.UserInterface;

/**
 * The class generates and manages the GameUI
 * 
 * @author Michael Kramer
 * @version .1
 */
public class GameCntl {
    
    /**
     * This constructor creates the GameUI and places it into the UserInterface
     */
    public GameCntl() {
        GameUI childUI = new GameUI(this);
        UserInterface.getInstance().setDisplay(childUI);
    }
}
