/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmenu.controller;

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
}
