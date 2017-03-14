package me.nadd.tilegame.ai;

import me.nadd.tilegame.Core;
import me.nadd.tilegame.entities.Entity;

/**
 * Basic Guard AI
 */
public class GuardAI extends MonsterAI {
    private int yAmountDown = 0;
    private int xAmountRight = 0;
    private int yAmountUp = 0;
    private int xAmountLeft = 0;
    
    public GuardAI(Entity target, int waitTime){
        this.setTickDelay(waitTime);
    }
        
    public void update(){
        if (yAmountDown <= 0 && xAmountRight <= 0 && yAmountUp <= 0 && xAmountLeft <= 0) {
            yAmountDown = (int) ((Math.random() * (Core.getMap().getXSize() / 2)));
            xAmountLeft = (int) ((Math.random() * (Core.getMap().getXSize() / 2)));
            yAmountUp = 1;
            xAmountRight = 1;
        }
        
        //System.out.println(zAmountDown + " " + xAmountLeft + " " + zAmountUp + " " + xAmountRight);
        if (yAmountDown > 0) {
        	getEntity().moveY(1);
                if (Core.getMap().getTile(getEntity().getX(), getEntity().getY()-1).isObstacle())
                    System.out.println("Wall below at " + getEntity().getX() + "x and" + (getEntity().getY()-1) + "y");
                    yAmountDown = 0;
        	yAmountDown--;
        }else if (xAmountRight > 0) {
        	xAmountRight = Core.getMap().getXSize() - 2 - getEntity().getX();
                if (Core.getMap().getTile(getEntity().getX()+1, getEntity().getY()).isObstacle())
                    System.out.println("Wall right at" + (getEntity().getX()+1) + "x and" + (getEntity().getY()) + "y");
                    xAmountRight = 0;
        	getEntity().moveX(1);
        }else if (xAmountLeft > 0) {
        	getEntity().moveX(-1);
                if (Core.getMap().getTile(getEntity().getX()-1, getEntity().getY()).isObstacle())
                    System.out.println("Wall left at " + (getEntity().getX()-1) + "x and" + (getEntity().getY()+1) + "y");
                    xAmountLeft = 0;
        	xAmountLeft--;
        }else if (yAmountUp > 0) {
        	yAmountUp = getEntity().getY() - 1;
                if (Core.getMap().getTile(getEntity().getX(), getEntity().getY()+1).isObstacle())
                    System.out.println("Wall above at" + getEntity().getX() + "x and" + (getEntity().getY()+1) + "y");
                    yAmountUp = 0;
        	getEntity().moveY(-1);
        }
        
        this.defaultAttack();
    }
}
