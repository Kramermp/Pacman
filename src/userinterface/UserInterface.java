package userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;

/**
 * This Class acts the window that the Application will use.
 * <p>
 * It is a Singleton class that follows the Lazy-Holder Idiom 
 * initialization-on-demand structure.
 * <p>
 * @author Michael Kramer <kramermp5@gmail.com>
 * @version .1
 */
public class UserInterface extends JFrame {
    
    /**
     * This is the default constructor for the UserInterface class
     */
    private UserInterface() {
        configureWindow();
    }
    
    /**
     * This method is how you should access the UserInterface object.
     * 
     * @return The Singular instance of the UserInterface.
     */
    public static UserInterface getInstance() {
        return LazyHolder.INSTANCE;
    }
    
    /**
     * This method displays the desired component
     * 
     * @param componentToDisplay The Component to be displayed
     */
    public void setDisplay(Component componentToDisplay) {
        this.clear();
        // Makes the component take the full JFrame
        this.add(componentToDisplay, BorderLayout.CENTER);
        this.repaint();
    }
    
    /**
     * The LazyHolder class used to hold the UserInterface instance.
     */
    private static class LazyHolder {
        private static final UserInterface INSTANCE = new UserInterface();
    }
    
    /**
     * This method is used to set some simple default settings for the window.
     */
    private void configureWindow() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    /**
     * Simple method used to empty the window
     */
    private void clear() {
        this.getContentPane().removeAll();
    }
}
