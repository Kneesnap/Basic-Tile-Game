package me.nadd.tilegame.entities;

import me.nadd.tilegame.ai.BasicAI;
import me.nadd.tilegame.ai.ChargerAI;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import me.nadd.tilegame.ai.DumbAI;
import me.nadd.tilegame.ai.GuardAI;

public class BasicEnemy extends Entity {
        private int type = 0;
        
	public BasicEnemy(int x, int y) {
		super(x, y);
		this.setAI(new BasicAI(null, 4, true, 75));
	}
        public BasicEnemy(int x, int y, int type){
            super(x, y);
            this.type = type;
            if (type == 1)
                this.setAI(new ChargerAI(null, 25, 5));
            else if (type == 3)
                this.setAI(new GuardAI(null, 750));
            else
                this.setAI(new BasicAI(null, 4, true, 300));
        }
            
	//BLUE = ChargerAI RED = BasicAI.
        //Upon entry of third type Basic should be default, and third to 2.
	public ReadableColor getColor() {
                if (type == 1)
                    return Color.BLUE;
                else if (type == 3)
                    return Color.PURPLE;
                else
                    return Color.RED;
	}
}
