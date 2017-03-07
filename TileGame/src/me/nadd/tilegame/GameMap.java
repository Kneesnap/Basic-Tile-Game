package me.nadd.tilegame;

import java.util.ArrayList;
import java.util.List;

import me.nadd.tilegame.entities.BasicEnemy;
import me.nadd.tilegame.tiles.Tile;

/**
 * A map of the game. Contains tiles.
 */
public class GameMap {
	
	private Tile[][] tiles;
	
	public GameMap(int xSize, int ySize){
		this.tiles = new Tile[xSize][ySize];
		generateMap();
	}
  
	/**
	 * Randomly generates the map.
	 */
	public void generateMap(){
		//TODO: Populate with holes, enemies, checkpoint, etc.
		for(int y = 0; y < tiles.length; y++)
			for(int x = 0; x < tiles[y].length; x++)
				tiles[y][x] = new Tile(x, y);
		Core.getEntities().add(new BasicEnemy(4, 4));
	}
  
	
	/**
	 * Returns the raw tile array.
	 */
	public Tile[][] getTiles() {
		return this.tiles;
	}
	
	/**
	 * Gets an iterable list of all tiles.
	 */
	public List<Tile> getAllTiles(){
		List<Tile> tl = new ArrayList<>();
		for(Tile[] arr : tiles)
			for(Tile tile : arr)
				tl.add(tile);
		return tl;
	}
  
	/**
	 * Gets the tile at a given position.
	 */
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || y >= tiles.length || x >= tiles[y].length)
			return null;
		return tiles[y][x];
	}
  
	/**
	 * Sets the tile at a given position.
	 */
	public void setTile(int x, int y, Tile t){
		if(x < 0 || y < 0 || y >= tiles.length || x >= tiles[y].length)
			return;
		tiles[y][x] = t;
	}
}
