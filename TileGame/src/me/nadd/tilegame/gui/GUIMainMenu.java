package me.nadd.tilegame.gui;


import me.nadd.tilegame.GameRender;
import me.nadd.tilegame.gui.component.GuiButton;

public class GUIMainMenu extends GUI {
	
	@Override
	public void initGUI() {
		addComponent(new GuiButton(getWidth() / 2 - 25, 30, "Play", 50, () -> GameRender.openGUI(new GUIGame())));
	}
	
	protected void render() {
		this.setBackgroundColor(1, 0, 1, 0);
		this.setDrawColor(1, 1, 0);
		drawRect(getMouseX() - 10, getMouseY() - 10, getMouseX() + 10, getMouseY() + 10);
	}

	@Override
	public void onClose() {
		
	}
}
