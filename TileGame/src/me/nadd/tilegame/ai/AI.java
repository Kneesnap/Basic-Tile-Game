package me.nadd.tilegame.ai;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.entities.Player;


/**
 * Basic AI. Does nothing.
 * 
 */
public abstract class AI {
	private Entity entity;
	private int tickDelay;
	
	/**
	 * Sets the entity this controls.
	 */
	public void setEntity(Entity entity){
		this.entity = entity;
	}
	
	/**
	 * Gets the interval of ticks the AI should be run at.
	 */
	public int getTickDelay() {
		return this.tickDelay;
	}
	
	/**
	 * Sets the tick delay
	 */
	public void setTickDelay(int delay) {
		this.tickDelay = delay;
	}
	
	/**
	 * Ticks the AI.
	 */
	public abstract void update();
	
	/**
	 * Gets the entity this belongs to.
	 */
	public Entity getEntity() {
		return this.entity;
	}
	
	/**
	 * Attack the nearest player if possible.
	 */
	protected void attackNearestPlayer() {
                //Checks if the entity can attack any player.
		for(Player p : Core.getPlayers()){
			if(getEntity().canAttack(p)){                            
                                //If there is an attackable player,
                                //the first one in the player list is attacked.
				getEntity().attackEntity(p);
				break;
			}
		}
	}
	
	/**
	 * Attacks the nearest entity if possible.
	 */
	protected void attackNearest() {
                //Checks if the entity can attack any entity.
		for(Entity e : Core.getEntities()){
			if(getEntity().canAttack(e)){
                                //If there is an attackable entity,
                                //the first one in the entity list is attacked.
				getEntity().attackEntity(e);
				break;
			}
		}
	}
	
	/**
	 * Whether or not this AI should tick.
	 * @return
	 */
	public boolean shouldTick() {
		return getEntity().isAlive() && Core.haveTicksPassed(getTickDelay());
	}
}
