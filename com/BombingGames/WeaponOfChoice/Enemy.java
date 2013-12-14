package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractCharacter;
import com.BombingGames.WurfelEngine.Core.Map.Point;

/**
 *An enemy which can follow a character.
 * @author Benedikt Vogler
 */
public class Enemy extends AbstractCharacter{
    private AbstractCharacter target;
    private int runningagainstwallCounter = 0;
    private Point lastPos;
    
    /**
     * Zombie constructor. Use AbstractEntitiy.getInstance to create an zombie.
     * @param id
     * @param pos
     */
    public Enemy(int id, Point pos) {
        super(id, 1, pos, false);
        setTransparent(true);
        setObstacle(true);
        setDimensionZ(1);
    }

    @Override
    public void jump() {
        super.jump(5);
    }

    @Override
    public void update(float delta) {
        //follow the target
        if (target != null) {
            walk(
                (target.getPos().getAbsY()<getPos().getAbsY()),
                (target.getPos().getAbsY()>getPos().getAbsY()),
                (target.getPos().getAbsX()<getPos().getAbsX()),
                (target.getPos().getAbsX()>getPos().getAbsX()),
                .35f
            );
        }
        //update as usual
        super.update(delta);
        
        //if standing on same position as in last update
        if (getPos().equals(lastPos))
            runningagainstwallCounter += delta;
        else {
            runningagainstwallCounter=0;
            lastPos = getPos().cpy();
        }
        
        //jump after one second
        if (runningagainstwallCounter > 50) {
            jump();
            runningagainstwallCounter=0;
        }
    }

    /**
     * Set the target which the zombie follows.
     * @param target an character
     */
    public void setTarget(AbstractCharacter target) {
        this.target = target;
    }

    @Override
    public float[] getAiming() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}