package me.nadd.tilegame.ai;

import java.util.Random;

import me.nadd.tilegame.entities.Entity;

/**
 * Basic AI class that moves randomly.
 */
public class DumbAI extends AI {


	@Override
	public void update() {
		Random random = new Random();
		boolean xAxis = random.nextInt(2) == 0;
		int distance = random.nextInt(2) == 0 ? 1 : -1;
		if (xAxis)
			getEntity().moveX(distance);
		else
			getEntity().moveY(distance);
		attackNearestPlayer();
	}
}
