package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Configuration;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author Benedikt Vogler
 */
public class CustomConfiguration extends Configuration {

    @Override
    public boolean useLightEngine() {
        return false;
    }

    @Override
    public int getChunkGenerator() {
        return 2;
    }

    @Override
    public String getSpritesheetPath() {
        return "com/BombingGames/WeaponOfChoice/sprites/Spritesheet";
    }
    
    @Override
    public void initLoadingQueque(AssetManager manager) {
        super.initLoadingQueque(manager);
        manager.load("com/BombingGames/WeaponOfChoice/SpritesBig.txt", TextureAtlas.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/melee.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/punch.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/reload.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/shot.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/shotgun.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/wiz.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/dudeldi.ogg", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/bust.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/scream1.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/scream2.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/scream3.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/scream4.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/impactFlesh.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/fire.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/poop.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/thump.wav", Sound.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/music.ogg", Music.class);
        manager.load("com/BombingGames/WeaponOfChoice/Sounds/dead.ogg", Sound.class);
    }
}
