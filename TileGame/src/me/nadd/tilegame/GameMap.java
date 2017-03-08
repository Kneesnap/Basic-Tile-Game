package me.nadd.tilegame;

import java.util.ArrayList;
import java.util.List;

import me.nadd.tilegame.entities.BasicEnemy;
import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.tiles.Tile;

/**
 * A map of the game. Contains tiles.
 */
public class GameMap {
	
	private Tile[][] tiles;
        private int xSize;
        private int ySize;
	
	public GameMap(int xSize, int ySize){
		this.tiles = new Tile[xSize][ySize];
                this.xSize = xSize;
                this.ySize = ySize;
	}
  
	/**
	 * Randomly generates the map.
	 */
	public void generateMap(int numberOfEntities){
		//TODO: Populate with holes, enemies, checkpoint, etc.
		for(int y = 0; y < tiles.length; y++)
			for(int x = 0; x < tiles[y].length; x++)
				tiles[y][x] = new Tile(x, y);
                
                for (int i = 0 ; i < numberOfEntities ; i++)
                    while (true){
                        int randX = (int) ((Math.random() * (xSize / 2)) + ((xSize / 2) - 1));
                        int randY = (int) (Math.random() * (ySize / 2));
                        for (Entity e : Core.getEntities())
                            if (e.getX() == randX || e.getY() == randY)
                                continue;
                        //1 for Charger, anything else is Basic.
                        if ((i+1) % 3 == 0)
                            Core.getEntities().add(new BasicEnemy(randX, randY, 1));
                        else
                            Core.getEntities().add(new BasicEnemy(randX, randY, 0));
                        break;
                    }
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
