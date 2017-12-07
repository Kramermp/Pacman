package game.controller;

import entity.model.Direction;
import game.model.Game;
import game.ui.GameUI;
import game.ui.GameUIFactory;
import java.awt.event.KeyEvent;
import userinterface.Controller;
import userinterface.UserInterface;

/**
 * The class generates and manages the GameUI
 * 
 * @author Michael Kramer
 * @version .1
 */
public class GameCntl extends Controller {
    private int totalScore;
    private int level = 1;
    private Game game;
    
    
    /**
     * This constructor creates the GameUI and places it into the UserInterface
     */
    public GameCntl() {
        initializeGame();
        GameUI childUI = new GameUIFactory(game).getGameUI(this);
        UserInterface.getInstance().setDisplay(childUI);

        UserInterface.getInstance().setCurrentController(this);
        childUI.repaint();
        childUI.requestFocus(); 
    }
    
    private void initializeGame() {
        game = new Game(this, level);
    }
    
    public void boardCleared(int levelScore) {
        System.out.println("Board Cleared");
        totalScore+=levelScore;
        game = new Game(this, level);
        GameUI childUI = new GameUIFactory(game).getGameUI(this);
        UserInterface.getInstance().setDisplay(childUI);
        childUI.requestFocus();
    }
    
    @Override
    public void inputReceived(KeyEvent ke) {
        System.out.println("Input Recieved");
        switch(ke.getExtendedKeyCode()) {
            case KeyEvent.VK_UP:
                game.getPacMan().setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                game.getPacMan().setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                game.getPacMan().setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                game.getPacMan().setDirection(Direction.RIGHT);
                break;
        }
    }
}
