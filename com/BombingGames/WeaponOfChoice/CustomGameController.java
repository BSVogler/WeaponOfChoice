package com.BombingGames.WeaponOfChoice;

import com.BombingGames.WurfelEngine.Core.Controller;
import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractCharacter;
import com.BombingGames.WurfelEngine.Core.Gameobjects.AbstractEntity;
import com.BombingGames.WurfelEngine.Core.GameplayScreen;
import com.BombingGames.WurfelEngine.Core.Map.Coordinate;
import com.BombingGames.WurfelEngine.Core.Map.Map;
import com.BombingGames.WurfelEngine.Core.WECamera;
import com.BombingGames.WurfelEngine.WE;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 *The <i>CustomGameController</i> is for the game code. Put engine code into <i>Controller</i>.
 * @author Benedikt
 */
public class CustomGameController extends Controller {
    private SpinningWheel spinningWheel;
    private int round = 1;
    private final int roundLength = 15000;
    private int roundTimer;
    private CustomWeapon currentWeapon;
    private boolean gameOver;
    private boolean cooldown = false;
    private Music music;
    private long startingTime;
    private int survivedSeconds;
    
        
    @Override
    public void init(){
        Gdx.app.log("CustomGameController", "Initializing");
        super.init();
        
        gameOver=false;
        music = WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/music.ogg");
        music.setLooping(true);
        music.play();

        
        AbstractCharacter player = (AbstractCharacter) AbstractEntity.getInstance(
                30,
                0,
                Map.getCenter(Map.getGameHeight())
        );
        player.setControls("WASD");
        setPlayer(player);
        player.setDamageSounds(new Sound[]{
            (Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/scream1.wav"),
            (Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/scream2.wav"),
            (Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/scream3.wav"),
            (Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/scream4.wav")
        });
        
        addCamera(
            new WECamera(
                getPlayer(),
                0, //left
                0, //top
                Gdx.graphics.getWidth(), //width 
                Gdx.graphics.getHeight()//height
            )
        );
        CustomWeapon.init();
        CustomBullet.init();
        
        roundTimer = roundLength;
        spinningWheel = new SpinningWheel(this);
        spinningWheel.add(new CustomWeapon(0, null));
        spinningWheel.add(new CustomWeapon(1, null));
        spinningWheel.add(new CustomWeapon(2, null));
        spinningWheel.add(new CustomWeapon(3, null));
        spinningWheel.add(new CustomWeapon(4, null));
        spinningWheel.add(new CustomWeapon(5, null));
        spinningWheel.add(new CustomWeapon(6, null));
        spinningWheel.add(new CustomWeapon(7, null));
        spinningWheel.spin();
        
        startingTime = System.currentTimeMillis();
        survivedSeconds = 0;
        
        //useLightEngine(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }

    
    @Override
    public void update(float delta){
        if (!gameOver){
            float origidelta = delta;
            delta *= getTimespeed();

            //get input and do actions
            Input input = Gdx.input;

            if (!GameplayScreen.msgSystem().isListeningForInput()) {

                boolean running = false;
                if (input.isKeyPressed(Input.Keys.SHIFT_LEFT) && getPlayer().getMana()>0 &&!cooldown){
                    getPlayer().setMana((int) (getPlayer().getMana()-delta));
                    running = true;
                    if (getPlayer().getMana()<=0) cooldown=true;
                }else {
                    getPlayer().setMana((int) (getPlayer().getMana()+delta/2f));
                }

                if (getPlayer().getMana()>100) cooldown=false;


                //walk
                if ("WASD".equals(getPlayer().getControls()))
                    getPlayer().walk(
                        input.isKeyPressed(Input.Keys.W),
                        input.isKeyPressed(Input.Keys.S),
                        input.isKeyPressed(Input.Keys.A),
                        input.isKeyPressed(Input.Keys.D),
                        .25f+(running? 0.5f: 0)
                    );
                if (input.isKeyPressed(Input.Keys.SPACE)) getPlayer().jump();
            }



            roundTimer -= delta;
            if (roundTimer <= 0){
                //reset
                roundTimer = roundLength;
                round++;
                GameplayScreen.msgSystem().add("New Round! Round: "+round, "Warning");
                spinningWheel.spin();

                //spawn an enemy
                GameplayScreen.msgSystem().add("Spawning "+(round-1) +" enemys.", "Warning");
                for (int i = 0; i < round; i++) {
                    Coordinate randomPlace = new Coordinate(
                        (int) (Map.getBlocksX()*Math.random()),
                        (int) (Map.getBlocksY()*Math.random()),
                        (float) Map.getGameHeight(),
                        true);
                    Enemy enemy = (Enemy) AbstractCharacter.getInstance(40, 0,randomPlace.getPoint());
                    enemy.setTarget(getPlayer());
                    enemy.exist();
                }

            }
            spinningWheel.update(origidelta);

            if (getPlayer().getHealt() <= 0 && !gameOver)
                gameOver();

            if (currentWeapon != null)
                currentWeapon.update(input.isButtonPressed(0), delta);

            super.update(origidelta);
        }else {
            music.stop();
        }
    }

    /**
     * @return the spinningWheel
     */
    public SpinningWheel getSpinningWheel() {
        return spinningWheel;
    }
    
    public void equipWeapon(int id){
        currentWeapon = new CustomWeapon(id, getPlayer());
        currentWeapon.reload();
    }

    public CustomWeapon getCurrentWeapon() {
        return currentWeapon;
    }
    
    public void gameOver(){
        gameOver = true;
        ((Sound) WE.getAsset("com/BombingGames/WeaponOfChoice/Sounds/dead.ogg")).play();
        survivedSeconds =(int) ((System.currentTimeMillis()-startingTime)/1000);
        Gdx.app.error("Game over:", "Time:"+survivedSeconds);
        
        getPlayer().destroy();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Music getMusic() {
        return music;
    }

    public int getRound() {
        return round;
    }

    public int getSurvivedSeconds() {
        return survivedSeconds;
    }
}
