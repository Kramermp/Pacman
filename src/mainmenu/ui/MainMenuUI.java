package mainmenu.ui;

import javax.swing.JPanel;
import mainmenu.controller.MainMenuCntl;

/**
 * This class is a JPanel that contains the content of the Main Menu. It utilizes
 * the MainMenuCntl to interact with the User Interface.
 * 
 * @author Michael Kramer <kramermp5@gmail.com>
 * @version .1
 */
public class MainMenuUI extends JPanel {
    private MainMenuCntl parentCntl;
    
    /**
     * This constructor creates a MainMenuUI object
     * @param parentCntl The MainMenuCntl that this UI uses to interact with th
     */
    public MainMenuUI(MainMenuCntl parentCntl) {
        this.parentCntl = parentCntl;
    }
}
