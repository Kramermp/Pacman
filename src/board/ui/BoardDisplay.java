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
    private Board source;
    private static BufferedImage backgroundImage;
    
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
                if(spaceArray[i][j] == 0) {
                    g.setColor(Color.BLACK);
                } else if(spaceArray[i][j] == 1) {
                    //Random rng = new Random();
                    //g.setColor(new Color(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
                    g.setColor(Color.BLUE);
                }
                g.fillRect(j * spaceWidth, i * spaceHeight, spaceWidth, spaceHeight);
            }
        }
    }
    
    private void loadImage() {
        try {
            backgroundImage = ImageIO.read(new File("background.png"));
        } catch (IOException ex) {
            Logger.getLogger(BoardDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
