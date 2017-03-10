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
			if (getEntity() == check || !(check instanceof Player))
				continue;
			double dis = getEntity().distanceTo(check);
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
		if (Core.getLivePlayers().isEmpty())
        	attackNearestPlayer();
        else
        	attackNearest();
	}
}
