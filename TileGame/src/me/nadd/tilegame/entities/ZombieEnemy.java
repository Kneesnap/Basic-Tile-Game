package me.nadd.tilegame.entities;

import me.nadd.tilegame.ai.BasicAI;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

/**
 * A basic zombie enemy.
 */
public class ZombieEnemy extends Entity {
    public ZombieEnemy(int x, int y) {
		super(x, y);
		this.setAI(new BasicAI(11, false, 600));
	}
            
	public ReadableColor getColor() {
		return Color.GREEN;
	}
}
