package entity.ui;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 *
 * @author Michael Kramer
 * @version .1
 */
public interface Drawable {
    
    public void drawEntity(Graphics g);
    public void setImageObserver(ImageObserver io);
}
