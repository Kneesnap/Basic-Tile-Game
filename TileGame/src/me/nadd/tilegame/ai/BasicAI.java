package me.nadd.tilegame.ai;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.Utils;
import static me.nadd.tilegame.Utils.randChoice;
import me.nadd.tilegame.entities.Entity;

/**
 * A simple basic test AI.
 */
public class BasicAI extends MonsterAI {
    private int aggressionLevel;
    private boolean randMovement;
    
    public BasicAI(){
        this(4, false, 5);
    }
    
    public BasicAI(int aggressionLevel, boolean randMovement, int tickDelay){
        this.aggressionLevel = aggressionLevel;
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
        //  ATTACK  //
        this.defaultAttack();
    }
    
    /**
     * Moves toward the nearest player horizontally if the horizontal distance
     * is greater, vertically if otherwise.
     */
    protected void smartMove() {
        //Gets the difference between the controlled entity's target's position
        //and the controlled entity's position.
        int xDif = (getTarget().getX() - getEntity().getX());
        int yDif = (getTarget().getY() - getEntity().getY());
        
        //Checks which axis' difference is greater and moves toward the 
        //controlled entity's target on that axis.
        if (Math.abs(xDif) > Math.abs(yDif) && xDif != 0)
            getEntity().moveX(xDif / Math.abs(xDif));
        else if (yDif != 0)
            getEntity().moveY(yDif / Math.abs(yDif));
    }
    
    /**
     * Moves randomly.
     */
    protected void randMove(){
        int moveBy = randChoice() == 0 ? 1 : -1;
        if (randChoice() == 0)
            getEntity().moveX(moveBy);
        else
            getEntity().moveY(moveBy);
    }
    
    /**
     * Provides the decision on whether the controlled entity will move 
     * randomly or intelligently.
     * False is random, true is intelligently.
     * @return 
     */
    private boolean isSmartMove() {
        return (Utils.randInt(1, 10) < aggressionLevel);
    }
    
    /**
     * Returns whether or not the controlled entity is set to move randomly
     * while not moving intelligently.
     * @return 
     */
    public boolean isRandomMove(){
        return randMovement;
    }
    
    /**
     * Sets the aggression level.
     * @param aggressionLevel 
     */
    public void setAgression(int aggressionLevel){
        this.aggressionLevel = aggressionLevel;
    }
    
    /**
     * Sets if or if not the controlled entity will move randomly.
     * @param randMovement 
     */
    public void setRandomMove(boolean randMovement){
        this.randMovement = randMovement;
    }
    
    /**
     * Returns the aggression level.
     * @return 
     */
    public int getAgression(){
        return aggressionLevel;
    }
    
    /**
     * Returns the controlled entity's target.
     * @return 
     */
    @Override
    public Entity getTarget() {
	return Core.getLivePlayers().isEmpty() ? getEntity().getNearestEntity() : super.getTarget();
    }
}
