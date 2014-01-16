package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WeaponOfChoice.MainMenu.MainMenuScreen;
import com.BombingGames.WurfelEngine.WEMain;

/**
 *
 * @author Benedikt Vogler
 */
public class WeaponOfChoice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                WEMain.construct("Weapon of Choice - Made with WE V" + WEMain.VERSION, args);
                WEMain.setMainMenu(new MainMenuScreen());
                WEMain.launch();  
    }
    
}
