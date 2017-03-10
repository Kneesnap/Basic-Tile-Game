package me.nadd.tilegame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private static int MAP_SIZE_Y = 16;
  
	private static boolean isGoing;
	public static int tickCount;
	private static List<Entity> entities = new ArrayList<>();
	private static GameMap gameMap = new GameMap(MAP_SIZE_X, MAP_SIZE_Y);
	private static int level;
  
	public static void initGame(){
		System.out.println("TileGame - Starting...");
		GameRender.createWindow();
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
			System.out.println("STARTING GAME");
			isGoing = true;
			level = 0;
			nextLevel();
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
	
	/**
	 * Increments the level
	 */
	public static void nextLevel() {
		level++;
		System.out.println("STARTING LEVEL " + level);
		getEntities().clear();
		getEntities().add(new Player(0, getMap().getYSize() - 1));
		gameMap.generateMap();
		GameRender.openGUI(new GUIGame());
	}
	
	public static void doGameTick(){
		tickCount = tickCount % 500 + 1;
		//  ENTITY TICK  //
		List<Entity> ent = new ArrayList<Entity>();
		getEntities().forEach(ent::add);
		ent.forEach(Entity::onTick);
		
		if(getEntities().size() <= 1)
			stopGame();
	}
	
	/**
	 * Checks if enough ticks have passed.
	 */
	public static boolean haveTicksPassed(int ticks) {
		return ticks <= 0 || tickCount % ticks == 0;
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
	 * Returns all alive players
	 */
	public static List<Player> getLivePlayers() {
		return getPlayers().stream().filter(Player::isAlive).collect(Collectors.toList());
	}
    
	/**
	 * Gets the current level.
	 * @return
	 */
	public static int getLevel(){
		return level;
	}
  
	/**
	 * Return the game map.
	 */
	public static GameMap getMap(){
		return gameMap;
	}
}
