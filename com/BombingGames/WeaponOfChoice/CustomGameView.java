package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Controller;
import com.BombingGames.WurfelEngine.Core.Gameobjects.PlayerWithWeapon;
import com.BombingGames.WurfelEngine.Core.GameplayScreen;
import com.BombingGames.WurfelEngine.Core.View;
import com.BombingGames.WurfelEngine.Core.Camera;
import com.BombingGames.WurfelEngine.WE;
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
         this.controller = controller;
     }

    @Override
    public void init(Controller controller) {
        super.init(controller);
        View.addInputProcessor(new InputListener());
         Camera camera = new Camera(
                controller.getPlayer(),
                0, //left
                0, //top
                Gdx.graphics.getWidth(), //width 
                Gdx.graphics.getHeight()//height
            );
        
        addCamera(camera);
        ((PlayerWithWeapon) controller.getPlayer()).setCamera(camera);
    }
    
    


     @Override
     public void render(){
         super.render();
         
         getBatch().begin();
         controller.getSpinningWheel().render(this);
         getBatch().end();
         CustomWeapon weapon = controller.getCurrentWeapon();
         if (weapon != null)
            drawString("Shots: "+weapon.getShotsLoaded()+"/"+weapon.getShots(), Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-100, Color.WHITE.cpy());
         
        ShapeRenderer sh = getShapeRenderer();
        //health
        sh.begin(ShapeRenderer.ShapeType.Filled);
        sh.setColor(
            new Color(
                1-(controller.getPlayer().getHealt()/1000f),
                controller.getPlayer().getHealt()/1000f,
                0,
                1
            )
        );
        sh.rect(
            Gdx.graphics.getWidth()/2-100,
            10,
            controller.getPlayer().getHealt()/10*2,
            50
        );
        sh.end();

        
        sh.begin(ShapeRenderer.ShapeType.Line);
        sh.setColor(Color.BLACK);
        sh.rect(Gdx.graphics.getWidth()/2-100, 10, 200, 50);
        sh.end();
        
        //mana
        sh.begin(ShapeRenderer.ShapeType.Filled);
        sh.setColor(
            new Color(
                0,
                0,
                1,
                1
            )
        );
        sh.rect(
            Gdx.graphics.getWidth()/2-100,
            64,
            controller.getPlayer().getMana()/10*2,
            10
        );
        sh.end();

        
        sh.begin(ShapeRenderer.ShapeType.Line);
        sh.setColor(Color.BLACK);
        sh.rect(Gdx.graphics.getWidth()/2-100, 10, 200, 50);
        sh.end();
        
        if (controller.isGameOver()){
            drawString(
               "Game Over",
                Gdx.graphics.getWidth()/2-30,
                Gdx.graphics.getHeight()/2-170
            );  
        
            drawString(
                "Kills:"+Enemy.getKillcounter(),
                Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()/2
            );
            drawString(
                "You survived "+controller.getSurvivedSeconds()+" seconds.",
                Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()/2+20
            ); 
        }
     }
 
     private class InputListener implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            if (!GameplayScreen.msgSystem().isActive()) {
                 //toggle fullscreen
                 if (keycode == Input.Keys.F){
                     WE.setFullscreen(!WE.isFullscreen());
                 }

                //reload
                 if (keycode == Input.Keys.R) {
                     controller.getCurrentWeapon().reload();
                  }  

                 //reset zoom
                 if (keycode == Input.Keys.Z) {
                     getCameras().get(0).setZoom(1);
                     GameplayScreen.msgSystem().add("Zoom reset");
                  }  

   
                 if (keycode == Input.Keys.ESCAPE)// Gdx.app.exit();
                     WE.showMainMenu();
            }
            
             //toggle input for msgSystem
             if (keycode == Input.Keys.ENTER)
                 GameplayScreen.msgSystem().setActive(!GameplayScreen.msgSystem().isActive());

            return true;            
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (controller.getCurrentWeapon() != null) {
                controller.getCurrentWeapon().trigger();
                return true;
            }
            else
                return false;
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
            getCameras().get(0).setZoom(getCameras().get(0).getZoom() - amount/100f);
            
            GameplayScreen.msgSystem().add("Zoom: " + getCameras().get(0).getZoom());   
            return true;
        }
    }
}