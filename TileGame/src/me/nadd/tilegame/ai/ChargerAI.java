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
public class ChargerAI extends BasicAI{
    private Entity target;
    private int randTime;
    private final int RAND_TIME;
    private int sprintTime;
    private final int SPRINT_TIME;
    private int current = 0 + (int) (Math.random() * 10);
    private int max = 0;
    private int waitTime = 0;
    
    public ChargerAI(Entity target, int randTime, int sprintTime){
        this.target = target;
        if (target == null && !Core.getPlayers().isEmpty()){
            this.target = Core.getPlayers().get(0);
        }
        this.randTime = randTime;
        this.RAND_TIME = randTime;
        this.sprintTime = sprintTime + randTime;
        this.SPRINT_TIME = sprintTime + randTime;
        max = sprintTime + randTime + 1;
    }
    
    public void dumbMove(){
            if (current <= randTime){
                waitTime = 125;
                super.randMove();
                current++;
            } else if (current <= sprintTime) {
                waitTime = 35;
                super.smartMove();
                current++;
            } else {
                current = 0;
            }
    }
    
    
    @Override
    public void update() {
        waitTime--;
        if (waitTime  <= 0){
            dumbMove();
            if (!Core.getLivePlayers().isEmpty())
                attackNearestPlayer();
            else
                attackNearest();
        }
        if (Core.getLivePlayers().isEmpty()){
            this.setTarget(Core.getNearestEntity(this.getEntity()));
        }
    }
}
