package mainmenu.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
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
        addComponents();
    }
    
    private void addComponents() {
        JButton playBtn = new JButton("Play");
        playBtn.addActionListener((ActionEvent ae) -> { 
            System.out.println("playBtn click event registered.");
            parentCntl.startGame();
        });
        add(playBtn);
    }

}
