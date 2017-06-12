package me.nadd.tilegame.tiles;

import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.entities.Player;
import me.nadd.tilegame.gui.component.Images;

/**
 * The goal tile
 * 
 * @author Kneesnap
 */
public class GoalTile extends Tile {
	public GoalTile(int x, int y) {
		super(x, y, Images.GOAL);
	}

	@Override
	public void onWalk(Entity e) {
		if(e instanceof Player)
			Core.nextLevel();
	}
	
	public ReadableColor getColor() {
		return Color.CYAN;
	}
}
