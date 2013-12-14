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
    private String Name;
    private final int id;
    private static TextureAtlas spritesheetBig;
    private static int scaling = 4;

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
