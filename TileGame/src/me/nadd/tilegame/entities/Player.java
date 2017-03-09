package me.nadd.tilegame.entities;

import me.nadd.tilegame.controls.DefaultControls;
import me.nadd.tilegame.controls.PlayerControls;

/**
 * Player Entity
 * 
 * Created March 3, 2017
 * @author Kneesnap
 */
public class Player extends Entity {
	
	private PlayerControls controls = new DefaultControls();
	
	public Player(int x, int y) {
		super(x, y, null);
	}
	
	/**
	 * Get the control scheme for this player.
	 */
	public PlayerControls getControls(){
		return this.controls;
	}
	
	/**
	 * Handles a key press.
	 * @param keyPress
	 */
	public void handleKeyPress(int keyPress) {
		//TODO: Make this check if the key is pressed, every few ticks, instead of an event system, this should feel like a game, not like typing.
		if(keyPress == controls.getKeyUp())
			moveY(-1);
		
		if(keyPress == controls.getKeyDown())
			moveY(1);
		
		if(keyPress == controls.getKeyLeft())
			moveX(-1);
		
		if(keyPress == controls.getKeyRight())
			moveX(1);
	}
        public void kill() {
		super.kill();
                //Play DeathSound.wav
	}
	
}
