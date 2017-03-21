package me.nadd.tilegame.ai;

import me.nadd.tilegame.Utils;

/**
 * A basic AI that charges up and moves in bursts.
 */
public class ChargerAI extends BasicAI {
    //The amount of ticks it takes to fully charge.
    private int chargeTime;
    //The amount of ticks a "burst" or sprint lasts for.
    private int sprintTime;
    //The counter that decides whether the entity is sprinting or charging.
    private int current;
    
    //The amount of ticks it takes to update while charging.
    private final int CHARGE_DELAY = 8;
    
    public ChargerAI(int chargeTime, int sprintTime) {
        this.chargeTime = (chargeTime / CHARGE_DELAY);
        this.sprintTime = sprintTime + this.chargeTime;
    }
    
    @Override
    public void update() {
    	
        //  DEFAULT TICK DELAY  //
        this.setTickDelay(1);
        
    	if (current <= chargeTime) {
    		//  WHILE CHARGING  //
            this.setTickDelay(CHARGE_DELAY);
    		super.randMove();
    	} else if (current <= sprintTime) {
            //  WHILE SPRINTING  //
    		super.smartMove();
    	} else {
            //This resets the counter. It's set to -1 because it gets incremented after this.
    		current = -1;
    	}
        
    	current++;
    	
        //  ATTACK  //
    	this.defaultAttack();
    }
}
