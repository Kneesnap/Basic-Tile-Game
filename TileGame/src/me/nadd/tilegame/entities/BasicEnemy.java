package me.nadd.tilegame.entities;

import me.nadd.tilegame.ai.BasicAI;
import me.nadd.tilegame.ai.ChargerAI;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import me.nadd.tilegame.ai.DumbAI;

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
                this.setAI(new ChargerAI(null, 15, 5));
            else
                this.setAI(new BasicAI(null, 4, true, 75));
        }
            
	//BLUE = ChargerAI RED = BasicAI.
        //Upon entry of third type Basic should be default, and third to 2.
	public ReadableColor getColor() {
                if (type == 1)
                    return Color.BLUE;
                else
                    return Color.RED;
	}
}
