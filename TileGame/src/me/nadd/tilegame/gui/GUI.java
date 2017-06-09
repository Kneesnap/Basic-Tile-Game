package me.nadd.tilegame.gui;

import static org.lwjgl.opengl.GL11.*;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import me.nadd.tilegame.Drawable;

import me.nadd.tilegame.gui.component.GuiComponent;
import me.nadd.tilegame.GameRender;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.ReadableColor;

public abstract class GUI {
	
	private static int height;
	private static int width;
	private List<GuiComponent> components = new ArrayList<GuiComponent>();
	
	public static int getWidth(){
		return width;
	}
	
	public static int getHeight(){
		return height;
	}
	
	public static double getMouseX() {
		DoubleBuffer x = BufferUtils.createDoubleBuffer(4);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(4);
		GLFW.glfwGetCursorPos(GameRender.getWindow(), x, y);
		return x.get(0);
	}
	
	public static double getMouseY() {
		DoubleBuffer x = BufferUtils.createDoubleBuffer(4);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(4);
		GLFW.glfwGetCursorPos(GameRender.getWindow(), x, y);
		return y.get(0);
	}
	
	public static void updateScreenDimensions(){
		//  GET DIMENSIONS  //
		IntBuffer w = BufferUtils.createIntBuffer(4);
		IntBuffer h = BufferUtils.createIntBuffer(4);
		GLFW.glfwGetWindowSize(GameRender.getWindow(), w, h);
		//  UPDATE LOCAL VARIABLES  //
		width = w.get(0);
		height = h.get(0);
	}
	
	public void draw() {
		//  DRAW ALL GUI COMPONENTS  //
		for(GuiComponent gc : this.components){
			GL11.glPushMatrix();
			gc.render(this);
			GL11.glPopMatrix();
		}
                
                //  DRAW THIS GUI  //
                GL11.glPushMatrix();
		this.render();
                GL11.glPopMatrix();
	}
	
	/**
	 * Called when a GUI is initiated. (Either on creation or window resizing.)
	 */
	public abstract void initGUI();
	
	protected abstract void render();
	
	public abstract void onClose();
	
	public void setBackgroundColor(float r, float g, float b, float alpha){
		glClearColor(r, g, b, alpha);
	}
	
	public void setDrawColor(float r, float g, float b) {
		glColor3f(r, g, b);
	}
	
	public void drawTriangle(float x, float y, float midXDif, float midYDif, float endXDif, float endYDif) {
		//  CALCULATE WHERE TO DRAW LINES  //
		float midX = midXDif - x;
		float midY = midYDif - y;
		float endX = endXDif - x;
		float endY = endYDif - y;
		
		//  DRAW TRIANGLE  //
		drawLine(x, y, midX, midY);
		drawLine(x, y, endX, endY);
		drawLine(midX, midY, endX, endY);
	}
	
	public void drawRect(double x, double y, double x2, double y2){
		drawRect((float)x, (float)y, (float)x2, (float)y2); 
	}
	
	public void drawRect(float x, float y, float x2, float y2) {
		//Draw four lines that form a rectangle.
		drawLine(x, y, x2, y);
		drawLine(x, y2, x2, y2);
		drawLine(x, y, x, y2);
		drawLine(x2, y, x2, y2);
	}
	
	public void drawLine(float x, float y, float x2, float y2) { 
	    glBegin(GL_LINES);
	    glVertex2d(x, y);
	    glVertex2d(x2, y2);
	    glEnd();
	}
	
	/*public void drawString(double x, double y, String text){
		drawString((float)x, (float)y, text);
	}
	
	/**
	 * Draw a string at the given coordinates
	 */
	/*public void drawString(float x, float y, String text){
		GameRender.getFont().drawString(x, y, text);
	}*/

	/**
	 * Get all GUI components
	 */
	public List<GuiComponent> getComponents() {
		return this.components;
	}
	
	/**
	 * Add a GUI Component
	 */
	protected void addComponent(GuiComponent c) {
		this.components.add(c);
	}
        
        protected void draw(Drawable d) {
            glPushMatrix();
            d.render(this);
            glPopMatrix();
        }
        
        public void color(ReadableColor c) {
            GL11.glColor3f(c.getRed() / 255F, c.getGreen() / 255F, c.getBlue() / 255F);
        }
}
