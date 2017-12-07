/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board.ui;

import board.model.Board;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Michael Kramer
 * @version .1
 */
public class BoardDisplay {
    private final Board source;
    private static BufferedImage backgroundImage;
    private static BufferedImage powerupImage;
    private static BufferedImage pelletImage;
    
    public BoardDisplay(Board board) {
        this.source = board;
        loadImage();
    }
    
    public void drawBoard(Graphics g, Dimension size) {
        int[][] spaceArray = source.getSpaceArray();
        
        int spaceHeight = size.height / source.getHeight();
        int spaceWidth = size.width / source.getWidth();
        
        //g.drawImage(backgroundImage, 0, 0, size.width, size.height, null);
        for(int i = 0; i < source.getHeight(); i++) {
            for(int j = 0; j < source.getWidth(); j++) {
                switch (spaceArray[i][j]) {
                    case 3: // Has Power Pellet
                        g.drawImage(powerupImage, j * spaceWidth, i * spaceHeight, spaceWidth, spaceHeight, null);
                        break;
                    case 0: // Has regular Pellet
                        //Random rng = new Random();
                        //g.setColor(new Color(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
                        g.drawImage(pelletImage, j * spaceWidth, i * spaceHeight, spaceWidth, spaceHeight, null);
                        break;
                    case 1: // Has Wall
                        g.setColor(Color.BLUE);
                        g.fillRect(j * spaceWidth, i * spaceHeight, spaceWidth, spaceHeight);
                        break;
                    case 2: //
                        g.setColor(Color.RED);
                        g.fillRect(j * spaceWidth, i * spaceHeight, spaceWidth, spaceHeight);
                        break;

                    default:

                        break;
                }
            }
        }
    }
    
    private void loadImage() {
        try {
            backgroundImage = ImageIO.read(new File("background.png"));
            powerupImage = ImageIO.read(new File("powerup.png"));
            pelletImage = ImageIO.read(new File("pellet.png"));
        } catch (IOException ex) {
            Logger.getLogger(BoardDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
