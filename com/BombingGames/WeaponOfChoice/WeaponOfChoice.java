package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WeaponOfChoice.MainMenu.MainMenuScreen;
import com.bombinggames.wurfelengine.WE;
import com.bombinggames.wurfelengine.core.Gameobjects.AbstractGameObject;
import com.bombinggames.wurfelengine.core.WorkingDirectory;

/**
 *
 * @author Benedikt Vogler
 */
public class WeaponOfChoice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WE.setMainMenu(new MainMenuScreen());
		WorkingDirectory.setApplicationName("WeaponOfChoice");
		AbstractGameObject.setCustomSpritesheet("com/bombinggames/WeaponOfChoice/sprites/Spritesheet");
        WE.launch("Weapon of Choice - Made with WE V" + WE.VERSION, args);  
    }
    
}
