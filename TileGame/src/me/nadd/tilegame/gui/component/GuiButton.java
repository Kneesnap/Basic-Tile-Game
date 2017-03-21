package me.nadd.tilegame.gui.component;

import me.nadd.tilegame.gui.GUI;


/**
 * A basic Button.
 * @author Kneesnap
 */
public class GuiButton extends GuiComponent {
	protected final static int BOX_HEIGHT = 20;
	
	private int width;
	private String text;
	private Runnable onClick;
	
	public GuiButton(int xPos, int yPos, String text, int width, Runnable onClick) {
		super(xPos, yPos);
		setText(text);
		setWidth(width);
		this.onClick = onClick;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String str) {
		this.text = str;
	}
	
	@Override
	public void render(GUI gui) {
		if(isHover())
			gui.setDrawColor(0, 1, 0);
		gui.drawRect(getX(), getY(), getX() + getWidth(), getY() + BOX_HEIGHT);
	}
	
	@Override
	public void onMouseClick(GUI gui) {
		if(isHover() && this.onClick != null)
			this.onClick.run();
	}
	
	public boolean isHover() {
		return this.isMouseInRegion(getX(), getY(), getX() + getWidth(), getY() + BOX_HEIGHT);
	}
}
