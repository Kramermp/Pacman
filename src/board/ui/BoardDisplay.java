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

/**
 *
 * @author Michael Kramer
 * @version .1
 */
public class BoardDisplay {
    private Board source;
    
    public BoardDisplay(Board board) {
        this.source = board;
    }
    
    public void drawBoard(Graphics g, Dimension size) {
        int[][] spaceArray = source.getSpaceArray();
        
        int spaceHeight = size.height / source.getHeight();
        int spaceWidth = size.width / source.getWidth();
        for(int i = 0; i < source.getHeight(); i++) {
            for(int j = 0; j < source.getWidth(); j++) {
                if(spaceArray[i][j] == 0) {
                    g.setColor(Color.BLACK);
                } else if(spaceArray[i][j] == 1) {
                   g.setColor(Color.BLUE);
                }
                g.fillRect(j * spaceWidth, i * spaceHeight, spaceWidth, spaceHeight);
            }
        }
    }
}
