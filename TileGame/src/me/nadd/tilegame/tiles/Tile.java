package me.nadd.tilegame.tiles;

import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.Drawable;
import me.nadd.tilegame.entities.Entity;

/**
 * Tile - Represents a basic tile on the map.
 * 
 * Created March 2, 2017.
 * @author Kneesnap
 */
public class Tile implements Drawable {
	private final int x;
	private final int y;
  
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
	}
  
	/**
	 * Returns the X Coordinate of this tile.
	 */
	public int getX(){
		return this.x;
	}
  
	/**
	 * Returns the Y Coordinate of this tile.
	 */
	public int getY(){
		return this.y;
	}
  
	/**
	 * Can an entity walk on this tile?
	 */
	public boolean isWalkable(Entity ent){
		return !this.isOccupied();
	}
  
	/**
	 * Runs when an entity steps on this tile.
	 */
	public void onWalk(Entity ent) {
		
	}
   
	/**
	 * Returns if this square is occupied.
	 */
	public boolean isOccupied() {
		for(Entity entity : Core.getEntities())
			if(entity.getX() == this.getX() && entity.getY() == this.getY())
				return true;
		return false;
	}
   
    public boolean equals(Object obj) {
    	Tile tile = (Tile)obj;
    	return tile.getX() == this.getX() && tile.getY() == this.getY();
    }

	@Override
	public Tile getTile() {
		return this;
	}

	@Override
	public ReadableColor getColor() {
		return Color.GREY;
	}
}
