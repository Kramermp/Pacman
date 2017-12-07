package entity.ui;

import game.ui.GameUI;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 *
 * @author Michael Kramer
 * @version .1
 */
public interface Drawable {
    
    public void drawEntity(Graphics g, GameUI parentDisplay);
    public void setImageObserver(ImageObserver io);
}
