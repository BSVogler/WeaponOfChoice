package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.View;
import com.BombingGames.WurfelEngine.WEMain;
import com.badlogic.gdx.backends.openal.Wav.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author Benedikt Vogler
 */
public class Weapon {
    private String name;
    private final int id;
    private static TextureAtlas spritesheetBig;
    private static final int scaling = 2;
    
    //sound
    private Sound fire;
    private Sound reload;
    
    //stats
    private int delay;
    private int shots;
    private int relodingTime;
    private int distance;
    
    private int shotsLoaded;
    private int reloading;
    private int shooting;

    public static void init(){
        if (spritesheetBig == null) {
            spritesheetBig = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/SpritesBig.txt");
            for (TextureAtlas.AtlasRegion region : spritesheetBig.getRegions()) {
                    region.flip(false, true);
            }
            for (Texture tex : spritesheetBig.getTextures()) {
                tex.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
            }
        }
    }
    
    public Weapon(int id) {
        this.id = id;
        
        switch (id){
            case 0:
                name="katana";
                delay = 600;
                relodingTime =0;
                shots = 1;
                distance = 1;
                
                fire = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/melee.wav");
                reload = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/wiz.wav"); 
            break;
            case 1:
                name="pistol";
                delay = 600;
                relodingTime =1000;
                shots = 7;
                distance = 10;
                
                fire = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/shot.wav");
                reload = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/reload.wav"); 
            break;
            case 2:
                name="fist";
                delay = 500;
                relodingTime =0;
                shots = 1;
                distance = 1;
                
                fire = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/punch.wav");
                reload = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/melee.wav"); 
            break;
            case 3:
                name="shotgun";
                delay = 600;
                relodingTime =1500;
                shots = 2;
                distance = 5;
                
                fire = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/shotgun.wav");
                reload = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/reload.wav"); 
            break;    

            case 4:
                name="rocket launcher";
                delay = 0;
                relodingTime =1500;
                shots = 1;
                distance = 6;
                
                fire = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/shot.wav");
                reload = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/reload.wav"); 
            break;
            
            case 5:
                name="poop";
                delay = 900;
                relodingTime =500;
                shots = 1;
                distance = 7;
            break;    

            case 6:
                name="machine gun";
                delay = 30;
                relodingTime =900;
                shots = 30;
                distance = 10;
                
                fire = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/shot.wav");
                reload = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/reload.wav"); 
            break;
        }
        shotsLoaded = shots; //fully loaded
    }
    
    /**
     * Renders a big version of the image
     * @param view
     * @param x
     * @param y 
     */
    public void renderBig(View view, int x, int y){
        Sprite sprite = new Sprite(spritesheetBig.findRegion(""+id));
        sprite.setX(x);
        sprite.setY(y);
        sprite.scale(scaling);
        sprite.draw(view.getBatch());
    
    }

    public static TextureAtlas getSpritesheetBig() {
        return spritesheetBig;
    }

    public int getId() {
        return id;
    }

    public static int getScaling() {
        return scaling;
    }
    
    /**
     * Manages the weapon
     * @param trigger Is the trigger down?
     * @param delta
     */
    public void update(boolean trigger, float delta){
        if (shooting > 0){
            shooting-=delta;
        } else {
            if (reloading >= 0) {
                reloading-=delta;
                if (reloading<=0)//finished reloading
                    shotsLoaded = shots;
            } else {
                //if not shootring or loading
                if (shotsLoaded <= 0)//autoreload
                    reload();

                if (trigger && shotsLoaded>0)
                    shoot();  
            }
        }
    }
    
    
    private void shoot(){
        fire.play();
                
        shooting = delay;
        shotsLoaded--;      
    }
    
    public void reload(){
        reloading =relodingTime;
        reload.play();
    }

    public int getShotsLoaded() {
        return shotsLoaded;
    }

    public int getShots() {
        return shots;
    }

    public int getReloading() {
        return reloading;
    }

    public int getShooting() {
        return shooting;
    }

    void trigger() {
         if (shooting <= 0 && reloading <= 0){
            //if not shootring or loading
            if (shotsLoaded <= 0)//autoreload
                reload();

            if (shotsLoaded>0)
                shoot();  
        }
    }
}
