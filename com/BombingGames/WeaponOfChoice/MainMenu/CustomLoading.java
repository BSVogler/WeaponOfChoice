/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BombingGames.WeaponOfChoice.MainMenu;

import com.bombinggames.wurfelengine.core.Loading.LoadingScreen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author Benedikt Vogler
 */
public class CustomLoading extends LoadingScreen {

	@Override
	public void customLoading(AssetManager manager) {
		manager.load("com/bombinggames/WeaponOfChoice/SpritesBig.txt", TextureAtlas.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/melee.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/punch.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/reload.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/shot.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/shotgun.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/wiz.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/dudeldi.ogg", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/bust.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/scream1.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/scream2.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/scream3.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/scream4.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/impactFlesh.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/fire.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/poop.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/thump.wav", Sound.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/music.ogg", Music.class);
        manager.load("com/bombinggames/WeaponOfChoice/Sounds/dead.ogg", Sound.class);
	}
	
}
