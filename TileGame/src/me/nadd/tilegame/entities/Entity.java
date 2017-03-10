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
 * @author Nathan Geddis
 */
public class Entity implements Drawable {
        
	private int x;
	private int y;
	private AI ai; // ai controls entity behavior
	private boolean isAlive = true; //isAlive represents 
	
	public Entity(int x, int y) {
		this(x, y, null);
	}
        // constructor take in x and y positions, and type of ai
	public Entity(int x, int y, AI ai) {
		this.x = x;
		this.y = y;
		this.ai = ai;
	}
  
	/**
	 * Runs every tick. Will run AI by default.
	 */
	public void onTick(){
		// Tick the AI if allowed.
		if(this.ai != null) {
			this.ai.setEntity(this); //shouldTick may check operations that require having the entity set
			if(this.ai.shouldTick())
				this.ai.update();
		}
		
		// Remove entity if it is not alive
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
		setPosition(x, getY());
	}
  
	/**
	 * Changes the Y coordinate of the entity.
	 */
	public void setY(int y){
		setPosition(getX(), y);
	}
	
	/**
	 * Sets the position of the entity without doing any checks.
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		getTile().onWalk(this);
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
	 * Checks if this entity attack another entity
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
	 * Check if this entity is alive
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
	
	/**
	 * Get the AI
	 */
	public AI getAI(){
		return ai;
	}
	
	/**
	 * Gets the distance to another entity
	 */
	public double distanceTo(Entity e) {
		return distanceTo(e.getTile());
	}
	
	/**
	 * Returns the distance to a tile.
	 * 
	 * a^2 + b^2 = c^2
	 */
	public double distanceTo(Tile t) {
		double aSqr = Math.pow(t.getX() - getX(), 2);
		double bSqr = Math.pow(t.getY() - getY(), 2);
		return Math.sqrt(aSqr + bSqr);
	}
	
	/**
	 * Returns the nearest entity.
	 * @return
	 */
	public Entity getNearestEntity() {
		double minDis = 999D;
		Entity closest = null;
		
		for (Entity check : Core.getEntities()) {
			//Can't pick ourself
			if (this == check)
				continue;
			//Is this entity closer than the one we currently have selected?
			double dis = distanceTo(check);
			if (dis <= minDis){
				minDis = dis;
				closest = check;
			}
		}
		
		return closest;
	}
}
