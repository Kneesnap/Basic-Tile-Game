package me.nadd.tilegame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.javafx.application.PlatformImpl;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

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
        
    	private static final int MAP_SIZE_X = 16;
	private static final int MAP_SIZE_Y = 16;
  
	private static boolean isGoing;
	private static int tickCount;
	private static List<Entity> entities = new ArrayList<>();
	private static GameMap gameMap = new GameMap(MAP_SIZE_X, MAP_SIZE_Y);
	private static int level;
        
        private static MediaPlayer currentPlayer;
        private static Sound currentMusic;
  
	public static void initGame(){
		System.out.println("TileGame - Starting...");
		//Init JavaFX (Media Player)
		PlatformImpl.startup(() -> {});
		//  CREATE WINDOW  //
		GameRender.createWindow();
	}
  
	/**
	 * Called to shutdown the program.
	 */
	public static void closeGame(){
		System.out.println("TileGame - Stopping...");
		//Stops the game properly.
		stopGame();
		//Exit JavaFX. Otherwise the window hangs.
                PlatformImpl.exit();
		//Closes this process.
		System.exit(0);
	}
  
	/**
	 * Starts the game. Fails silently if a game is already taking place.
	 */
	public static void startGame(){
		if (!isGoing) {
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
		if (isGoing) {
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
		//  RESET ENTITIES  //
		getEntities().clear();
		getEntities().add(new Player(0, getMap().getYSize() - 1));
		
		//  GENERATE MAP  //
		gameMap.generateMap();
		
		//  START GAME  //
		GameRender.openGUI(new GUIGame());
                if (level != 1)
                    Core.playSound(Sound.NEXT_LEVEL);
	}
	
        /**
         * Ticks the game. Normally 20 TPS.
         */
	public static void doGameTick(){
		tickCount = tickCount % 500 + 1;
                //  MAP TICK  //
                
                
		//  ENTITY TICK  //
		List<Entity> ent = new ArrayList<Entity>();
		getEntities().forEach(ent::add);
		ent.forEach(Entity::onTick);
		
		//If there's only one entity left, stop the game.
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
         * @return GameMap
	 */
	public static GameMap getMap(){
		return gameMap;
	}
        
        /**
         * Play a sound effect.
         * @param sound
         */
        public static void playSound(Sound sound) {
            //Silently fail if the file isn't found.
            if (sound == null || sound.getAudio() == null)
                return;
            
            MediaPlayer player = new MediaPlayer(sound.getAudio());
            player.play();
        }
        
        /**
         * Play that funky music white-boy.
         * @param music
         */
        public static void playMusic(Sound music) {
            //If the file isn't found silently fail.
            if(music == null || music.getAudio() == null)
                return;
            
            //Stop the current music
            if (currentPlayer != null) {
                // Don't restart the current music.
                if (currentMusic == music)
                    return;
                stopMusic();
            }
            
            //Play the new music.
            currentMusic = music;
            currentPlayer = new MediaPlayer(music.getAudio());
            //Put music on repeat.
            currentPlayer.setOnEndOfMedia(
                    () -> currentPlayer.seek(Duration.ZERO));
            currentPlayer.play();
        }
        
        
        /**
         * Stop that music!
         */
        public static void stopMusic() {
            if(currentPlayer == null)
                return;
            currentPlayer.stop();
            currentPlayer.dispose();
            currentPlayer = null;
        }
}
