package com.BombingGames.WeaponOfChoice;

import com.badlogic.gdx.backends.lwjgl.audio.Wav.Sound;
import com.bombinggames.wurfelengine.WE;
import com.bombinggames.wurfelengine.core.Gameobjects.MovableEntity;
import com.bombinggames.wurfelengine.extension.shooting.Weapon;

/**
 *
 * @author Benedikt Vogler
 */
public class CustomWeapon extends Weapon {
	private static final long serialVersionUID = 1L;
    
    public static void init(){
//        if (getSpritesheetBig() == null) {
//            setSpritesheetBig((TextureAtlas) WE.getAsset("com/bombinggames/WeaponOfChoice/SpritesBig.txt"));
//            for (TextureAtlas.AtlasRegion region : getSpritesheetBig().getRegions()) {
//                    region.flip(false, true);
//            }
//            for (Texture tex : getSpritesheetBig().getTextures()) {
//                tex.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
//            }
//        }
    }
    
    public CustomWeapon(byte id, MovableEntity character) {
        super(id, character);
        
        switch (id){
            case 0:
                setFireSound("melee");
                setReload("wiz"); 
            break;
                
            case 1:
                setFireSound("shot"));
                setReload("reload"); 
            break;
                
            case 2:
                setFireSound("punch");
                //setReload((Sound) WEMain.getInstance().manager.get("com/bombinggames/WeaponOfChoice/Sounds/melee.wav")); 
            break;
                
            case 3:
                setFire((Sound) WE.getAsset("com/bombinggames/WeaponOfChoice/Sounds/shotgun.wav"));
                setReload((Sound) WE.getAsset("com/bombinggames/WeaponOfChoice/Sounds/reload.wav")); 
            break;    

            case 4:
                setFire((Sound) WE.getAsset("com/bombinggames/WeaponOfChoice/Sounds/bust.wav"));
                setReload((Sound) WE.getAsset("com/bombinggames/WeaponOfChoice/Sounds/reload.wav")); 
            break;
                                 
            case 5:
                setFire((Sound) WE.getAsset("com/bombinggames/WeaponOfChoice/Sounds/poop.wav"));
                //setReload((Sound) WEMain.getInstance().manager.get("com/bombinggames/WeaponOfChoice/Sounds/reload.wav")); 
            break;
                
            case 6:
                setFire((Sound) WE.getAsset("com/bombinggames/WeaponOfChoice/Sounds/thump.wav"));
                setReload((Sound) WE.getAsset("com/bombinggames/WeaponOfChoice/Sounds/reload.wav")); 
            break;
                
            case 7:                
                setFire((Sound) WE.getAsset("com/bombinggames/WeaponOfChoice/Sounds/fire.wav"));
                setReload((Sound) WE.getAsset("com/bombinggames/WeaponOfChoice/Sounds/reload.wav")); 
            break;     
        }
    }
}