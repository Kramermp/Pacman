package mainmenu.ui;

import entity.model.Direction;
import entity.model.Enemy;
import entity.model.Entity;
import entity.model.Entity.MovementState;
import entity.model.PacMan;
import entity.ui.Drawable;
import entity.ui.EnemyDisplay;
import entity.ui.PacManDisplay;
import game.ui.GameUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import mainmenu.controller.MainMenuCntl;

/**
 * This class is a JPanel that contains the content of the Main Menu. It utilizes
 * the MainMenuCntl to interact with the User Interface.
 * 
 * @author Michael Kramer <kramermp5@gmail.com>
 * @version .1
 */
public class MainMenuUI extends JPanel {
    private MainMenuCntl parentCntl;
    private PacMan pacmanModel;
    private PacManDisplay pacmanDisplay;
    private EnemyDisplay[] enemyDisplays;
    
    private Font levelFont;
    
    private BufferedImage logoImage;
    
    private Timer timer = new Timer(33, (ActionEvent ae) -> {
        this.updateUI();
    });
    
    
    /**
     * This constructor creates a MainMenuUI object
     * @param parentCntl The MainMenuCntl that this UI uses to interact with th
     */
    public MainMenuUI(MainMenuCntl parentCntl, PacMan pacmanModel, Enemy[] enemies) {
        this.parentCntl = parentCntl;
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        
        pacmanDisplay = new PacManDisplay(pacmanModel, this);
        pacmanModel.setXPos(500);
        Enemy.resetEnemyNumber();
        enemyDisplays = new EnemyDisplay[enemies.length];
        for(int i = 0; i < enemies.length; i++) {
            enemies[i].setXPos(i * 100 );
            enemyDisplays[i] = new EnemyDisplay(enemies[i], this);
        }
        this.loadFont();
        this.loadImage();
        addComponents();
        
    }
    
    private void addComponents() {
        
        
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel logoLbl = new JLabel();
        logoLbl.setIcon(new ImageIcon(logoImage));
        logoLbl.setHorizontalAlignment(JLabel.CENTER);
        logoLbl.setVerticalAlignment(JLabel.NORTH);
        c.gridx = 0;
        c.gridy =0;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.ipady = 25;
        c.weighty = 1;
        add(logoLbl, c);
        
        
        JLabel pressEnter = new JLabel();
        float fontSize = this.getHeight() / 10;
        pressEnter.setFont(levelFont.deriveFont(fontSize));
        pressEnter.setText("Press Enter to Play");  
        c.gridx = 0;
        c.gridy =1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.NONE;
        c.weightx = .25;
        c.ipady = 25;
        c.weighty = 5;
        
        JButton playBtn = new JButton("Play");
//        System.out.println("Test");
        playBtn.addActionListener((ActionEvent ae) -> { 
            System.out.println("playBtn click event registered.");
            parentCntl.startGame();
        });
        
        add(playBtn, c);
        
        
    }
    
    public void start() {
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        //System.out.println("Painting Component");
        super.paintComponent(g);
        //super.paintComponent(g);
        pacmanDisplay.drawEntity(g);
        for(int i = 0; i < enemyDisplays.length; i++) {
            enemyDisplays[i].drawEntity(g);
        }
        
        float fontSize = this.getWidth() / 10; 
        Font actualFont = levelFont.deriveFont(fontSize);
        g.setFont(actualFont);
        g.setColor(Color.WHITE);
        String message = ("PACMAN");
        //g.drawChars(message, 0, 0, 50, 50);
        //g.drawString(message, (this.getWidth() / 2) -  (int) (fontSize * 2.1), (this.getHeight() / 4));
            
        
        //g.drawString(enterMessage, (this.getWidth() / 2) -  (int) (enterFontSize / 2 * 2.1), (this.getHeight() / 3));
    }
    
    
    private void loadFont() {
        System.out.println("Loading Font");
        try {
            levelFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/invasion2000/INVASION2000.ttf"));
        } catch (FontFormatException ex) {
            Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, "trest", ex);
        } catch (FileNotFoundException ex) {
            System.err.println("Font File not found");
        }catch (IOException ex) {
            Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadImage() {
        try {
            logoImage = ImageIO.read(new File("resources/images/logo.png"));
        } catch (FileNotFoundException ex) {
            System.err.println("Font File not found");
        }catch (IOException ex) {
            Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
