package me.nadd.tilegame.tiles;

import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.Drawable;
import me.nadd.tilegame.GameRender;
import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.gui.GUI;
import static me.nadd.tilegame.gui.GUI.getWidth;
import me.nadd.tilegame.gui.component.Image;
import me.nadd.tilegame.gui.component.Images;
import org.lwjgl.opengl.GL11;

/**
 * Tile - Represents a basic tile on the map.
 * 
 * Created March 2, 2017.
 * @author Kneesnap
 */
public class Tile implements Drawable {
	private final int x;
	private final int y;
        private Image i;
  
	public Tile(int x, int y, Images i){
		this.x = x;
		this.y = y;
                if (i != null)
                    this.i = new Image(0, 0, getScaledTileSize(), i);
                else{
                    if(Math.random() < 0.25)
                        this.i = new Image(0, 0, getScaledTileSize(), Images.BLANK);
                    else if (Math.random() < 0.25)
                        this.i = new Image(0, 0, getScaledTileSize(), Images.BLANK3);
                    else
                        this.i = new Image(0, 0, getScaledTileSize(), Images.BLANK2);
                }
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
        
        public boolean isObstacle(){
                return (this instanceof ObstacleTile);
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
		return Color.DKGREY;
	}

    @Override
    public void render(GUI gui) {
        gui.color(getColor());
        int x = getX() * getScaledTileSize();
        int y = getY() * getScaledTileSize();
	i.setLoc(x, y);
        i.render(gui);
        
    }
    
    public static int getScaledTileSize() {
        return GameRender.getCurrentScreen().getWidth() / (Core.getMap().getTiles().length * 2);
    }
}
