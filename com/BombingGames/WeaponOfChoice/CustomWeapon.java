package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractMovableEntity;
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
    
    public CustomWeapon(int id, AbstractMovableEntity character) {
        super(id, character);
        
        switch (id){
            case 0:
                setFire((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/melee.wav"));
                setReload((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/wiz.wav")); 
            break;
                
            case 1:
                setFire((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/shot.wav"));
                setReload((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/reload.wav")); 
            break;
                
            case 2:
                setFire((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/punch.wav"));
                //setReload((Sound) WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/melee.wav")); 
            break;
                
            case 3:
                setFire((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/shotgun.wav"));
                setReload((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/reload.wav")); 
            break;    

            case 4:
                setFire((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/bust.wav"));
                setReload((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/reload.wav")); 
            break;
                                 
            case 5:
                setFire((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/poop.wav"));
                //setReload((Sound) WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/Sounds/reload.wav")); 
            break;
                
            case 6:
                setFire((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/thump.wav"));
                setReload((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/reload.wav")); 
            break;
                
            case 7:                
                setFire((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/fire.wav"));
                setReload((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/reload.wav")); 
            break;     
        }
    }
    
    public static int getScaling() {
        return scaling;
    }
}