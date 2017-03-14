package me.nadd.tilegame.controls;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Default "Player 1" controls.
 * Standard WASD input.
 * 
 * Created March 3rd, 2017.
 * @author Kneesnap
 */
public class DefaultControls extends PlayerControls {
	public DefaultControls() {
		super(GLFW_KEY_UP, GLFW_KEY_DOWN, GLFW_KEY_LEFT, GLFW_KEY_RIGHT, GLFW_KEY_LEFT_SHIFT);
	}
}
