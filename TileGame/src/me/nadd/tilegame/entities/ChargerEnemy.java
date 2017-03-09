/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nadd.tilegame.entities;

import me.nadd.tilegame.ai.ChargerAI;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

/**
 *
 * @author Nathan
 */
public class ChargerEnemy extends BasicEnemy {
    
    public ChargerEnemy(int x, int y, int randTime, int sprintTime, int waitTime){
        super(x, y);
        this.setAI(new ChargerAI(null, randTime, sprintTime, waitTime));
    }
    
    public ReadableColor getColor() {
        return Color.BLUE;
    }
}
