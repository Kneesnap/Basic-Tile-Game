package me.nadd.tilegame.gui;

import org.lwjgl.opengl.GL11;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.Drawable;

public class GUIGame extends GUI {

	@Override
	protected void render() {
		this.setBackgroundColor(0, 0, 0, 0);
		
		//  DRAW MAP  //
		Core.getMap().getAllTiles().forEach(this::draw);
		
		//  DRAW ENTITIES  //
		Core.getEntities().forEach(this::draw);
	}
	
	/**
	 * Draws a drawable tile on the map.
	 */
	public void draw(Drawable d) {
		GL11.glColor3f(d.getColor().getRed() / 255F, d.getColor().getGreen() / 255F, d.getColor().getBlue() / 255F);
		int x = d.getTile().getX() * getScaledTileSize();
		int y = d.getTile().getY() * getScaledTileSize();
		this.drawRect(x, y, x + getScaledTileSize(), y + getScaledTileSize());
	}
	
	/**
	 * Returns the size of each tile.
	 */
	public int getScaledTileSize() {
		return getWidth() / (Core.getMap().getTiles().length * 2);
	}
	@Override
	public void onClose() {
		
	}

	@Override
	public void initGUI() {
		
	}
}
