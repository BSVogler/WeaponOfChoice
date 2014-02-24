package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WeaponOfChoice.MainMenu.MainMenuScreen;
import com.BombingGames.WurfelEngine.WE;

/**
 *
 * @author Benedikt Vogler
 */
public class WeaponOfChoice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WE.construct("Weapon of Choice - Made with WE V" + WE.VERSION, args);
        WE.setMainMenu(new MainMenuScreen());
        WE.launch();  
    }
    
}
