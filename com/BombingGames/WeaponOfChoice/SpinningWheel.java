package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.View;
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
    private int spintime;
   
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
        
    public void render(View view){
        if (visible){
            int y=50;
            for (Weapon weapon : this) {
                weapon.renderBig(view, Gdx.graphics.getWidth()/2,y);
                y = y +50;
            }
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
