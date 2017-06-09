package me.nadd.tilegame.gui;

import org.lwjgl.opengl.GL11;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.Drawable;
import me.nadd.tilegame.Sound;
import me.nadd.tilegame.gui.component.Image;
import me.nadd.tilegame.gui.component.Images;

public class GUIGame extends GUI {

	@Override
	protected void render() {
		this.setBackgroundColor(0, 0, 0, 0);
		
                //  DRAW MAP  //
		Core.getMap().getAllTiles().forEach(this::draw);
                //  DRAW ENTITIES  //
		Core.getEntities().forEach(this::draw);
		
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
