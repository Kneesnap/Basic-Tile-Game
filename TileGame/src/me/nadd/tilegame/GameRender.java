package me.nadd.tilegame;

import com.sun.javafx.application.PlatformImpl;
import me.nadd.tilegame.gui.GUI;
import me.nadd.tilegame.gui.GUIMainMenu;
import static me.nadd.tilegame.tiles.Tile.getScaledTileSize;

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
	
	private static int fps;
	private static int tps;
	
	private static int tempFps;
	private static int tempTps;
	private static long resetTime;
	
	public static final int TICKS_PER_SECOND = 20;
	private static long lastTickTime;
	/**
	 * Creates the main game window.
	 */
	public static void createWindow() {
		System.out.println("Creating Window.");
		//Create main window, set context.
		GLFW.glfwInit();
		//  CREATE WINDOW  //
		mainWindowId = GLFW.glfwCreateWindow(600, 600, "TileGame", 0, 0);
		GLFW.glfwSetKeyCallback(mainWindowId, new KeyboardListener());
		
		//  SETUP CONTEXT  //
		GLFW.glfwMakeContextCurrent(mainWindowId);
		GL.createCapabilities();
		
		// Disable vSYnc. Some computers have it enabled by default, and it causes lots of problems.
		glfwSwapInterval(0);
		startRendering();
	}
        
        private static void windowSizeChanged(long window, int width, int height) {
            GUI.updateScreenDimensions();
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, GUI.getWidth(), GUI.getHeight(), 0, -1, 1);
            glMatrixMode(GL_MODELVIEW);
        }
        
        private static void framebufferSizeChanged(long window, int width, int height) {
            glViewport(0, 0, width, height);
        }
        
	private static void startRendering() {
		//Continues until the window is shut.
                
            GLFW.glfwSetWindowSizeCallback(mainWindowId, GameRender::windowSizeChanged);
            GLFW.glfwSetFramebufferSizeCallback(mainWindowId, GameRender::framebufferSizeChanged);
            windowSizeChanged(mainWindowId, GUI.getWidth(), GUI.getHeight());
            
        while (!isClosed()) {
        	//Clears the frame buffer.
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            // Set 0, 0 to be the top left corner
            
    		
            //Render game.
            GL11.glPushMatrix();
            renderGame();
            GL11.glPopMatrix();
            
            glfwSwapBuffers(mainWindowId); // Updates the screen.
 
            //Check for window events, mouse clicks, key presses.
            glfwPollEvents();
            
            tickFPS();
            //Runs the game tick.
            long currentTime = System.currentTimeMillis();
            if(currentTime > lastTickTime + (1000 / TICKS_PER_SECOND)){
            	lastTickTime = currentTime;
            	Core.doGameTick();
            	tempTps++;
            }
            
        }
        PlatformImpl.exit();
	}
	
	private static void tickFPS() {
		tempFps++;
		if(resetTime < System.currentTimeMillis()) {
			//  RESET FPS + TPS TIMERS  //
			fps = tempFps;
			tps = tempTps;
			tempFps = 0;
			tempTps = 0;
			resetTime = System.currentTimeMillis() + 1000;
			System.out.println("FPS = " + fps);
			System.out.println("TPS = " + tps);
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
	public static void openGUI(GUI gui) {
		// Close the current gui, if any.
		if(currentScreen != null)
			currentScreen.onClose();
		currentScreen = gui;
		// Setup the new GUI.
		currentScreen.initGUI();
	}
	
	/**
	 * Closes the current GUI.
	 */
	public static void closeGUI() {
		openGUI(new GUIMainMenu());
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
