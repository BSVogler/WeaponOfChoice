package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractCharacter;
import com.BombingGames.WurfelEngine.WE;
import com.BombingGames.WurfelEngine.shooting.Weapon;
import com.badlogic.gdx.backends.openal.Wav.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author Benedikt Vogler
 */
public class CustomWeapon extends Weapon {
    private static final int scaling = 2;
    
    private final int id;
    private String name;

    private AbstractCharacter character;//the char holding the weapon
    
    //sound
    private Sound fire;
    private Sound reload;
    
    //stats
    private int delay;
    private int shots;
    private int relodingTime;
    private int distance;
    private int bps;//bullets per shot
    private float spread;
    private int damage;
    private int bulletSprite;
    private int impactSprite;
    
    private int shotsLoaded;
    private int reloading;
    private int shooting;

    public static void init(){
        if (getSpritesheetBig() == null) {
            setSpritesheetBig((TextureAtlas) WE.getAsset("com/BombingGames/WeaponOfChoice/SpritesBig.txt"));
            for (TextureAtlas.AtlasRegion region : getSpritesheetBig().getRegions()) {
                    region.flip(false, true);
            }
            for (Texture tex : getSpritesheetBig().getTextures()) {
                tex.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
            }
        }
    }
    private int explode;
    
    
    public CustomWeapon(int id, AbstractCharacter character) {
        super(id, character);
        
        this.id = id;
        this.character = character;
        
        switch (id){
            case 0:
                name="katana";
                delay = 900;
                relodingTime =0;
                shots = 1;
                distance = 0;
                bps = 10;
                spread = 0.5f;
                damage = 1000;
                bulletSprite = -1;
                impactSprite=15;
                
                fire = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/melee.wav");
                reload = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/wiz.wav"); 
            break;
                
            case 1:
                name="pistol";
                delay = 400;
                relodingTime =1000;
                shots = 7;
                distance = 10;
                bps = 1;
                spread = 0.1f;
                damage = 800;
                bulletSprite = 0;
                impactSprite=19;
                
                fire = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/shot.wav");
                reload = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/reload.wav"); 
            break;
                
            case 2:
                name="fist";
                delay = 500;
                relodingTime =0;
                shots = 1;
                distance = 0;
                bps = 10;
                spread = 0.4f;
                bulletSprite = -1;
                damage = 500;
                impactSprite=15;
                
                fire = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/punch.wav");
                //reload = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/melee.wav"); 
            break;
                
            case 3:
                name="shotgun";
                delay = 600;
                relodingTime =1300;
                shots = 2;
                distance = 5;
                bps = 20;
                spread = 0.2f;
                damage = 400;
                bulletSprite = 0;
                impactSprite=19;
                
                fire = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/shotgun.wav");
                reload = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/reload.wav"); 
            break;    

            case 4:
                name="machine gun";
                delay = 70;
                relodingTime =1300;
                shots = 25;
                distance = 10;
                bps = 1;
                spread = 0.08f;
                damage = 400;
                bulletSprite = 0;
                impactSprite=19;
                
                fire = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/bust.wav");
                reload = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/reload.wav"); 
            break;
                                 
            case 5:
                name="poop";
                delay = 900;
                relodingTime =500;
                shots = 1;
                distance = 3;
                bps = 1;
                spread = 0.2f;
                damage = 400;
                bulletSprite = 3;
                explode = 1;
                impactSprite=19;
                
                fire = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/poop.wav");
                //reload = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/reload.wav"); 
            break;
                
            case 6:
                name="rocket launcher";
                delay = 0;
                relodingTime =1500;
                shots = 1;
                distance = 5;
                bps = 1;
                damage = 100;
                bulletSprite = 2;
                explode = 2;
                spread = 0.1f;
                impactSprite=19;
                
                fire = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/thump.wav");
                reload = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/reload.wav"); 
            break;
                
            case 7:
                name="fire launcher";
                delay = 40;
                relodingTime =1700;
                shots = 50;
                distance = 3;
                bps = 5;
                spread = 0.4f;
                damage = 200;
                bulletSprite = 1;
                impactSprite=18;
                
                fire = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/fire.wav");
                reload = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/reload.wav"); 
            break;     
                

           
        }
        shotsLoaded = shots; //fully loaded
    }
    
    public static int getScaling() {
        return scaling;
    }
}