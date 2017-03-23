package me.nadd.tilegame;

import java.util.ArrayList;
import java.util.List;

import me.nadd.tilegame.entities.BasicEnemy;
import me.nadd.tilegame.entities.ChargerEnemy;
import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.entities.ZombieEnemy;
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
         * Generates obstacles in a plus shape given by dividing the x and y
         * axes.
         */
        public void generateObstacles(){
		for(int y = 0; y < getYSize(); y++){
			for(int x = 0; x < getXSize(); x++){
				if (((x == (int) (getXSize() / 2) || x == (int) ((getXSize() / 2) - 1))
                                //Generates in the middle of the map horizontally
                                        
                                ||(y == (int) (getYSize() / 2) || y == (int) ((getYSize() / 2) - 1)))
                                //Generates in the middle of the map vertically.
                                        
				&& (Math.random() < 0.25)
                                //Generates the random chance of placing a tile.
                                        
				&& x > 0
                                //Stops generation at the left wall.
                                        
                		&& x < getXSize()
                                //Stops generation at the right wall.
                                        
                		&& y > 0 
                                //Stops generation at the top wall.
                                        
                		&& y < getYSize()){
                                //Stops generation at the bottom wall.
                                        
                                        //Adds a new wall (ObstacleTile).
					addTile(new ObstacleTile(x, y));
					continue;
				}
			}
		}
        }
        public void generateEntities(int level){
            for (int i = 0 ; i < level ; i++) {
			while (true){
                                //Creates a random point, checks whether or not
                                //to place an enemy there. Repeats if not.
				int randX = (int) ((Math.random() * (getXSize() / 2)) + ((getXSize() / 2) - 1));
				int randY = (int) (Math.random() * (getYSize() / 2));
				for (Entity e : Core.getEntities()){
					if (level == 5 && (e.getX() == randX || e.getY() == randY || e.getX() == randX-1 || e.getY() == randY+1))
						continue;
					if (e.getX() == randX || e.getY() == randY)
						continue;
				}
                                
				//Decides what type/amount of enemy to place
                                //based on the level. Plays different music.
                                
                                //Level 5 spawns double the enemies.
				if (level == 5){
					Core.getEntities().add(new BasicEnemy(randX, randY));
					Core.getEntities().add(new BasicEnemy(randX, randY));
					break;
                                
                                //Level 7 spawns only chargers.
				} else if (level == 7) {
					Core.getEntities().add(new ChargerEnemy(randX, randY));
					break;
                                
                                /*
                                Otherwise, the normal operation is to spawn one
                                charger for every 3 levels, and one zombie for
                                every 4 levels. All other entities are basic 
                                enemies.
                                        */
				} else {
					if ((i + 1) % 3 == 0)
						Core.getEntities().add(new ChargerEnemy(randX, randY));
					else if ((i + 1) % 4 == 0)
						Core.getEntities().add(new ZombieEnemy(randX, randY));
					else
						Core.getEntities().add(new BasicEnemy(randX, randY));
					break;
				}
			}
		}
        }
            
	/**
	 * Randomly generates the map.
	 * 
	 * TODO: Clean this up, create a modularized level generator that accepts settings.
	 */
	public void generateMap(){
		int level = Core.getLevel();
		
		//  SET ALL TILES TO DEFAULT  //
		for(int y = 0; y < tiles.length; y++)
			for(int x = 0; x < tiles[y].length; x++)
				addTile(new Tile(x, y));
		
		//  ADD OBSTACLE TILES  //
		generateObstacles();
		
		//  ADD ENTITIES  //
		generateEntities(level);
		
		//  CREATE GOAL  //
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
	
        /**
         * Places a tile where its x and y values specify for it to be.
         * @param t 
         */
	public void addTile(Tile t) {
		setTile(t.getX(), t.getY(), t);
	}
}
