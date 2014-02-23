package com.BombingGames.WeaponOfChoice.MainMenu;

import com.BombingGames.WeaponOfChoice.CustomConfiguration;
import com.BombingGames.WeaponOfChoice.CustomGameController;
import com.BombingGames.WeaponOfChoice.CustomGameView;
import com.BombingGames.WurfelEngine.Configuration;
import com.BombingGames.WurfelEngine.WE;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


/**
 * The controlelr of the main Menu manages the data.
 * @author Benedikt
 */
public class Controller {
    
    private final MenuItem[] menuItems = new MenuItem[2];
    private final Sound fx;
    
    /**
     * Creates a new Controller
     */
    public Controller() {
        TextureAtlas texture = new TextureAtlas(Gdx.files.internal("com/BombingGames/WurfelEngine/MainMenu/Images/MainMenu.txt"), true);
                
        //menuItems[0] = new MenuItem(0, texture.getRegions().get(3));
        menuItems[0] = new MenuItem(1, texture.getRegions().get(1));
        //menuItems[2] = new MenuItem(2, texture.getRegions().get(0));
        menuItems[1] = new MenuItem(3, texture.getRegions().get(0));
        
        fx = Gdx.audio.newSound(Gdx.files.internal("com/BombingGames/WurfelEngine/MainMenu/click2.wav"));
        Gdx.input.setInputProcessor(new InputListener());
        
        //add asstes to queque
        AssetManager manager = WE.getAssetManager();
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
    
    /**
     * updates game logic
     * @param delta
     */
    public void update(int delta){
        if (menuItems[0].isClicked()) { 
            MainMenuScreen.setLoadMap(false);
            fx.play();
            CustomGameController ctrl = new CustomGameController();
            WE.initGame(ctrl, new CustomGameView(ctrl), new CustomConfiguration());
        } else if (menuItems[1].isClicked()){
                fx.play();
                Gdx.app.exit();
        }
    }

    /**
     *
     * @return
     */
    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    /**
     *
     */
    public void dispose(){
        fx.dispose();
    }

    private class InputListener implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.ESCAPE)
                Gdx.app.exit();
            return true;
        }

        @Override
        public boolean keyUp(int keycode) {
            return true;
        }

        @Override
        public boolean keyTyped(char character) {
            return true;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return true;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return true;
        }

        @Override
        public boolean scrolled(int amount) {
            return true;
        }
    }
}