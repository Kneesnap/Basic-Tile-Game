package me.nadd.tilegame.gui;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.gui.component.GuiButton;
import me.nadd.tilegame.gui.component.Image;

public class GUIMainMenu extends GUI {
    
	@Override
	public void initGUI() {
                addComponent(new Image(30, 30, "C:\\Users\\voice\\Desktop\\lwjgl32.png"));
		addComponent(new GuiButton(getWidth() / 2 - 25, 30, "Play", 50, Core::startGame));
                
                Core.stopMusic();
	}
	
        @Override
	protected void render() {
		this.setBackgroundColor(0, 0, 0, 0);
		this.setDrawColor(1, 1, 0);
		drawRect(getMouseX() - 10, getMouseY() - 10, getMouseX() + 10, getMouseY() + 10);
	}

	@Override
	public void onClose() {
		
	}
}
