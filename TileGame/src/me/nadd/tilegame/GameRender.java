package me.nadd.tilegame;

import me.nadd.tilegame.gui.GUI;
import me.nadd.tilegame.gui.GUIMainMenu;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Handles all rendering.
 * 
 * Created March 3rd, 2017.
 * @author Kneesnap
 */
public class GameRender {
	
	private static long mainWindowId;
	private static GUI currentScreen;
	
	public static final int TICK_INTERVAL = 50;
	private static long lastTickTime;
	/**
	 * Creates the main game window.
	 */
	public static void createWindow() {
		System.out.println("Creating Window.");
		//Create main window, set context.
		GLFW.glfwInit();
		mainWindowId = GLFW.glfwCreateWindow(800, 600, "TileGame", 0, 0);
		GLFW.glfwSetKeyCallback(mainWindowId, new KeyboardListener());
		GLFW.glfwMakeContextCurrent(mainWindowId);
		GL.createCapabilities();
		startRendering();
	}
	
	/**
	 * Starts the render loop.
	 */
	public static void startRendering() {
		//Continues until the window is shut.
        while (!isClosed()) {
        	//Clears the frame buffer.
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            // Set 0, 0 to be the top left corner
            GUI.updateScreenDimensions();
    		glMatrixMode(GL_PROJECTION);
    		glLoadIdentity();
    		glOrtho(0, GUI.getWidth(), GUI.getHeight(), 0, 1, -1);
    		glMatrixMode(GL_MODELVIEW);
    		
            //Render our game.
            GL11.glPushMatrix();
            renderGame();
            GL11.glPopMatrix();
            
            glfwSwapBuffers(mainWindowId); // swap the color buffers
 
            //Check for window events, mouse clicks, key presses.
            glfwPollEvents();
            
            //Runs the game tick.
            long currentTime = System.currentTimeMillis();
            if(currentTime > lastTickTime - TICK_INTERVAL){
            	lastTickTime = currentTime;
            	Core.doGameTick();
            }
        }
	}
	
	/**
	 * Renders all objects on screen.
	 */
	public static void renderGame() {
		if(currentScreen != null)
			currentScreen.draw();
		else 
			openGUI(new GUIMainMenu());
	}
	
	/**
	 * Opens a new GUI.
	 */
	public static void openGUI(GUI gui){
		if(currentScreen != null)
			currentScreen.onClose();
		currentScreen = gui;
		currentScreen.initGUI();
	}
	
	/**
	 * Closes the current GUI.
	 */
	public static void closeGUI() {
		if(currentScreen != null)
			currentScreen.onClose();
		currentScreen = new GUIMainMenu();
	}
	
	/**
	 * Has the window been closed?
	 */
	public static boolean isClosed() {
		return GLFW.glfwWindowShouldClose(mainWindowId);
	}
	
	/**
	 * Get the main window id.
	 */
	public static long getWindow() {
		return mainWindowId;
	}
	
	/**
	 * Returns the open gui.
	 */
	public static GUI getCurrentScreen() {
		return currentScreen;
	}
}
