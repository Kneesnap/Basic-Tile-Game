package me.nadd.tilegame;

import me.nadd.tilegame.tiles.Tile;

import org.lwjgl.util.ReadableColor;

/**
 * Represents any object that can be drawn on the map.
 * @author Kneesnap
 */
public interface Drawable {

	public Tile getTile();
	
	public ReadableColor getColor();
}
