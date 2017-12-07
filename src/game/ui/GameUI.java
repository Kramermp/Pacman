package game.ui;

import board.ui.BoardDisplay;
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
    private Timer timer  = new Timer(100, (ActionEvent ae) -> {
        //System.out.println("Display Timer Tick");
        this.drawBoard();
    });
    
    private PacManDisplay pacmanDisplay;
    private BoardDisplay boardDisplay;
    
    /**
     * This is the default constructor for GameUI
     * 
     * @param parentCntl the GameCntrl to manage this UI
     * @param pacmanDisplay the PacManDisplay object
     */
    public GameUI(GameCntl parentCntl, PacManDisplay pacmanDisplay, 
            BoardDisplay boardDisplay) {
        this.parentCntl = parentCntl;
        this.pacmanDisplay = pacmanDisplay;
        this.boardDisplay = boardDisplay;
        this.setDoubleBuffered(true);
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
        //System.out.println("Painting Component");
        boardDisplay.drawBoard(g, getSize());
        pacmanDisplay.drawEntity(g, this);
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
