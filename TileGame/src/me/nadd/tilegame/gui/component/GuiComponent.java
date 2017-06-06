package me.nadd.tilegame.gui.component;

import me.nadd.tilegame.gui.GUI;

/**
 * Base GUI Component.
 * @author Kneesnap
 */
public abstract class GuiComponent {
	
	private int xPos;
	private int yPos;
	
	public GuiComponent(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/**
	 * Get the X position of the component.
	 */
	public int getX() {
		return this.xPos;
	}
	
	/**
	 * Get the Y position of the component.
	 */
	public int getY() {
		return this.yPos;
	}
	
	/**
	 * Move this component to the specified coordinates.
	 */
	public void move(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
        
        /**
         * Calls when this GUI is unloaded.
         */
        public void destroy() {
            
        }
	
	/**
	 * Render this gui component.
	 */
	public abstract void render(GUI gui);
	
	/**
	 * Calls when a mouse is clicked.
	 */
	public abstract void onMouseClick(GUI gui);
	
	protected boolean isMouseInRegion(int x1, int y1, int x2, int y2){
		double x = GUI.getMouseX();
		double y = GUI.getMouseY();
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}
}
