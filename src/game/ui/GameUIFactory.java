package game.ui;

import entity.ui.PacManDisplay;
import game.controller.GameCntl;
import game.model.Game;

/**
 * This class is used to generate GameUIs in a simple and clean manner.
 * 
 * @author Michael Kramer
 * @version .1
 */
public class GameUIFactory {
    private Game sourceGame;
    
    public GameUIFactory(Game sourceGame) {
        this.sourceGame = sourceGame;
    }
    
    /**
     * Gets a GameUI using the provided GameCntl as the parentCntl
     * 
     * @param gameCntl the desired ParentCntl
     * @return the GameUI object
     */
    public GameUI getGameUI(GameCntl gameCntl) {
        PacManDisplay pacmanDisplay = new PacManDisplay(sourceGame.getPacMan());
        
        return new GameUI(gameCntl, pacmanDisplay);
    }
}
