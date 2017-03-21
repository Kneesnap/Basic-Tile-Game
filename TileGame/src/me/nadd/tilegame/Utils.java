package me.nadd.tilegame;

/**
 * A general utility class
 * @author Kneesnap
 */
public class Utils {
	
	public static int randInt(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
        public static boolean randChoice(){
            return (Math.random() < 0.5) ? true : false;
        }
}
