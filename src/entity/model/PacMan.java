package entity.model;

import game.model.Game;

/**
 *
 * @author Michael Kramer <kramermp5@gmail.com>
 * @version .1
 */
public class PacMan extends Entity {
    
    public PacMan() {
        super();
        super.setSpeed(10);
        super.setDirection(Direction.RIGHT);
    }
    
    public PacMan(Game parentGame, double xPos, double yPos) {
        super(parentGame, xPos, yPos);
        super.setSpeed(.25);
    }
    
    @Override
    public void move (){
        super.setMovementState(MovementState.MOVING);
        if(super.board != null) {
            super.board.getSpace((int) xPos, (int) yPos).hasPacMan = false;
            super.move();
            super.board.getSpace((int) xPos, (int) yPos).hasPacMan = true;
        } else {
            super.move();
            //System.out.println(super.xPos);
        }
        
        
    }
    
}
