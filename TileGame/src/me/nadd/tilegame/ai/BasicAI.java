package me.nadd.tilegame.ai;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.entities.Entity;

public class BasicAI extends AI{
    private Entity target;
    private int agressionLevel;
    private boolean randMovement;
    private int wait = 50;
    
    public BasicAI(Entity entity){
        this(entity, null);
    }
    
    public BasicAI(Entity entity, Entity target){
        this(entity, target, 10, false);
    }
    
    public BasicAI(Entity entity, Entity target,
            int agressionLevel, boolean randMovement){
        super(entity);
        this.target = target;
        if (target == null && !Core.getPlayers().isEmpty()){
            this.target = Core.getPlayers().get(0);
        }
        this.agressionLevel = agressionLevel;
        this.randMovement = randMovement;
    }
    
    public void dumbMove(){
        if (ifMove()){
            int xDif = (target.getX() - getEntity().getX());
            int yDif = (target.getY() - getEntity().getY());
            if (Math.abs(xDif) > Math.abs(yDif) && xDif != 0)
                getEntity().moveX(xDif / Math.abs(xDif));
            else if (yDif != 0)
                getEntity().moveY(yDif / Math.abs(yDif));
        }
        else if (randMovement){
            randMove();
        }    
    }
    
    public boolean ifMove(){
        double decision = Math.random() * 10 + 1;
        return (decision < agressionLevel);
    }
    
    public void randMove(){
        double xOrY = Math.random();
        double moveWhere = Math.random();
        int moveBy = moveWhere < 0.5 ? 1 : -1;
        if (xOrY < 0.5)
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
        wait--;
        if (wait  <= 0){
            dumbMove();
            attackNearestPlayer();
            wait = 50;
        }
        
    }
    
    
}
