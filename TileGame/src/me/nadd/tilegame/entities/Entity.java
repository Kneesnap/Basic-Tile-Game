package me.nadd.tilegame.entities;

import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.Drawable;
import me.nadd.tilegame.ai.AI;
import me.nadd.tilegame.tiles.Tile;

/**
 * Base Entity class
 * 
 * Created March 2, 2017.
 * @author Kneesnap
 */
public class Entity implements Drawable {
  
	private int x;
	private int y;
	private AI ai;
	private boolean isAlive = true;
	
	public Entity(int x, int y) {
		this(x, y, null);
	}
  
	public Entity(int x, int y, AI ai) {
		this.x = x;
		this.y = y;
		this.ai = ai;
	}
  
	/**
	 * Runs every tick. Will run AI by default.
	 */
	public void onTick(){
		if(this.ai != null && this.isAlive()){
                        this.ai.setEntity(this);
			this.ai.update();
                }
		if(!this.isAlive())
			Core.getEntities().remove(this);
	}
  
	/**
	 * Get the X coordinate of this entity.
	 */
	public int getX(){
		return this.x;
	}
   
	/**
	 * Get the Y coordinate of this entity.
	 */
	public int getY(){
		return this.y;
  	}
  
	/**
	 * Changes the X coordinate of the entity.
	 */
	public void setX(int x){
		this.x = x;
	}
  
	/**
	 * Changes the Y coordinate of the entity.
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Gets the tile the entity is currently on.
	 */
	public Tile getTile() {
		return Core.getMap().getTile(getX(), getY());
	}
	
	/**
	 * Move over the X axis.
	 */
	public void moveX(int xInc){
		int inc = xInc > 0 ? 1 : -1;
		while(xInc != 0) {
			int newX = getX() + inc;
			xInc -= inc;
			Tile tile = Core.getMap().getTile(newX, getY());
			if(tile != null && tile.isWalkable(this))
				setX(newX);
		}
	}
	
	/***
	 * Move over the Y axis.
	 */
	public void moveY(int yInc){
		int inc = yInc > 0 ? 1 : -1;
		while(yInc != 0) {
			int newY = getY() + inc;
			yInc -= inc;
			Tile tile = Core.getMap().getTile(getX(), newY);
			if(tile != null && tile.isWalkable(this))
				setY(newY);
		}
	}

	@Override
	public ReadableColor getColor() {
		return Color.YELLOW;
	}
	
	/**
	 * Can this entity attack another entity?
	 */
	public boolean canAttack(Entity ent) {
		int xDif = Math.abs(getX() - ent.getX());
		int yDif = Math.abs(getY() - ent.getY());
		return xDif + yDif == 1;
	}
	
	/**
	 * Attack another entity. Silently fails if canAttack returns false.
	 */
	public void attackEntity(Entity ent) {
		if(!canAttack(ent))
			return;
		ent.kill();
	}
	
	/**
	 * Set this entity as dead.
	 */
	public void kill() {
		this.isAlive = false;
	}
	
	/**
	 * Is this entity alive?
	 */
	public boolean isAlive() {
		return this.isAlive;
	}
	
	/**
	 * Set the AI.
	 */
	public void setAI(AI ai) {
		this.ai = ai;
	}
        public AI getAI(){
            return ai;
        }
}
