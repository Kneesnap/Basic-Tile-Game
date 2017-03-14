package me.nadd.tilegame;

/**
 * A general utility class
 * @author Drew
 *
 */
public class Utils {
	
	public static int randInt(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
        public static int randChoice(){
            return (Math.random() < 0.5) ? 1 : 0;
        }
}
