package me.nadd.tilegame.entities;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.KeyboardListener;
import me.nadd.tilegame.Sound;
import me.nadd.tilegame.controls.DefaultControls;
import me.nadd.tilegame.controls.PlayerControls;

/**
 * Player Entity
 * 
 * Created March 3, 2017
 * @author Kneesnap
 */
public class Player extends Entity {
	
        private static int x;
        private static int y;
	private PlayerControls controls = new DefaultControls();
        
        public static int getcX(){
            return x;
        }
        public static int getcY(){
            return y;
        }
	
	public Player(int x, int y) {
		super(x, y, null);
                this.x=x;
                this.y=y;
	}
        
        
	/**
	 * Get the control scheme for this player.
	 */
	public PlayerControls getControls(){
		return this.controls;
	}
	
	@Override
	public void onTick() {
		keyboardTick();
		super.onTick();
	}
	
	private void keyboardTick() {
		if(!Core.haveTicksPassed((KeyboardListener.isKeyDown(controls.getKeyPressShift())) ? 1 : 2))
			return;
		//  MOVE PLAYER  //
		if(KeyboardListener.isKeyDown(controls.getKeyUp()))
			moveY(-1);
                        y-=1;
		
		if(KeyboardListener.isKeyDown(controls.getKeyDown()))
			moveY(1);
                        y+=1;
		
		if(KeyboardListener.isKeyDown(controls.getKeyLeft()))
			moveX(-1);
                        x-=1;
		
		if(KeyboardListener.isKeyDown(controls.getKeyRight()))
			moveX(1);
                        x+=1;
	}
	
	public void kill() {
		super.kill();
                Core.playSound(Sound.DEATH);
                Core.stopMusic();
	}
}
