package me.nadd.tilegame.gui.component;

import me.nadd.tilegame.gui.GUI;

/**
 * A basic checkbox.
 */
public class GuiCheckBox extends GUIButton {
	
	private boolean state;
	
	public GuiCheckBox(int xPos, int yPos) {
		super(xPos, yPos, "", BOX_HEIGHT, this::toggle);
	}

	@Override
	public void render(GUI gui) {
		if(isChecked())
			gui.setDrawColor(0, 1, 0);
		else
			gui.setDrawColor(1, 0, 0);
		gui.drawRect(getX(), getY(), getX() + getWidth(), getY() + BOX_HEIGHT);
	}
	
	/**
	 * Toggle the status of this checkbox.
	 */
	public void toggle() {
		this.state = !this.state;
	}
	
	/**
	 * Is this checkbox checked?
	 */
	public boolean isChecked() {
		return this.state;
	}
}
