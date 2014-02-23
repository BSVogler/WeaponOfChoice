package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Configuration;
import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractEntity;
import com.BombingGames.WurfelEngine.Core.Gameobjects.AnimatedBlock;
import com.BombingGames.WurfelEngine.Core.Gameobjects.Block;
import com.BombingGames.WurfelEngine.Core.Gameobjects.EntityFactory;
import com.BombingGames.WurfelEngine.Core.Gameobjects.EntitySpawner;
import com.BombingGames.WurfelEngine.Core.Gameobjects.ExplosiveBarrel;
import com.BombingGames.WurfelEngine.Core.Gameobjects.SimpleEntity;
import com.BombingGames.WurfelEngine.Core.Map.Point;
import com.badlogic.gdx.Gdx;

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
    public EntityFactory getEntityFactory() {
        return new CustomEntityFactory(); //To change body of generated methods, choose Tools | Templates.
    }

    private static class CustomEntityFactory implements EntityFactory {

        @Override
        public AbstractEntity produce(int id, int value, Point point) {
            AbstractEntity entity;
            switch (id){
                //...
                case 40:
                    entity = new Enemy(id, point);
                    break;
                default:
                    entity = new SimpleEntity(id);
                    Gdx.app.error("CustomBlockFactory", "Entity not defined.");
            }
            return entity;
        }
    }
    
    
    
}
