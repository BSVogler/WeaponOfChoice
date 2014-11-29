package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Gameobjects.AnimatedEntity;
import com.BombingGames.WurfelEngine.Core.Gameobjects.MovableEntity;
import com.BombingGames.WurfelEngine.WE;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector3;
import java.util.Arrays;

/**
 *An enemy which can follow a character.
 * @author Benedikt Vogler
 */
public class Enemy extends MovableEntity{
    private MovableEntity target;
    private int runningagainstwallCounter = 0;
    private float[] lastPos;
    private static int killcounter = 0;
    
    public void init(){
       killcounter=0; 
    }
    
    /**
     * Zombie constructor. Use AbstractEntitiy.getInstance to create an zombie.
     * @param id
     */
    public Enemy(int id) {
        super(id, 2);
        setTransparent(true);
        setObstacle(true);
        setDamageSounds(new Sound[]{
            (Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/impactFlesh.wav")
        });
    }

    @Override
    public void jump() {
        jump(5);
    }

    @Override
    public void update(float delta) {
        if (getPosition().getCoord().onLoadedMapHorizontal()) {
            //follow the target
            if (target != null) {
				Vector3 d = new Vector3();
                d.x = target.getPosition().getX()-getPosition().getX();
                d.y = target.getPosition().getY()-getPosition().getY();
				d.nor();
				d.z = getMovement().z;
				// update the movement vector
				setMovement(d);
                setSpeed(0.4f);

                //attack
                if (Arrays.equals(target.getPosition().getCoord().getTriple(), getPosition().getCoord().getTriple())){
                    setMana((int) (getMana()+delta));
                    if (getMana()>=1000){
                        setMana(0);//reset
                        new AnimatedEntity(46, 0, new int[]{300}, true, false).spawn(getPosition().cpy());//spawn blood
                        target.damage(50);
                    }
                }
            }
            //update as usual
            super.update(delta);

            //if standing on same position as in last update
            if (Arrays.equals(getPosition().getRel(), lastPos))
                runningagainstwallCounter += delta;
            else {
                runningagainstwallCounter=0;
                lastPos = getPosition().getRel();
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
    public void setTarget(MovableEntity target) {
        this.target = target;
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