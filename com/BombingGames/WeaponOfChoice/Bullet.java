package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractEntity;

/**
 *
 * @author Benedikt Vogler
 */
public class Bullet extends AbstractEntity {
    private float[] dir = new float[3];
    private float speed;
    private int updates =0;
    
   public Bullet(int id){
       super(id);
   } 
    @Override
    public void update(float delta) {
        getPos().addVector(
            new float[]{
                dir[0]*delta*speed,
                dir[1]*delta*speed,
                0
            }
        );
        
        //only exist 150 update calls then destroy self
        updates++;
        if (updates > 500)
            destroy();
    }

    void setDirection(float[] dir) {
        this.dir = dir;
    }
    
    void setSpeed(float speed){
        this.speed = speed;
    }
    
}
