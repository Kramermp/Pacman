package entity.ui;

import board.model.Board;
import entity.model.Entity;
import entity.model.PacMan;
import game.ui.GameUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import mainmenu.ui.MainMenuUI;

/**
 * This class serves as the model to view adapter for the pacman class.
 * @author Michael Kramer
 * @version .1
 */
public class PacManDisplay extends Display implements Drawable {
    private static final String PACMAN_IMAGE_PATH = "resources/images/pacman/";
    private MainMenuUI mainmenuUI;
    private JPanel parentDisplay;
    private PacMan source;
    private ImageObserver observer;
    private BufferedImage openMouthImageUp;
    private BufferedImage openMouthImageDown;
    private BufferedImage openMouthImageLeft;
    private BufferedImage openMouthImageRight;
    private BufferedImage closedMouthImageUp;
    private BufferedImage closedMouthImageDown;
    private BufferedImage closedMouthImageLeft;
    private BufferedImage closedMouthImageRight;
    private BufferedImage pacmanNoMouthImage;
    private BufferedImage currentImage;
    private final Board board;
    
    
    public PacManDisplay(PacMan pacman, MainMenuUI mainmenu) {
        this.mainmenuUI = mainmenu;
        this.source = pacman;
        this.board = null;
        loadImage();
        currentImage = openMouthImageRight;
        
    }
    
    public PacManDisplay (PacMan pacman, Board board) {
        this.source = pacman;
        this.board = board;
        loadImage();
        currentImage = pacmanNoMouthImage;
    }
    
    public void drawEntity(Graphics g) {
        //System.out.println("Drawing PacManEntity");
        
        int pacmanHeight = mainmenuUI.getSize().height / 10;
        Entity.maxX = mainmenuUI.getWidth();
        Entity.minX = 0 - (mainmenuUI.getWidth() / 10);
        
        int realXPos =(int)(source.getXPos());
        int realYPos = (int) mainmenuUI.getSize().height / 2;
        
        g.drawImage(getCurrentImage(), realXPos, realYPos, getPacManWidth(), pacmanHeight, null);
    }

    @Override
    public void drawEntity(Graphics g, GameUI parentDisplay) {
        //System.out.println("Drawing PacManEntity");
        int spaceHeight = parentDisplay.getSize().height / board.getHeight();
        int spaceWidth = parentDisplay.getSize().width / board.getWidth();
        Entity.minX = -1;
        
        int pacmanHeight = spaceHeight - 10;
        int pacmanWidth = spaceWidth - 10;
        
        
        int realXPos = (int) (source.getXPos() * spaceWidth + 5);
        int realYPos = (int) (source.getYPos() * spaceHeight) + 5; 
        
        g.drawImage(getCurrentImage(), realXPos, realYPos, pacmanWidth, pacmanHeight, observer);
    }
    
    public BufferedImage getCurrentImage() {
        if(source.getState() == Entity.MovementState.SPAWNED) {
            return pacmanNoMouthImage;
        }
        if(System.currentTimeMillis() % 500 > 250) {
            return getClosedMouthImage();
        } else {
            return getOpenMouthImage();
        }
    }
    
    private BufferedImage getClosedMouthImage() {
        switch(source.getDirection()) { 
            case UP:
                return closedMouthImageUp;
            case DOWN:
                return closedMouthImageDown;
            case LEFT:
                return closedMouthImageLeft;
            case RIGHT:
                return closedMouthImageRight;
            default:
                return closedMouthImageDown;
        }
    }
    
    private BufferedImage getOpenMouthImage() {
        switch(source.getDirection()) {
            case UP:
                return openMouthImageUp;
            case DOWN:
                return openMouthImageDown;
            case LEFT:
                return openMouthImageLeft;
            case RIGHT:
                return openMouthImageRight;
            default:
                return openMouthImageDown;
        }
    }
    
    @Override
    public void setImageObserver(ImageObserver io) {
        this.observer = io;
    }
    
    private void loadImage() {
        try {
            openMouthImageUp = ImageIO.read(new File(PACMAN_IMAGE_PATH + "pacman-openmouthup.png"));
            openMouthImageDown = ImageIO.read(new File(PACMAN_IMAGE_PATH + "pacman-openmouthdown.png"));
            openMouthImageLeft = ImageIO.read(new File(PACMAN_IMAGE_PATH + "pacman-openmouthleft.png"));
            openMouthImageRight = ImageIO.read(new File(PACMAN_IMAGE_PATH + "pacman-openmouthright.png"));
            closedMouthImageUp = ImageIO.read(new File(PACMAN_IMAGE_PATH + "pacman-closedmouthup.png"));
            closedMouthImageDown = ImageIO.read(new File(PACMAN_IMAGE_PATH + "pacman-closedmouthdown.png"));
            closedMouthImageLeft = ImageIO.read(new File(PACMAN_IMAGE_PATH + "pacman-closedmouthleft.png"));
            closedMouthImageRight = ImageIO.read(new File(PACMAN_IMAGE_PATH + "pacman-closedmouthright.png"));
            pacmanNoMouthImage = ImageIO.read(new File(PACMAN_IMAGE_PATH + "pacman-nomouth.png"));
        } catch (IOException ex) {
            System.err.println("IOException reading PacMan Image");
        }
    }
    
    public int getPacManWidth() {
        if(this.board != null) {
            return parentDisplay.getSize().width / board.getWidth() - 10;
        } else {
            return mainmenuUI.getSize().height / 10;
        }
    }
}
