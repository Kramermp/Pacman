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
    private BufferedImage enemy1ImageUp;
    private BufferedImage enemy1ImageDown;
    private BufferedImage enemy1ImageLeft;
    private BufferedImage enemy1ImageRight;
    private BufferedImage enemy2ImageUp;
    private BufferedImage enemy2ImageDown;
    private BufferedImage enemy2ImageLeft;
    private BufferedImage enemy2ImageRight;
    private BufferedImage enemy3ImageUp;
    private BufferedImage enemy3ImageDown;
    private BufferedImage enemy3ImageLeft;
    private BufferedImage enemy3ImageRight;
    
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
            enemy1ImageUp = ImageIO.read(new File("enemy1Up.png"));
            enemy1ImageDown = ImageIO.read(new File("enemy1Down.png"));
            enemy1ImageLeft = ImageIO.read(new File("enemy1Left.png"));
            enemy1ImageRight = ImageIO.read(new File("enemy1Right.png"));
            enemy2ImageUp = ImageIO.read(new File("enemy2Up.png"));
            enemy2ImageDown = ImageIO.read(new File("enemy2Down.png"));
            enemy2ImageLeft = ImageIO.read(new File("enemy2Left.png"));
            enemy2ImageRight = ImageIO.read(new File("enemy2Right.png"));
            enemy3ImageUp = ImageIO.read(new File("enemy3Up.png"));
            enemy3ImageDown = ImageIO.read(new File("enemy3Down.png"));
            enemy3ImageLeft = ImageIO.read(new File("enemy3Left.png"));
            enemy3ImageRight = ImageIO.read(new File("enemy3Right.png"));
        } catch (IOException ex) {
            Logger.getLogger(EnemyDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Image getCurrentImage() {
        switch(this.enemyNumber) {
            case 1:
                return getEnemy1Image();
            case 2:
                return getEnemy2Image();
            case 3:
                return getEnemy3Image();
            default:
                return getEnemy1Image();
        }
    }
    
    private Image getEnemy1Image() {
        switch(source.getDirection()) {
            case UP:
                return enemy1ImageUp;
            case DOWN:
                return enemy1ImageDown;
            case LEFT:
                return enemy1ImageLeft;
            case RIGHT:
                return enemy1ImageRight;
            default:
                return enemy1ImageDown;
        }
    }
    
    private Image getEnemy2Image() {
        switch(source.getDirection()) {
            case UP:
                return enemy2ImageUp;
            case DOWN:
                return enemy2ImageDown;
            case LEFT:
                return enemy2ImageLeft;
            case RIGHT:
                return enemy2ImageRight;
            default:
                return enemy2ImageDown;
        }
    }
    
    private Image getEnemy3Image() {
        switch(source.getDirection()) {
            case UP:
                return enemy3ImageUp;
            case DOWN:
                return enemy3ImageDown;
            case LEFT:
                return enemy3ImageLeft;
            case RIGHT:
                return enemy3ImageRight;
            default:
                return enemy3ImageDown;
        }
    }
}
