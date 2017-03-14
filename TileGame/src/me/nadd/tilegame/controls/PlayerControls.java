package me.nadd.tilegame.controls;

/**
 * Player Controls
 * 
 * Created March 3, 2017.
 * @author Kneesnap
 */
public class PlayerControls {
	
	private int keyMoveUp;
	private int keyMoveDown;
	private int keyMoveLeft;
	private int keyMoveRight;
        private int keyPressShift;
	
	public PlayerControls(){
		
	}
	
	public PlayerControls(int up, int down, int left, int right, int shift){
		this.setKeyUp(up);
		this.setKeyDown(down);
		this.setKeyLeft(left);
		this.setKeyRight(right);
                this.setKeyPressShift(shift);
	}
	
	/**
	 * Get the "Move up" key.
	 */
	public int getKeyUp() {
		return this.keyMoveUp;
	}
	
	/**
	 * Sets the "Move Up" key.
	 */
	public void setKeyUp(int key) {
		this.keyMoveUp = key;
	}
	
	/**
	 * Get the "Move Down" key.
	 */
	public int getKeyDown() {
		return this.keyMoveDown;
	}
	
	/**
	 * Sets the "Move Down" key.
	 */
	public void setKeyDown(int key) {
		this.keyMoveDown = key;
	}
	
	/**
	 * Get the "Move Left" key.
	 */
	public int getKeyLeft() {
		return this.keyMoveLeft;
	}
	
	/**
	 * Sets the "Move Left" key.
	 */
	public void setKeyLeft(int key) {
		this.keyMoveLeft = key;
	}
	
	/**
	 * Get the "Move Right" key.
	 * @return
	 */
	public int getKeyRight() {
		return this.keyMoveRight;
	}
	
	/**
	 * Sets the "Move Right" key.
	 */
	public void setKeyRight(int key) {
		this.keyMoveRight = key;
	}
        
        public int getKeyPressShift(){
            return this.keyPressShift;
        }
        /**
         * Sets the "Sprint" key.
         */
        public void setKeyPressShift(int shift){
            this.keyPressShift = shift;
        }
}
