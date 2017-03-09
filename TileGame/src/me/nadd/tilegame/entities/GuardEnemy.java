
package me.nadd.tilegame.entities;

import me.nadd.tilegame.ai.GuardAI;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

/**
 *
 * @author Nathan
 */
public class GuardEnemy extends BasicEnemy {
    
    public GuardEnemy(int x, int y, int waitTime){
        super(x, y);
        this.setAI(new GuardAI(null, waitTime));
    }
    public ReadableColor getColor() {
        return Color.PURPLE;
    }
    
}
