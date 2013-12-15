package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Controller;
import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractCharacter;
import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractEntity;
import com.BombingGames.WurfelEngine.Core.Gameobjects.Block;
import com.BombingGames.WurfelEngine.Core.Gameobjects.Player;
import com.BombingGames.WurfelEngine.WEMain;
import com.badlogic.gdx.audio.Sound;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Benedikt Vogler
 */
public class Bullet extends AbstractEntity {
    private static Sound explosionsound;
    private float[] dir = new float[3];//movement
    private float speed;
    private int damage;
    private int distance =0;//distance traveled
    private AbstractCharacter parent;//no self shooting
    private int maxDistance = 1000;//default maxDistance
    private int explosive = 0;
    
   public Bullet(int id){
       super(id);
   } 
   
    public static void init(){
        if (explosionsound == null) explosionsound = WEMain.getInstance().manager.get("com/BombingGames/WurfelEngine/Game/Sounds/explosion2.ogg");
    }
   
    @Override
    public void update(float delta) {
        getPos().addVector(
            new float[]{
                dir[0]*delta*speed,
                dir[1]*delta*speed,
                -delta/(float)maxDistance
            }
        );
        
//        //only exist 150 update calls then destroy self
//        distance += (Math.abs(dir[0])+Math.abs(dir[1]))*delta*speed;
//        if (distance > maxDistance)
//            destroy();
        
//        if (getPos().onLoadedMap() && getPos().getBlockSafe().getId() != 0){
//            AbstractEntity flash = AbstractEntity.getInstance(15, 0, getPos().cpy());
//            flash.existNext();
//            destroy();
//        }
        
        //block hit
        if (getPos().onLoadedMap() && getPos().getBlockSafe().isObstacle()){
            AbstractEntity.getInstance(15, 0, getPos().cpy()).existNext();
            destroy();
        }
        
        //check character hit
         //get every character on this coordinate
        ArrayList<AbstractCharacter> entitylist = Controller.getMap().getAllEntitysOnCoord(getPos().getCoord(), AbstractCharacter.class);
        entitylist.remove(parent);
        if (!entitylist.isEmpty()) {
            entitylist.get(0).damage(damage);
            AbstractEntity.getInstance(16, 0, getPos().cpy()).existNext();//spawn blood
            destroy();
        }
    }

    public void setDirection(float[] dir) {
        this.dir = dir;
    }
    
    public void setSpeed(float speed){
        this.speed = speed;
    }

    public void setParent(AbstractCharacter parent) {
        this.parent = parent;
    }
    
    public void setMaxDistance(int maxDistance){
        this.maxDistance = maxDistance;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    public void setExplosive(int ex){
        explosive = ex;
    }
    
      /**
     * Explodes the barrel.
     */
    private void explode(){
        for (int x=-explosive; x<explosive; x++)
            for (int y=-explosive*2; y<explosive*2; y++)
                for (int z=-explosive; z<explosive; z++){
                    //place air
                     if (x*x + (y/2)*(y/2)+ z*z < explosive*explosive){
                        Controller.getMap().setDataSafe(
                            getPos().getCoord().cpy().addVector(new float[]{x, y, z}).getCoord() , Block.getInstance(0)
                        );
                     }
                }
        
         for (int x=-explosive; x<explosive; x++)
            for (int y=-explosive*2; y<explosive*2; y++)
                for (int z=-explosive; z<explosive; z++){
                    
                    //spawn effect
                    if (x*x + (y/2)*(y/2)+ z*z >= explosive*explosive-4 &&
                        x*x + (y/2)*(y/2)+ z*z <= explosive*explosive){
                        AbstractEntity effect = AbstractEntity.getInstance(
                            41,
                            0,
                            getPos().getCoord().cpy().addVector(new float[]{x, y, z}).getPoint()
                        );
                        effect.existNext();
                        ArrayList<AbstractEntity> list = Controller.getMap().getAllEntitysOnCoord(effect.getPos().getCoord());
                        for (AbstractEntity ent : list) {
                            if ((ent instanceof AbstractCharacter) && ! (ent instanceof Player))
                                ((AbstractCharacter) ent).damage(100);
                        }
                    }
                    
                }
         if (explosionsound != null) explosionsound.play();
         Controller.requestRecalc();
    }

    
    @Override
    public void destroy() {
        if (explosive>0) explode();
        super.destroy();
    }
}
