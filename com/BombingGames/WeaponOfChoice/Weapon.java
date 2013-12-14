package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.View;
import com.BombingGames.WurfelEngine.WEMain;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author Benedikt Vogler
 */
public class Weapon {
    private String name;
    private final int id;
    private static TextureAtlas spritesheetBig;
    private static final int scaling = 4;
    
    //stats
    private int delay;
    private int shots;
    private int relodingTime;
    private int distance;

    public static void init(){
        if (spritesheetBig == null) {
            spritesheetBig = WEMain.getInstance().manager.get("com/BombingGames/WeaponOfChoice/SpritesBig.txt");
            for (TextureAtlas.AtlasRegion region : spritesheetBig.getRegions()) {
                    region.flip(false, true);
            }
            for (Texture tex : spritesheetBig.getTextures()) {
                tex.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
            }
        }
    }
    
    public Weapon(int id) {
        this.id = id;
        
        switch (id){
            case 0:
                name="katana";
                delay = 600;
                relodingTime =0;
                shots = 1;
                distance = 1;
            break;
            case 1:
                name="pistol";
                delay = 600;
                relodingTime =0;
                shots = 7;
                distance = 10;
            break;
            case 2:
                name="fist";
                delay = 400;
                relodingTime =0;
                shots = 1;
                distance = 1;
            break;
            case 3:
                name="poop";
                delay = 900;
                relodingTime =0;
                shots = 1;
                distance = 7;
            break;
            case 4:
                name="rocket launcher";
                delay = 0;
                relodingTime =1500;
                shots = 1;
                distance = 6;
                
            break;
            case 5:
                name="shotgun";
                delay = 200;
                relodingTime =0;
                shots = 2;
                distance = 5;
            break;
            case 6:
                name="machine gun";
                delay = 30;
                relodingTime =900;
                shots = 30;
                distance = 10;
            break;    
        }
    }
    
    /**
     * Renders a big version of the image
     * @param view
     * @param x
     * @param y 
     */
    public void renderBig(View view, int x, int y){
        Sprite sprite = new Sprite(spritesheetBig.findRegion(""+id));
        sprite.setX(x);
        sprite.setY(y);
        sprite.scale(scaling);
        sprite.draw(view.getBatch());
    
    }

    public static TextureAtlas getSpritesheetBig() {
        return spritesheetBig;
    }

    public int getId() {
        return id;
    }

    public static int getScaling() {
        return scaling;
    }
    
    
    
}
