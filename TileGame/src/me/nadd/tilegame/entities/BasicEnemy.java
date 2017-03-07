package me.nadd.tilegame.entities;

import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import me.nadd.tilegame.ai.DumbAI;

public class BasicEnemy extends Entity {
	public BasicEnemy(int x, int y) {
		super(x, y);
		this.setAI(new DumbAI(this));
	}
	
	public ReadableColor getColor() {
		return Color.RED;
	}
}
