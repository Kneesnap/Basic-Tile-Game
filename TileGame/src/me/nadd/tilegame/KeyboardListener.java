package me.nadd.tilegame;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import me.nadd.tilegame.controls.ActionControls;
import me.nadd.tilegame.gui.component.GuiComponent;

public class KeyboardListener extends GLFWKeyCallback {

	private static boolean[] keys = new boolean[65536];
	
	private List<KeyListener> listeners = new ArrayList<>();
	
	public KeyboardListener() {
		
		//  BIND SPECIAL KEYS  //
		add(new KeyListener(ActionControls.STOP_KEY, event -> Core.closeGame()));
		add(new KeyListener(GLFW.GLFW_KEY_SPACE, event -> Core.stopGame()));
		
		//  SETUP MOUSE LISTENER  //
		GLFW.glfwSetMouseButtonCallback(GameRender.getWindow(), GLFWMouseButtonCallback.create((window, button, action, mods) -> {
		    if(GameRender.getCurrentScreen() != null && action == GLFW.GLFW_PRESS && button == 0)
		    	for(GuiComponent gc : GameRender.getCurrentScreen().getComponents())
		    		gc.onMouseClick(GameRender.getCurrentScreen());
		}));
	}
	
	private void add(KeyListener kl) {
		this.listeners.add(kl);
	}
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods){
                //Some genius decided to make physical volume keys negative.
                if(key < 0)
                    return;
		boolean pressed = action != GLFW.GLFW_RELEASE;
		
		//  RECORD IF PRESSED  //
		keys[key] = pressed;
		KeyEvent event = new KeyEvent(key, action, window);
		
		//  FIRE THE EVENT  //
		this.listeners.stream().filter(l -> l.getKeyCode() == event.getKeyCode()).forEach((listener) -> listener.onPress(event));
	}
	
	public static boolean isKeyDown(int keycode) {
		return keys[keycode];
	}
	
	/**
	 * A reaction to a singular keypress.
	 * 
	 * Created March 3rd, 2017.
	 * @author Kneesnap
	 */
	public class KeyListener {
		
		private int keyCode;
		private Consumer<KeyEvent> callback;
		
		public KeyListener(int keyCode, Consumer<KeyEvent> callback) {
			this.keyCode = keyCode;
			this.callback = callback;
		}
		
		public int getKeyCode() {
			return this.keyCode;
		}
		
		public void onPress(KeyEvent event) {
			this.callback.accept(event);
		}
	}
	
	/**
	 * An event that gets fired on a key press.
	 * 
	 * Created March 3rd, 2017.
	 * @author Kneesnap
	 */
	public class KeyEvent {
		
		private int keyCode;
		private int action;
		private long windowId;
		
		public KeyEvent(int keyCode, int action, long windowId) {
			this.keyCode = keyCode;
			this.action = action;
			this.windowId = windowId;
		}
		
		public int getKeyCode() {
			return this.keyCode;
		}
		
		public int getAction() {
			return this.action;
		}
		
		public long getWindowId() {
			return this.windowId;
		}
	}
}
