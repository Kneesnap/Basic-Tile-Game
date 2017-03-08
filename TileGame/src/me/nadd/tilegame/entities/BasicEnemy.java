package me.nadd.tilegame.entities;

import me.nadd.tilegame.ai.BasicAI;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import me.nadd.tilegame.ai.DumbAI;

public class BasicEnemy extends Entity {
	public BasicEnemy(int x, int y) {
		super(x, y);
		this.setAI(new BasicAI(null, 4, true, 75));
	}
	
	public ReadableColor getColor() {
		return Color.RED;
	}
}
