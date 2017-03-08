package me.nadd.tilegame.ai;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.entities.Player;


//XxXxGraffitixXxX

/**
 * Basic AI. Does nothing.
 * 
 */
public abstract class AI {
	private Entity entity;
	
        public void setEntity(Entity entity){
            this.entity = entity;
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
	 * Attack the first player possible to attack.
	 */
	protected void attackNearestPlayer() {
		for(Player p : Core.getPlayers()){
			if(getEntity().canAttack(p)){
				getEntity().attackEntity(p);
				break;
			}
		}
	}
        protected void attackNearest() {
            for(Entity e : Core.getEntities()){
                if(getEntity().canAttack(e)){
                    getEntity().attackEntity(e);
                    break;
                }
            }
        }
}
