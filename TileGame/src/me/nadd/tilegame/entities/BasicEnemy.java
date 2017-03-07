package me.nadd.tilegame.entities;

import me.nadd.tilegame.ai.BasicAI;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import me.nadd.tilegame.ai.DumbAI;

public class BasicEnemy extends Entity {
	public BasicEnemy(int x, int y) {
		super(x, y);
		this.setAI(new BasicAI(this, null, 1, true));
	}
	
	public ReadableColor getColor() {
		return Color.RED;
	}
}
