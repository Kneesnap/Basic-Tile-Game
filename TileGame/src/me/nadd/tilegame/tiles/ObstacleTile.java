package me.nadd.tilegame.tiles;

import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.gui.component.Images;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

/**
 * A basic obstacle.
 */
public class ObstacleTile extends Tile {
    
    public ObstacleTile(int x, int y){
        super(x, y, Images.WALL);
    }
    
    public boolean isWalkable(Entity ent){
        return false;
    }
    
    public ReadableColor getColor() {
        return Color.LTGREY;
    }
}
