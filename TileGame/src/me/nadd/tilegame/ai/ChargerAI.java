package me.nadd.tilegame.ai;

import me.nadd.tilegame.Utils;

/**
 * A basic AI that charges up and moves in bursts.
 */
public class ChargerAI extends BasicAI {
    private int randTime;
    private int sprintTime;
    private int waitTime;
    private int current = Utils.randInt(0, 10);
    
    public ChargerAI(int randTime, int sprintTime, int waitTime){
        this.randTime = randTime;
        this.sprintTime = sprintTime + randTime;
        this.waitTime = waitTime;
    }
    
    @Override
    public void update() {
    	
    	this.setTickDelay(waitTime);
    	if (current <= randTime){
    		this.setTickDelay(waitTime * 4);
    		super.randMove();
    	} else if (current <= sprintTime) {
    		this.setTickDelay(1);
    		super.smartMove();
    	} else {
    		current = -1;
    	}
    	current++;
    	
    	this.defaultAttack();
    }
}
