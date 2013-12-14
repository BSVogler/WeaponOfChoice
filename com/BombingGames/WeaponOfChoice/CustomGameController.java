package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Controller;
import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractCharacter;
import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractEntity;
import com.BombingGames.WurfelEngine.Core.GameplayScreen;
import com.BombingGames.WurfelEngine.Core.Map.Chunk;
import com.BombingGames.WurfelEngine.Core.Map.Coordinate;
import com.BombingGames.WurfelEngine.Core.Map.Map;
import com.BombingGames.WurfelEngine.Core.WECamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 *The <i>CustomGameController</i> is for the game code. Put engine code into <i>Controller</i>.
 * @author Benedikt
 */
public class CustomGameController extends Controller {
    private SpinningWheel spinningWheel;
    private int round = 1;
    private final int roundLength = 10000;
    private int roundTimer;
    private Weapon currentWeapon;
    
        
    @Override
    public void init(){
        Gdx.app.log("CustomGameController", "Initializing");
         Chunk.setGenerator(2);
         super.init();

         AbstractCharacter player = (AbstractCharacter) AbstractEntity.getInstance(
                40,
                0,
                Map.getCenter(Map.getGameHeight())
        );
        player.setControls("WASD");
        setPlayer(player);
        
        addCamera(
            new WECamera(
                getPlayer(),
                0, //left
                0, //top
                Gdx.graphics.getWidth(), //width 
                Gdx.graphics.getHeight()//height
            )
        );
        Weapon.init();
        
        roundTimer = roundLength;
        spinningWheel = new SpinningWheel(this);
        spinningWheel.add(new Weapon(0, null));
        spinningWheel.add(new Weapon(1, null));
        spinningWheel.add(new Weapon(2, null));
        spinningWheel.add(new Weapon(3, null));
        spinningWheel.add(new Weapon(4, null));
        spinningWheel.spin();
        
        //useLightEngine(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }

    
    @Override
    public void update(float delta){
        //get input and do actions
        Input input = Gdx.input;
        
        if (!GameplayScreen.msgSystem().isListeningForInput()) {

            //walk
            if (getPlayer() != null){
                if ("WASD".equals(getPlayer().getControls()))
                    getPlayer().walk(
                        input.isKeyPressed(Input.Keys.W),
                        input.isKeyPressed(Input.Keys.S),
                        input.isKeyPressed(Input.Keys.A),
                        input.isKeyPressed(Input.Keys.D),
                        .25f+(input.isKeyPressed(Input.Keys.SHIFT_LEFT)? 0.75f: 0)
                    );
                if (input.isKeyPressed(Input.Keys.SPACE)) getPlayer().jump();
            } else {
                //update camera position
                WECamera camera = getCameras().get(0);
                camera.setOutputPosY( camera.getOutputPosY()
                    - (input.isKeyPressed(Input.Keys.W)? 3: 0)
                    + (input.isKeyPressed(Input.Keys.S)? 3: 0)
                );
                camera.setOutputPosX( camera.getOutputPosX()
                    + (input.isKeyPressed(Input.Keys.D)? 3: 0)
                    - (input.isKeyPressed(Input.Keys.A)? 3: 0)
                );
            }
        }
        
        
        roundTimer -= delta;
        if (roundTimer <= 0){
            //reset
            roundTimer = roundLength;
            round++;
            GameplayScreen.msgSystem().add("New Round! Round:"+round, "Warning");
            spinningWheel.spin();
            
            //spawn an enemy
            for (int i = 0; i < round; i++) {
                Coordinate randomPlace = new Coordinate(
                    (int) (Map.getBlocksX()*Math.random()),
                    (int) (Map.getBlocksY()*Math.random()),
                    Map.getGameHeight(),
                    true);
                Enemy enemy = (Enemy) AbstractCharacter.getInstance(14, 0,randomPlace.getPoint());
                enemy.setTarget(getPlayer());
                enemy.exist();
            }

        }
        spinningWheel.update(delta);
        
        currentWeapon.update(input.isButtonPressed(0), delta);
        

        super.update(delta);
    }

    /**
     * @return the spinningWheel
     */
    public SpinningWheel getSpinningWheel() {
        return spinningWheel;
    }
    
    public void equipWeapon(int id){
        currentWeapon = new Weapon(id, getPlayer());
        currentWeapon.reload();
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }
}
