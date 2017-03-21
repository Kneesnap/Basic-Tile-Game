package me.nadd.tilegame.entities;

import me.nadd.tilegame.ai.ChargerAI;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

/**
 * A charger Enemy that moves randomly then charges quickly at the player
 * 
 * @author Nathan
 */
public class ChargerEnemy extends Entity {
    
    /**
     * Basic Constructor for Charger Enemy
     * @param x sets x position 
     * @param y sets y position
     * @param randTime time spent moving randomly
     * @param sprintTime time spent charging at player
     * @param waitTime time spent waiting between actions
     */
    public ChargerEnemy(int x, int y) {
        super(x, y);
        this.setAI(new ChargerAI(120, 5));
    }
    
    public ReadableColor getColor() {
        return Color.BLUE;
    }
}
