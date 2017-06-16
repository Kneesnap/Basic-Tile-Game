package me.nadd.tilegame.gui;

import java.util.List;
import org.lwjgl.opengl.GL11;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.Drawable;
import me.nadd.tilegame.Sound;
import me.nadd.tilegame.entities.Entity;
import me.nadd.tilegame.entities.Player;
import me.nadd.tilegame.gui.component.Image;
import me.nadd.tilegame.gui.component.Images;
import me.nadd.tilegame.tiles.Tile;

public class GUIGame extends GUI {

	@Override
	protected void render() {
		this.setBackgroundColor(0, 0, 0, 0);
		
                //  DRAW MAP  //
		Core.getMap().getAllTilesNearPlayer().forEach(this::draw);
                /*for (Tile[] arr1 : Core.getMap().getTiles()) {
                    for (Tile t : arr1) {
                        for (Player p : Core.getPlayers()) {
                            if (p.distanceTo(t) < 8) {
                                draw(t);
                                break;
                            }
                        }
                    }
                }*/
                    
                
                //  DRAW ENTITIES  //
		Core.getEntities().forEach(this::draw);
                /*for (Entity e : Core.getEntities())
                    for (Player p : Core.getPlayers()){
                        if (p.distanceTo(e) < 8)
                            draw(e);
                            break;
                    }*/
		}
        
	@Override
	public void onClose() {
		
	}

	@Override
	public void initGUI() {
                int level = Core.getLevel();
                
                //On horde levels plays a certain piece.
                if (level == 5 || level == 7)
                    Core.playMusic(Sound.HORDE_LEVEL_MUSIC);
                //On levels higer than five plays a certain piece.
                else if (level > 5)
                    Core.playMusic(Sound.HIGHER_LEVEL_MUSIC);
                //Otherwise play the default music.
                else
                    Core.playMusic(Sound.DEFAULT_GAME_MUSIC);
	}
}
