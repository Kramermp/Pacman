/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.event.KeyEvent;

/**
 *
 * @author Michael Kramer
 * @version .1
 */
public abstract class Controller {
    public abstract void inputReceived(KeyEvent ke);
}
