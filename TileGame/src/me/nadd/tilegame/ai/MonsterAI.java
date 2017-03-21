package me.nadd.tilegame.ai;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.entities.Player;

/**
 * A base monster AI.
 * @author Kneesnap
 */
public abstract class MonsterAI extends AI{
	
	public Entity getTarget() {
		return getNearestPlayer();
	}
	
	public abstract void update();
	
	/**
	 * Gets the closest player.
	 */
	protected Player getNearestPlayer() {
		if(getEntity() == null)
			return null;
		
		double minDis = 999D;
		Player closest = null;
		
		for (Entity check : Core.getEntities()) {
			//Skip the entity if it is this entity, or it's not a player.
			if (getEntity() == check || !(check instanceof Player))
				continue;
			
			double dis = getEntity().distanceTo(check);
			//If the entity we're checking is closer than the current closest entity.
			if (dis <= minDis){
				minDis = dis;
				closest = (Player)check;
			}
		}
		return closest;
	}
	
	@Override
	public boolean shouldTick() {
		return getTarget() != null && super.shouldTick();
	}
	
	protected void defaultAttack() {
		//If there are no players, the enemies turn into cannibals. Otherwise they attack the player.
		if (!Core.getLivePlayers().isEmpty())
                    attackNearestPlayer();
                else
                    attackNearest();
	}
}
