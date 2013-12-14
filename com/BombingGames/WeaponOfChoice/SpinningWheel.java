package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.View;
import com.BombingGames.WurfelEngine.WEMain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.openal.Ogg.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 */
public class SpinningWheel extends ArrayList<Weapon> {
    private static final long serialVersionUID = 1L;
    
    private final CustomGameController controller;
    private boolean visible;
    private int current = 0;
    private final int spintime = 5000;
    private int timer;

    public SpinningWheel(CustomGameController ctlr) {
        controller = ctlr;
    }
   
    
    /**
     * Returns a new selection
     */
    public void spin(){
        Sound dudeldi = (Sound) WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/dudeldi.ogg");
        dudeldi.play();
        visible = true;
        int newSelection;
        do 
            newSelection = (int) (size()*Math.random());
        while(newSelection == current);
        current = newSelection;
        timer = spintime;
        controller.setTimespeed(0.2f);

    }
    
    public void update(float delta){
        if (visible)
            timer -= delta;
        
        if (timer <= 0) {//reset
            visible = false;
            timer = spintime;
            controller.equipWeapon(current);
            controller.setTimespeed(1f);
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
                    
                y = y +70*Weapon.getScaling();
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
