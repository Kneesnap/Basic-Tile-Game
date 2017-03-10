
package me.nadd.tilegame.entities;

/**
 * a basic Ranged Enemy that extends BasicEnemy
 * 
 * @author Nathan
 */
public class RangedEnemy extends BasicEnemy {
    
    private byte attackDirection; // 0 is horizontal, 1 is vertival 
    
    public RangedEnemy(int x, int y){
        super(x, y);
    }
    
    /**
     * Can this entity attack another entity?
     */
    public boolean canAttack(Entity ent){
        if (this.getX() == ent.getX()){
            attackDirection = 0;
            return true;
        } else if (this.getY() == ent.getY()){
            attackDirection = 1;
            return true;
        }
        return false;
    }
    
    /**
     * Attack another entity. Silently fails if canAttack returns false.
     */
    public void attackEntity(Entity ent) {
        if(canAttack(ent)){
            if (this.attackDirection == 0){
                // code to create a horizontal ragned attack 
            }else if (this.attackDirection == 1){
                // code to create a vertical ranged attack
            }
        }
    }
}
