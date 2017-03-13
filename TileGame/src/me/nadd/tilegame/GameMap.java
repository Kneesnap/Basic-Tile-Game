package me.nadd.tilegame;

import java.util.ArrayList;
import java.util.List;

import me.nadd.tilegame.entities.BasicEnemy;
import me.nadd.tilegame.entities.ChargerEnemy;
import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.entities.GuardEnemy;
import me.nadd.tilegame.tiles.GoalTile;
import me.nadd.tilegame.tiles.ObstacleTile;
import me.nadd.tilegame.tiles.Tile;

/**
 * A map of the game. Contains tiles.
 */
public class GameMap {
	
	private Tile[][] tiles;
	
	public GameMap(int xSize, int ySize){
		this.tiles = new Tile[xSize][ySize];
	}
	
	/**
	 * Returns the X size of the map.
	 */
	public int getXSize() {
		return getTiles()[0].length;
	}
	
	/**
	 * Returns the Y size of the map.
	 */
	public int getYSize() {
		return getTiles().length;
	}
  
	/**
	 * Randomly generates the map.
	 * 
	 * TODO: Clean this up, create a modularized level generator that accepts settings.
	 */
	public void generateMap(){
		int entityCount = Core.getLevel();
		//TODO: Populate with holes, enemies, checkpoint, etc.
		//Populates Tiles
		for(int y = 0; y < tiles.length; y++)
			for(int x = 0; x < tiles[y].length; x++)
				addTile(new Tile(x, y));
		
		//Populates Obstacles
		for(int y = 0; y < getYSize(); y++){
			for(int x = 0; x < getXSize(); x++){
				if (((x == (int) (getXSize() / 2) || x == (int) ((getXSize() / 2) - 1))
						||(y == (int) (getYSize() / 2) || y == (int) ((getYSize() / 2) - 1)))
						&& (Math.random() < 0.25)
						&& x > 0
                		&& x < getXSize()
                		&& y > 0
                		&& y < getYSize()){
					addTile(new ObstacleTile(x, y));
					continue;
				}
			}
		}
		
		//Populates entities
		for (int i = 0 ; i < entityCount ; i++) {
			while (true){
				int randX = (int) ((Math.random() * (getXSize() / 2)) + ((getXSize() / 2) - 1));
				int randY = (int) (Math.random() * (getYSize() / 2));
				for (Entity e : Core.getEntities()){
					if (entityCount == 5 && (e.getX() == randX || e.getY() == randY || e.getX() == randX-1 || e.getY() == randY+1))
						continue;
					if (e.getX() == randX || e.getY() == randY)
						continue;
				}
				//1 for Charger, anything else is Basic.
				if (entityCount == 5){
					Core.getEntities().add(new BasicEnemy(randX, randY));
					Core.getEntities().add(new BasicEnemy(randX, randY));
					break;
				} else if (entityCount == 7) {
					Core.getEntities().add(new ChargerEnemy(randX, randY, 25, 5, 10));
					break;
				} else {
					if ((i + 1) % 3 == 0)
						Core.getEntities().add(new ChargerEnemy(randX, randY, 25, 5, 10));
					else if ((i + 1) % 4 == 0)
						Core.getEntities().add(new GuardEnemy(randX, randY, 50));
					else
						Core.getEntities().add(new BasicEnemy(randX, randY));
					break;
				}
			}
		}
		
		//Sets the goal tile.
		addTile(new GoalTile(getXSize() - 1, 0));
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
		if(x < 0 || y < 0 || y >= getYSize() || x >= getXSize())
			return null;
		return tiles[y][x];
	}
  
	/**
	 * Sets the tile at a given position.
	 */
	public void setTile(int x, int y, Tile t){
		if(x < 0 || y < 0 || y >= getYSize() || x >= getXSize())
			return;
		tiles[y][x] = t;
	}
	
	public void addTile(Tile t) {
		setTile(t.getX(), t.getY(), t);
	}
}
