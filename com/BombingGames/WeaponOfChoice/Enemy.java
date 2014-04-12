package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractCharacter;
import com.BombingGames.WurfelEngine.Core.Gameobjects.AnimatedEntity;
import com.BombingGames.WurfelEngine.Core.Map.Point;
import com.BombingGames.WurfelEngine.WE;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector3;
import java.util.Arrays;

/**
 *An enemy which can follow a character.
 * @author Benedikt Vogler
 */
public class Enemy extends AbstractCharacter{
    private AbstractCharacter target;
    private int runningagainstwallCounter = 0;
    private float[] lastPos;
    private static int killcounter = 0;
    
    public void init(){
       killcounter=0; 
    }
    
    /**
     * Zombie constructor. Use AbstractEntitiy.getInstance to create an zombie.
     * @param id
     * @param pos
     */
    public Enemy(int id, Point pos) {
        super(id, 2, pos);
        setTransparent(true);
        setObstacle(true);
        setDimensionZ(1);
        setDamageSounds(new Sound[]{
            (Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/impactFlesh.wav")
        });
    }

    @Override
    public void jump() {
        super.jump(5);
    }

    @Override
    public void update(float delta) {
        if (getPos().getCoord().onLoadedMap()) {
            //follow the target
            if (target != null) {

                float dX = target.getPos().getAbsX()-getPos().getAbsX();
                float dY = target.getPos().getAbsY()-getPos().getAbsY();
                double length = Math.sqrt(dY*dY+dX*dX);
                //update the movement vector
//                setMovementX((float) (dX/length));
//                setMovementY((float) (dY/length));
//                move(0.4f);

                //attack
                if (Arrays.equals(target.getPos().getCoord().getRel(), getPos().getCoord().getRel())){
                    setMana((int) (getMana()+delta));
                    if (getMana()>=1000){
                        setMana(0);//reset
                        new AnimatedEntity(46, 0, getPos().cpy(), new int[]{300}, true, false).exist();//spawn blood
                        target.damage(50);
                    }
                }
            }
            //update as usual
            super.update(delta);

            //if standing on same position as in last update
            if (Arrays.equals(getPos().getRel(), lastPos))
                runningagainstwallCounter += delta;
            else {
                runningagainstwallCounter=0;
                lastPos = getPos().getRel();
            }

            //jump after some time
            if (runningagainstwallCounter > 500) {
                jump();
                setMana(0);
                runningagainstwallCounter=0;
            }
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
    public Vector3 getAiming() {
        throw new UnsupportedOperationException("Enemy can not aim at the moment.");
    }

    @Override
    public void dispose() {
        killcounter++;
        super.dispose();
    }

    public static int getKillcounter() {
        return killcounter;
    }
}