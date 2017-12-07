/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.ui;

import board.model.Board;
import entity.model.Enemy;
import game.ui.GameUI;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Michael Kramer
 */
public class EnemyDisplay implements Drawable {   
    private int enemyNumber = 0;
    private BufferedImage enemy1ImageDown;
    private BufferedImage enemy2ImageDown;
    private BufferedImage enemy3ImageDown;
    private Enemy source;
    
    public EnemyDisplay(Enemy source) {
        this.source = source;
        this.enemyNumber = this.source.getEnemyNumber();
        loadImages();
    }

    @Override
    public void drawEntity(Graphics g, GameUI parentDisplay) {
        //System.out.println("Drawing EnemeyEntity");
        int spaceHeight = parentDisplay.getSize().height / Board.getHeight();
        int spaceWidth = parentDisplay.getSize().width / Board.getWidth();
        
        int enemyHeight = spaceHeight - 10;
        int enemyWidth = spaceWidth - 10;
        
        int realXPos = (int) (source.getXPos() * spaceWidth + 5);
        int realYPos = (int) (source.getYPos() * spaceHeight) + 5; 
        
        g.drawImage(getCurrentImage(), realXPos, realYPos, enemyWidth, enemyHeight, parentDisplay);
        
        source.incrementFrame();
    }

    @Override
    public void setImageObserver(ImageObserver io) {
        
    }

    private void loadImages() {
        try {
            enemy1ImageDown = ImageIO.read(new File("enemy1.png"));
            enemy2ImageDown = ImageIO.read(new File("enemy2.png"));
            enemy3ImageDown = ImageIO.read(new File("enemy3.png"));
        } catch (IOException ex) {
            Logger.getLogger(EnemyDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Image getCurrentImage() {
        switch(this.enemyNumber) {
            case 1:
                
                return enemy1ImageDown;
            case 2:
                
                return enemy2ImageDown;
            case 3:
                
                return enemy3ImageDown;
            default:
                return enemy1ImageDown;
        }
    }
}
