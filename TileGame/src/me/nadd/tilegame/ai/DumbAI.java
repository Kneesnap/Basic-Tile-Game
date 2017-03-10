package me.nadd.tilegame.ai;

/**
 * Basic AI class that moves randomly.
 */
public class DumbAI extends BasicAI {

	@Override
	public void update() {
		randMove();
		attackNearestPlayer();
	}
}
