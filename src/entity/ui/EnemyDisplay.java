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
    private BufferedImage enemyImageDown;
    private Enemy source;
    
    public EnemyDisplay(Enemy source) {
        this.source = source;
        loadImages();
    }

    @Override
    public void drawEntity(Graphics g, GameUI parentDisplay) {
        System.out.println("Drawing EnemeyEntity");
        int spaceHeight = parentDisplay.getSize().height / Board.getHeight();
        int spaceWidth = parentDisplay.getSize().width / Board.getWidth();
        
        int pacmanHeight = spaceHeight - 10;
        int pacmanWidth = spaceWidth - 10;
        
        
        int realXPos = (int) (source.getXPos() * spaceWidth + 5);
        int realYPos = (int) (source.getYPos() * spaceHeight) + 5; 
        
        g.drawImage(getCurrentImage(), realXPos, realYPos, pacmanWidth, pacmanHeight, parentDisplay);
        
        source.incrementFrame();
    }

    @Override
    public void setImageObserver(ImageObserver io) {
        
    }

    private void loadImages() {
        try {
            enemyImageDown = ImageIO.read(new File("enemy.png"));
        } catch (IOException ex) {
            Logger.getLogger(EnemyDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Image getCurrentImage() {
        //Should Do Stuff
        return enemyImageDown;
    }
}
