package entity.ui;

import entity.model.PacMan;
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
    private PacMan source;
    private ImageObserver observer;
    private BufferedImage image;
    
    public PacManDisplay (PacMan pacman) {
        this.source = pacman;
        loadImage();
    }

    @Override
    public void drawEntity(Graphics g) {
        //System.out.println("Drawing PacManEntity");
        g.drawImage(image, source.getXPos(), source.getYPos(), observer);
        
    }

    @Override
    public void setImageObserver(ImageObserver io) {
        this.observer = io;
    }
    
    private void loadImage() {
        try {
            image = ImageIO.read(new File("pacman.png"));
        } catch (IOException ex) {
            System.err.println("IOException reading PacMan Image");
        }
    }
}
