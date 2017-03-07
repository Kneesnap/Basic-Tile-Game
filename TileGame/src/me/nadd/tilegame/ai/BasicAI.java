package me.nadd.tilegame.ai;

import me.nadd.tilegame.entities.Entity;

public class BasicAI extends AI{
    private Entity target;
    private int agressionLevel;
    private boolean randMovement;
    
    public BasicAI(Entity entity, Entity target){
        super(entity);
        this.target = target;
        this.agressionLevel = 10;
        this.randMovement = false;
    }
    
    public BasicAI(Entity entity, Entity target,
            int agressionLevel, boolean randMovement){
        super(entity);
        this.target = target;
        this.agressionLevel = agressionLevel;
        this.randMovement = randMovement;
    }
    
    public void dumbMove(){
        if (ifMove()){
            int xDif = (target.getX() - getEntity().getX());
            int yDif = (target.getY() - getEntity().getY());
            if (Math.abs(xDif) > Math.abs(yDif))
                getEntity().moveX(xDif / Math.abs(xDif));
            else
                getEntity().moveY(yDif / Math.abs(yDif));
        }
        else if (randMovement){
            randMove();
        }    
    }
    
    public boolean ifMove(){
        int decision = (int) Math.random() * 10 + 1;
        return (decision < agressionLevel);
    }
    
    public void randMove(){
        int xOrY = (int) Math.random();
        int moveBy = (int) Math.random();
        moveBy = moveBy == 1 ? 1 : -1;
        if (xOrY == 1)
            getEntity().moveX(moveBy);
        else
            getEntity().moveY(moveBy);
    }
    
    public void setTarget(Entity target){
        this.target = target;
    }
    public void setAgression(int agressionLevel){
        this.agressionLevel = agressionLevel;
    }
    public void setIfRandMove(boolean randMovement){
        this.randMovement = randMovement;
    }
    
    public Entity getTarget(){
        return target;
    }
    public int getAgression(){
        return agressionLevel;
    }
    public boolean getIfRandMove(){
        return randMovement;
    }
    
    @Override
    public void update() {
        dumbMove();
        attackNearestPlayer();
    }
    
    
}
