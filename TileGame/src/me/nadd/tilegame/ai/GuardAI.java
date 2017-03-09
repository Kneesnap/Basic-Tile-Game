/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nadd.tilegame.ai;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.entities.Entity;

/**
 *
 * @author voice
 */
public class GuardAI extends AI {
    private Entity target;
    private int yAmountDown=0;
    private int xAmountRight=0;
    private int yAmountUp=0;
    private int xAmountLeft=0;
    private int waitTime;
    private int WAIT_TIME;
    
    
    public GuardAI(Entity target, int waitTime){
        this.target = target;
        this.waitTime = waitTime;
        WAIT_TIME = waitTime;
        if (target == null && !Core.getPlayers().isEmpty()){
            this.target = Core.getPlayers().get(0);
        }
    }
    
    public void setTarget(Entity target){
        this.target = target;
    }
        
    public void update(){
        if (yAmountDown<=0&&xAmountRight<=0&&yAmountUp<=0&&xAmountLeft<=0){
            yAmountDown = (int) ((Math.random() * (Core.getMapSizeZ()/2)));
            xAmountLeft = (int) ((Math.random() * (Core.getMapSizeZ()/2)));
            yAmountUp = 1;
            xAmountRight = 1;
            
            //(int) ((Math.random() * (Core.getMapSizeZ()/2)) +(Core.getMapSizeZ()/2));
        }
        waitTime--;
        
        if (waitTime <= 0){
            //System.out.println(zAmountDown + " " + xAmountLeft + " " + zAmountUp + " " + xAmountRight);
            if (yAmountDown > 0){
                getEntity().moveY(1);
                yAmountDown--;
            }else if (xAmountRight > 0){
                xAmountRight = Core.getMapSizeX()-2-getEntity().getX();
                getEntity().moveX(1);
            }else if (xAmountLeft > 0){
                getEntity().moveX(-1);
                xAmountLeft--;
            }else if (yAmountUp > 0){
                yAmountUp = getEntity().getY()-1;
                getEntity().moveY(-1);
            }
            waitTime = WAIT_TIME;
        }
        
        if (Core.getLivePlayers().isEmpty()){
            this.setTarget(Core.getNearestEntity(this.getEntity()));
        }
        if (!Core.getLivePlayers().isEmpty())
                attackNearestPlayer();
            else
                attackNearest();
    }
}
