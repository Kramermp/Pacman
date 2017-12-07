package game.ui;

import entity.ui.PacManDisplay;
import game.controller.GameCntl;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The class displays the Game
 * 
 * @author Michael Kramer
 * @version .1
 */
public class GameUI extends JPanel {
    private GameCntl parentCntl;
    // This timer will be what drives the FPS
    private Timer timer  = new Timer(250, (ActionEvent ae) -> {
        //System.out.println("Display Timer Tick");
        this.drawBoard();
    });
    
    private PacManDisplay pacmanDisplay;
    
    /**
     * This is the default constructor for GameUI
     * 
     * @param parentCntl the GameCntrl to manage this UI
     * @param pacmanDisplay the PacManDisplay object
     */
    public GameUI(GameCntl parentCntl, PacManDisplay pacmanDisplay) {
        this.parentCntl = parentCntl;
        this.pacmanDisplay = pacmanDisplay;
        this.timer.start();
        this.configureDisplays();
        this.addKeyListener(new ControlListener());
    }
    
    private void drawBoard() {
        if (this.getGraphics() == null) {
            // Do Nothing
            //System.out.println("Graphics null");
        } else {
            this.paintComponent(this.getGraphics());
        } 
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.out.println("Painting Component");
        pacmanDisplay.drawEntity(g);
        this.updateUI();
        //UserInterface.getInstance().repaint();
    }
    
    private void configureDisplays() {
        this.pacmanDisplay.setImageObserver(this);
    }
    
    private class ControlListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {
            
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            parentCntl.inputReceived(ke);
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            
        }
    
    }
}
