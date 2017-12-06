package mainmenu.controller;

import game.controller.GameCntl;
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
    
    /**
     * Creates a MainMenuCntl, UserInterface, and puts a MainMenuUI inside
     */
    public MainMenuCntl() {
        MainMenuUI childUI = new MainMenuUI(this);
        userInterface = UserInterface.getInstance();
        userInterface.setDisplay(childUI);
        userInterface.setVisible(true);
    }
    
    /**
     * This method is called to initialize a game.
     */
    public void startGame() {
        new GameCntl();
    }
}
