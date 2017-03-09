/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nadd.tilegame.entities;

import me.nadd.tilegame.ai.BasicAI;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

/**
 *
 * @author voice
 */
public class ZombieEnemy extends Entity {
    public ZombieEnemy(int x, int y) {
		super(x, y);
		this.setAI(new BasicAI(null, 11, false, 600));
	}
            
	public ReadableColor getColor() {
            return Color.GREEN;
	}
}
