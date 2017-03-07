package me.nadd.tilegame;

import java.util.ArrayList;
import java.util.List;

import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.entities.Player;
import me.nadd.tilegame.gui.GUIGame;
import me.nadd.tilegame.gui.GUIMainMenu;

/**
 * Core - The main core of the game.
 * Manages the creation of the game.
 * 
 * Created March 2, 2017.
 * @author Kneesnap
 */
public class Core {
  
	private static int MAP_SIZE_X = 16;
	private static int MAP_SIZE_Z = 16;
  
	private static boolean isGoing;
	private static List<Entity> entities = new ArrayList<>();
	private static GameMap gameMap = new GameMap(MAP_SIZE_X, MAP_SIZE_Z);
  
	public static void initGame(){
		System.out.println("TileGame - Starting...");
		GameRender.createWindow();
		startGame();
	}
  
	/**
	 * Called to shutdown the program.
	 */
	public static void closeGame(){
		System.out.println("TileGame - Stopping...");
		stopGame();
		System.exit(0);
	}
  
	/**
	 * Starts the game. Fails silently if a game is already taking place.
	 */
	public static void startGame(){
		if(!isGoing){
			isGoing = true;
			getEntities().clear();
			gameMap.generateMap();
			getEntities().add(new Player(1, 1));
			GameRender.openGUI(new GUIGame());
		}
	}
  
	/**
	 * Stops the game. Fails silently if there is no current game.
	 */
	public static void stopGame() {
		if(isGoing){
			GameRender.openGUI(new GUIMainMenu());
			isGoing = false;
		}
	}
	
	public static void doGameTick(){
		//  ENTITY TICK  //
		List<Entity> ent = new ArrayList<Entity>();
		entities.forEach(ent::add);
		ent.forEach(Entity::onTick);
	}
  
	/**
	 * Is the game currently going?
	 */
	public static boolean isGoing(){
		return isGoing;
	}
  
	/**
	 * Get a list of all entities.
	 */
	public static List<Entity> getEntities(){
		return entities;
	}
  
	/**
	 * Return a list of players.
	 */
	public static List<Player> getPlayers(){
		List<Player> players = new ArrayList<>();
		for(Entity e : getEntities())
			if(e instanceof Player)
				players.add((Player)e);
		return players;
	}
  
	/**
	 * Return the game map.
	 */
	public static GameMap getMap(){
		return gameMap;
	}
}
