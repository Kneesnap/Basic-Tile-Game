package me.nadd.tilegame.entities;

import me.nadd.tilegame.ai.BasicAI;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

public class BasicEnemy extends Entity {
        
	public BasicEnemy(int x, int y) {
		super(x, y);
		this.setAI(new BasicAI(4, true, 300));
	}
            
	public ReadableColor getColor() {
		return Color.RED;
	}
}
