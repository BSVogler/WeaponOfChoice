package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Map.Generator;

/**
 *
 * @author Benedikt Vogler
 */
public class ArenaGenerator implements Generator {

    @Override
    public int generate(int x, int y, int z) {
        if (z==1)
            return 1;
        else
            return 0;
    }
    
}
