package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.View;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 */
public class SpinningWheel extends ArrayList<Weapon> {
    private static final long serialVersionUID = 1L;
    private boolean visible;
    private int current = 0;
    private final int spintime = 5000;
    private int timer;
   
    /**
     * Returns a new selection
     */
    public void spin(){
        visible = true;
        int newSelection;
        do 
            newSelection = (int) ((size()-1)*Math.random());
        while(newSelection == current);
        current = newSelection;
        timer = spintime;
    }
    
    public Weapon getCurrentWeapon(){
        return get(current);
    }
    
    public void update(float delta){
        if (visible)
            timer -= delta;
        
        if (timer <= 0) {//reset
            visible = false;
            timer = spintime;
        }
        
    }
        
    public void render(View view){
        if (visible){
            int y=50*Weapon.getScaling();
            for (Weapon weapon : this) {
                weapon.renderBig(view, Gdx.graphics.getWidth()/2-25*Weapon.getScaling(),y);
                
                if (weapon.getId() == current){
                    Sprite sprite = new Sprite(Weapon.getSpritesheetBig().findRegion("canvas"));
                    sprite.setX(Gdx.graphics.getWidth()/2-30*Weapon.getScaling());
                    sprite.setY(y);
                    sprite.scale(Weapon.getScaling());
                    sprite.draw(view.getBatch());
                }
                    
                y = y +350;
            }
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public Object clone() {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
}
