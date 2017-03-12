package me.nadd.tilegame.ai;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.Utils;
import static me.nadd.tilegame.Utils.randChoice;
import me.nadd.tilegame.entities.Entity;

/**
 * A simple basic test AI.
 */
public class BasicAI extends MonsterAI {
    private int agressionLevel;
    private boolean randMovement;
    
    public BasicAI(){
        this(4, false, 75);
    }
    
    public BasicAI(int agressionLevel, boolean randMovement, int tickDelay){
        this.agressionLevel = agressionLevel;
        this.randMovement = randMovement;
        this.setTickDelay(tickDelay);
    }
    
    @Override
    public void update() {
        //  MOVE  //
        if (isSmartMove())
        	smartMove();
        else if (isRandomMove())
        	randMove();
        
        this.defaultAttack();
    }
    
    protected void smartMove() {
        int xDif = (getTarget().getX() - getEntity().getX());
        int yDif = (getTarget().getY() - getEntity().getY());
        
        if (Math.abs(xDif) > Math.abs(yDif) && xDif != 0)
        	getEntity().moveX(xDif / Math.abs(xDif));
        else if (yDif != 0)
            getEntity().moveY(yDif / Math.abs(yDif));
    }
    
    protected void randMove(){
        int moveBy = randChoice() == 0 ? 1 : -1;
        if (randChoice() == 0)
            getEntity().moveX(moveBy);
        else
            getEntity().moveY(moveBy);
    }
    
    private boolean isSmartMove() {
        return (Utils.randInt(1, 10) < agressionLevel);
    }
    
    public boolean isRandomMove(){
        return randMovement;
    }
    
    public void setAgression(int agressionLevel){
        this.agressionLevel = agressionLevel;
    }
    
    public void setRandomMove(boolean randMovement){
        this.randMovement = randMovement;
    }
    
    public int getAgression(){
        return agressionLevel;
    }

	@Override
	public Entity getTarget() {
		return Core.getLivePlayers().isEmpty() ? getEntity().getNearestEntity() : super.getTarget();
	}
}
