package me.nadd.tilegame;

/**
 * A general utility class
 * @author Drew
 *
 */
public class Utils {
	
	public static int randInt(int min, int max) {
		return (int) ((Math.random() * (double)max) + min);
	}
}
