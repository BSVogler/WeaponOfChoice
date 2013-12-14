package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractEntity;

/**
 *
 * @author Benedikt Vogler
 */
public class Bullet extends AbstractEntity {
    private int[] dir = new int[3];
    
   public Bullet(int id){
       super(id);
   } 
    @Override
    public void update(float delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
