package com.BombingGames.WeaponOfChoice;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 */
public class SpinningWheel extends ArrayList<Weapon> {
    private static final long serialVersionUID = 1L;
    private boolean visible;
    private int current = 0;
   
    /**
     * Returns a new selection
     */
    public void spin(){
        int newSelection;
        do 
            newSelection = (int) (size()+Math.random());
        while(newSelection == current);
        current=newSelection;
    }
    
    public Weapon getCurrentWeapon(){
        return get(current);
    }
        
    public void render(){
        if (visible){
            int y=50;
            for (Weapon weapon : this) {
                weapon.renderBig(Gdx.graphics.getWidth()/2,y);
                y = y +50;
            }
        }
    }
}
