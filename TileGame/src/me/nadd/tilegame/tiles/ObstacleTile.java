/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nadd.tilegame.tiles;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.gui.Texture;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

/**
 *
 * @author voice
 */
public class ObstacleTile extends Tile {
    
    public ObstacleTile(int x, int y) throws MalformedURLException, IOException {
        super(x, y);
    }
    public boolean isWalkable(Entity ent){
        return false;
    }
    public ReadableColor getColor() {
        return Color.LTGREY;
    }
}
