package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Configuration;

/**
 *
 * @author Benedikt Vogler
 */
public class CustomConfiguration extends Configuration {

    @Override
    public int getChunkGenerator() {
        return 2;
    }

    @Override
    public String getSpritesheetPath() {
        return "com/BombingGames/WeaponOfChoice/sprites/Spritesheet";
    }
}
