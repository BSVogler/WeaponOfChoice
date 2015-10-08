/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BombingGames.WeaponOfChoice;

import com.badlogic.gdx.math.Vector2;
import com.bombinggames.wurfelengine.core.Gameobjects.Controllable;
import com.bombinggames.wurfelengine.core.Gameobjects.PlayerWithWeapon;

/**
 *
 * @author Benedikt Vogler
 */
public class Player extends  PlayerWithWeapon implements Controllable{
	
	public Player(int spritesPerDir, int height) {
		super(spritesPerDir, height);
	}

	@Override
	public void walk(boolean up, boolean down, boolean left, boolean right, float walkingspeed, float dt) {
		
		if (up || down || left || right){

			//update the direction vector
			Vector2 dir = new Vector2(0f,0f);

			if (up)    dir.y += -1;
			if (down)  dir.y += 1;
			if (left)  dir.x += -1;
			if (right) dir.x += 1;
			dir.nor().scl(walkingspeed);
			setHorMovement(dir);
		}
	}
	
	
}
