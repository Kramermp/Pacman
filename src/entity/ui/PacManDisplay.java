package entity.ui;

import board.model.Board;
import entity.model.PacMan;
import game.ui.GameUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class serves as the model to view adapter for the pacman class.
 * @author Michael Kramer
 * @version .1
 */
public class PacManDisplay implements Drawable {
    private GameUI parentDisplay;
    private PacMan source;
    private ImageObserver observer;
    private BufferedImage openMouthImage;
    private BufferedImage closedMouthImage;
    
    public PacManDisplay (PacMan pacman) {
        this.source = pacman;
        loadImage();
    }

    @Override
    public void drawEntity(Graphics g, GameUI parentDisplay) {
        //System.out.println("Drawing PacManEntity");
        int spaceHeight = parentDisplay.getSize().height / Board.getHeight();
        int spaceWidth = parentDisplay.getSize().width / Board.getWidth();
        
        int pacmanHeight = spaceHeight - 10;
        int pacmanWidth = spaceWidth - 10;
        
        
        int realXPos = (int) (source.getXPos() * spaceWidth + 5);
        int realYPos = (int) (source.getYPos() * spaceHeight) + 5; 
        
        g.drawImage(getCurrentImage(), realXPos, realYPos, pacmanWidth, pacmanHeight, observer);
    }
    
    public BufferedImage getCurrentImage() {
        if(System.currentTimeMillis() % 2 == 0) {
            return openMouthImage;
        } else {
            return closedMouthImage;
        }
    }
    
    @Override
    public void setImageObserver(ImageObserver io) {
        this.observer = io;
    }
    
    private void loadImage() {
        try {
            openMouthImage = ImageIO.read(new File("pacman-openmouth.png"));
            closedMouthImage = ImageIO.read(new File("pacman-closedmouth.png"));
        } catch (IOException ex) {
            System.err.println("IOException reading PacMan Image");
        }
    }
}
