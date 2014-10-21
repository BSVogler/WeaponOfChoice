package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.GameView;
import com.BombingGames.WurfelEngine.WE;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.audio.Ogg.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;

/**
 *
 * @author Benedikt Vogler
 */
public class SpinningWheel extends ArrayList<CustomWeapon> {
    private static final long serialVersionUID = 1L;
    
    private final CustomGameController controller;
    private boolean visible;
    private int current = -1;
    private final int spintime = 5000;
    private int timer;
    private int currentRandom;
    private float wheelSpeed;
    private float wheelTimer;

    public SpinningWheel(CustomGameController ctlr) {
        controller = ctlr;
    }
   
    
    /**
     * Returns a new selection
     */
    public void spin(){
        Sound dudeldi = (Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/dudeldi.ogg");
        dudeldi.play();
        controller.getMusic().setVolume(0.2f);
        
        visible = true;
        timer = spintime;
        controller.setTimespeed(0.3f);
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
                controller.getMusic().setVolume(1f);
            }

            wheelSpeed *= 1+ delta/400f;//time to pass before new random item get's bigger
            
            if (wheelSpeed >1000)
                wheelSpeed=50000;//stop it
            
            wheelTimer -= delta;
            if (wheelTimer <= 0){
                wheelTimer = wheelSpeed;
                currentRandom = (int) (Math.random()*size());
            }
        }
    }
        
    public void render(GameView view){
        if (visible){
            Sprite sprite;
            sprite = new Sprite(CustomWeapon.getSpritesheetBig().findRegion("canvas"));
            sprite.flip(false, true);
            sprite.setX(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2);
            sprite.setY(Gdx.graphics.getHeight()/2-30*CustomWeapon.getScaling());
            sprite.scale(CustomWeapon.getScaling());
            sprite.draw(WE.getEngineView().getBatch());
            
            if (controller.getRound()==1){
                sprite = new Sprite(CustomWeapon.getSpritesheetBig().findRegion("warmup"));
            } else {
                sprite = new Sprite(CustomWeapon.getSpritesheetBig().findRegion("newround"));
            }
            sprite.setX(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2);
            sprite.setY(Gdx.graphics.getHeight()/2-200);
            sprite.scale(CustomWeapon.getScaling());
            sprite.flip(false, true);
            sprite.draw(WE.getEngineView().getBatch());
                
            get(currentRandom).renderBig(view,
                Gdx.graphics.getWidth()/2-10*CustomWeapon.getScaling(),
                Gdx.graphics.getHeight()/2-25*CustomWeapon.getScaling()
            );
        }
        if (current>-1)
            get(current).renderBig(view,
                Gdx.graphics.getWidth()-150,
                Gdx.graphics.getHeight()-150
            );
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public Object clone() {
        return super.clone();
    }
}
