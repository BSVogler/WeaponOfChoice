package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Controller;
import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractCharacter;
import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractEntity;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Benedikt Vogler
 */
public class Bullet extends AbstractEntity {
    private float[] dir = new float[3];
    private float speed;
    private int updates =0;
    private AbstractCharacter parent;
    
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
        
        if (getPos().onLoadedMap() && getPos().getBlockSafe().getId() != 0){
            AbstractEntity flash = AbstractEntity.getInstance(15, 0, getPos().cpy());
            flash.existNext();
            destroy();
        }
        
        if (getPos().onLoadedMap() && getPos().getBlockSafe().getId() != 0){
            AbstractEntity.getInstance(15, 0, getPos().cpy()).existNext();
            destroy();
        }
        
        //check character hit
         //get every character
        ArrayList<AbstractCharacter> entitylist = Controller.getMap().getAllEntitysOfType(AbstractCharacter.class);
        entitylist.remove(parent);
        
        int[] coords = getPos().getCoord().getRel();
        
        //check every character if they are ina the same block
        int i = 0;
        while (i < entitylist.size() && !Arrays.equals( entitylist.get(i).getPos().getCoord().getRel(), coords)){
            i++;
        }
        
        //if enemy is hit
        if (i < entitylist.size() && Arrays.equals(entitylist.get(i).getPos().getCoord().getRel(), coords)) {
            entitylist.get(i).damage(20);
            AbstractEntity.getInstance(15, 1, getPos().cpy()).existNext();
            destroy();
        }
    }

    void setDirection(float[] dir) {
        this.dir = dir;
    }
    
    void setSpeed(float speed){
        this.speed = speed;
    }

    public void setParent(AbstractCharacter parent) {
        this.parent = parent;
    }
}
