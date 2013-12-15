package com.BombingGames.WeaponOfChoice;

import static com.BombingGames.WurfelEngine.Core.Controller.getLightengine;
import static com.BombingGames.WurfelEngine.Core.Controller.getMap;
import com.BombingGames.WurfelEngine.Core.GameplayScreen;
import com.BombingGames.WurfelEngine.Core.View;
import com.BombingGames.WurfelEngine.MainMenu.MainMenuScreen;
import com.BombingGames.WurfelEngine.WEMain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/**
 *
 * @author Benedikt
 */
public class CustomGameView extends View{
    private final CustomGameController controller;
    
     /**
     *
     * @param controller
     */
    public CustomGameView(CustomGameController controller) {
         super();
         Gdx.input.setInputProcessor(new InputListener());
         this.controller = controller;
     }


     @Override
     public void render(){
         super.render();
         
         getBatch().begin();
         controller.getSpinningWheel().render(this);
         getBatch().end();
         Weapon weapon = controller.getCurrentWeapon();
         if (weapon != null)
            drawString("Shots: "+weapon.getShotsLoaded()+"/"+weapon.getShots(), Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-100, Color.WHITE.cpy());
        ShapeRenderer sh = getShapeRenderer();
        sh.begin(ShapeRenderer.ShapeType.Filled);
        sh.setColor(
            new Color(
                1-(controller.getPlayer().getHealt()/100f),
                controller.getPlayer().getHealt()/100f,
                0,
                1
            )
        );
        sh.rect(
            Gdx.graphics.getWidth()/2-100,
            10,
            controller.getPlayer().getHealt()*2,
            50
        );
        sh.end();

        
        sh.begin(ShapeRenderer.ShapeType.Line);
        sh.setColor(Color.BLACK);
        sh.rect(Gdx.graphics.getWidth()/2-100, 10, 200, 50);
        sh.end();
        
             
     }
 
     private class InputListener implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            if (!GameplayScreen.msgSystem().isListeningForInput()) {
                 //toggle fullscreen
                 if (keycode == Input.Keys.F){
                     WEMain.setFullscreen(!WEMain.isFullscreen());
                 }

                 //toggle eathquake
                 if (keycode == Input.Keys.E){ //((ExplosiveBarrel)(getMapData(Chunk.getBlocksX()+5, Chunk.getBlocksY()+5, 3))).explode();
                     getMap().earthquake(5000);
                 }

                 //pause
                 //time is set 0 but the game keeps running
                   if (keycode == Input.Keys.P) {
                     getController().setTimespeed(0);
                  } 

                 //reset zoom
                 if (keycode == Input.Keys.Z) {
                     getController().getCameras().get(0).setZoom(1);
                     GameplayScreen.msgSystem().add("Zoom reset");
                  }  

                 //show/hide light engine
                 if (keycode == Input.Keys.L) {
                     if (getLightengine() != null) getLightengine().RenderData(!getLightengine().isRenderingData());
                  } 

                  if (keycode == Input.Keys.T) {
                     getController().setTimespeed();
                  } 

                 if (keycode == Input.Keys.ESCAPE)// Gdx.app.exit();
                     WEMain.getInstance().setScreen(new MainMenuScreen());
            }
            
             //toggle input for msgSystem
             if (keycode == Input.Keys.ENTER)
                 GameplayScreen.msgSystem().listenForInput(!GameplayScreen.msgSystem().isListeningForInput());

            return true;            
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            GameplayScreen.msgSystem().getInput(character);
            return true;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (controller.getCurrentWeapon() != null) {
                controller.getCurrentWeapon().trigger();
                return true;
            }
            else return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            getController().getCameras().get(0).setZoom(getController().getCameras().get(0).getZoom() - amount/100f);
            
            GameplayScreen.msgSystem().add("Zoom: " + getController().getCameras().get(0).getZoom());   
            return true;
        }
    }
}