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
    private int currentRandom;
    private int wheelSpeed;
    private int wheelTimer;

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
        timer = spintime;
        controller.setTimespeed(0.2f);
        wheelSpeed=1;
        wheelTimer=1;

    }
    
    public void update(float delta){
        if (visible) {
            timer -= delta;
        
            if (timer <= 0) {//reset
                visible = false;
                timer = spintime;
                current = currentRandom;
                controller.equipWeapon(current);
                controller.setTimespeed(1f);
            }

            wheelSpeed += 1+ delta/1000f;//time to pass before new random item get's bigger
            
            if (wheelSpeed >500)wheelSpeed=5000;//stop it
            
            wheelTimer -= delta;
            if (wheelTimer <= 0){
                wheelTimer = wheelSpeed;
                currentRandom = (int) (Math.random()*size());
            }
        }
    }
        
    public void render(View view){
        if (visible){
            
            get(currentRandom).renderBig(view,
                Gdx.graphics.getWidth()/2-25*Weapon.getScaling(),
                Gdx.graphics.getHeight()/2-25*Weapon.getScaling());

            Sprite sprite = new Sprite(Weapon.getSpritesheetBig().findRegion("canvas"));
            sprite.setX(Gdx.graphics.getWidth()/2-30*Weapon.getScaling());
            sprite.setY(Gdx.graphics.getHeight()/2-30*Weapon.getScaling());
            sprite.scale(Weapon.getScaling());
            sprite.draw(view.getBatch());

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
