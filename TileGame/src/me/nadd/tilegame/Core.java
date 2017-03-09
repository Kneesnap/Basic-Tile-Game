package me.nadd.tilegame;

import java.io.IOException;
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
        private static int level = 1;
  
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
                        System.out.println("STARTING GAME: LEVEL " + level);
			isGoing = true;
			getEntities().clear();
			getEntities().add(new Player(0, MAP_SIZE_Z-1));
			gameMap.generateMap(level);
			GameRender.openGUI(new GUIGame());
		}
	}
  
	/**
	 * Stops the game. Fails silently if there is no current game.
	 */
	public static void stopGame() {
		if(isGoing){
			GameRender.openGUI(new GUIMainMenu());
                        level = 1;
			isGoing = false;
		}
	}
	
	public static void doGameTick(){
		//  ENTITY TICK  //
		List<Entity> ent = new ArrayList<Entity>();
		entities.forEach(ent::add);
		ent.forEach(Entity::onTick);
                for (Player p : getPlayers()){
                    if (p.getX() == MAP_SIZE_X-1 && p.getY() == 0){
                        level++;
                        isGoing = false;
                        startGame();
                    }
                }
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
        
        public static List<Player> getLivePlayers(){
            List<Player> players = new ArrayList<>();
            for(Entity e : getEntities())
                if(e instanceof Player && e.isAlive())
                    players.add((Player)e);
            return players;
        }
        
        public static Entity getNearestEntity(Entity e){
            int minX = 0;
            int minY = 0;
            Entity minDistEntity;
            
            try {
                entities.get(1);
            } catch (Exception ex) {
                stopGame();
                return e;
            }
            
            if (e != entities.get(0)){
                minX = entities.get(0).getX() - e.getX();
                minY = entities.get(0).getY() - e.getY();
                minDistEntity = entities.get(0);
            } else {
                minX = entities.get(1).getX() - e.getX();
                minY = entities.get(1).getY() - e.getY();
                minDistEntity = entities.get(1);
            }
            double minDist = Math.pow((minX*minX + minY*minY), 0.5);

            for (Entity b : getEntities()){
                if (e == b)
                    continue;
                int distX = b.getX() - e.getX();
                int distY = b.getY() - e.getY();
                double Dist = Math.pow((distX*distX + distY*distY), 0.5);
                if (Dist < minDist){
                    minX = distX;
                    minY = distY;
                    minDist = Dist;
                    minDistEntity = b;
                }
            }
            
            return minDistEntity;
        }
        public static int getLevel(){
            return level;
        }
        public static int getMapSizeX(){
            return MAP_SIZE_X;
        }
        public static int getMapSizeZ(){
            return MAP_SIZE_Z;
        }
  
	/**
	 * Return the game map.
	 */
	public static GameMap getMap(){
		return gameMap;
	}
}
