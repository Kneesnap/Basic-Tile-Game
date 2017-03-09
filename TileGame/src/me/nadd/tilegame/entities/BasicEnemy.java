package me.nadd.tilegame.entities;

import me.nadd.tilegame.ai.BasicAI;
import me.nadd.tilegame.ai.ChargerAI;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import me.nadd.tilegame.ai.DumbAI;
import me.nadd.tilegame.ai.GuardAI;

public class BasicEnemy extends Entity {
        
	public BasicEnemy(int x, int y) {
		super(x, y);
		this.setAI(new BasicAI(null, 4, true, 300));
	}
            
	public ReadableColor getColor() {
            return Color.RED;
	}
}
