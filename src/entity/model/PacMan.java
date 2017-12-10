package entity.model;

import game.model.Game;

/**
 *
 * @author Michael Kramer <kramermp5@gmail.com>
 * @version .1
 */
public class PacMan extends Entity {
    
    public PacMan(Game parentGame, double xPos, double yPos) {
        super(parentGame, xPos, yPos);
        super.setSpeed(.5);
    }
    
    @Override
    public void move (){
        super.board.getSpace((int) xPos, (int) yPos).hasPacMan = false;
        super.move();
        super.board.getSpace((int) xPos, (int) yPos).hasPacMan = true;
        
    }
    
}
