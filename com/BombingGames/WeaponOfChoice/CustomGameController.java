package com.BombingGames.WeaponOfChoice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.bombinggames.wurfelengine.WE;
import com.bombinggames.wurfelengine.core.Controller;
import com.bombinggames.wurfelengine.core.Gameobjects.Block;
import com.bombinggames.wurfelengine.core.Map.Chunk;
import com.bombinggames.wurfelengine.core.Map.Coordinate;

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
	private Player player;
	private int mana = 0;
    
        
    @Override
    public void init(){
        Gdx.app.log("CustomGameController", "Initializing");
        super.init();
        
        gameOver=false;
        music = WE.getAsset("com/bombinggames/WeaponOfChoice/Sounds/music.ogg");
        music.setLooping(true);
        music.play();

        player = (Player) new Player(1, Block.GAME_EDGELENGTH)
			.spawn(new Coordinate(0, 0, 8).toPoint());
        player.setDamageSounds(new String[]{"scream1.wav", "scream2.wav",
            "scream3.wav",
            "scream4.wav"
        });
        
        
        CustomWeapon.init();
        
        roundTimer = roundLength;
        spinningWheel = new SpinningWheel(this);
        spinningWheel.add(new CustomWeapon((byte)0, null));
        spinningWheel.add(new CustomWeapon((byte)1, null));
        spinningWheel.add(new CustomWeapon((byte)2, null));
        spinningWheel.add(new CustomWeapon((byte)3, null));
        spinningWheel.add(new CustomWeapon((byte)4, null));
        spinningWheel.add(new CustomWeapon((byte)5, null));
        spinningWheel.add(new CustomWeapon((byte)6, null));
        spinningWheel.add(new CustomWeapon((byte)7, null));
        spinningWheel.spin();
        
        startingTime = System.currentTimeMillis();
        survivedSeconds = 0;
        
        //useLightEngine(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }

    
    @Override
    public void update(float dt){
		super.update(dt);
        if (!gameOver){
			float origidelta = dt / WE.CVARS.getValueF("timespeed");
            //get input and do actions
            Input input = Gdx.input;

            if (!WE.getConsole().isActive()) {

                boolean running = false;
                if (input.isKeyPressed(Input.Keys.SHIFT_LEFT) && mana > 0 && !cooldown){
                    mana = (int) (mana-dt);
                    running = true;
                    if (mana<=0) cooldown=true;
                }else {
                    mana = (int) (mana+dt/2f);
                }

                if (mana>100) cooldown=false;


                //walk
                getPlayer().walk(
                    input.isKeyPressed(Input.Keys.W),
                    input.isKeyPressed(Input.Keys.S),
                    input.isKeyPressed(Input.Keys.A),
                    input.isKeyPressed(Input.Keys.D),
                    4.25f+(running? 3.5f: 0),
					dt
                );
                if (input.isKeyPressed(Input.Keys.SPACE)) getPlayer().jump();
            }



            roundTimer -= dt;
            if (roundTimer <= 0){
                //reset
                roundTimer = roundLength;
                round++;
                WE.getConsole().add("New Round! Round: "+round, "Warning");
                spinningWheel.spin();

                //spawn enemies
                WE.getConsole().add("Spawning "+(round-1) +" enemys.", "Warning");
                for (int i = 0; i < round; i++) {
                    Coordinate randomPlace = new Coordinate(
                        (int) (Controller.getMap().getBlocksX()*Math.random()),
                        (int) (Controller.getMap().getBlocksY()*Math.random()),
						Chunk.getGameHeight()
					);
                    Enemy enemy = (Enemy) new Enemy((byte) 44).spawn(randomPlace.toPoint());
                    enemy.setTarget(getPlayer());
                }

            }
            spinningWheel.update(origidelta);

            if (getPlayer().getHealth() <= 0 && !gameOver)
                gameOver();

            if (currentWeapon != null && input.isButtonPressed(0))
                currentWeapon.shoot();

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
    
    public void equipWeapon(byte id){
        currentWeapon = new CustomWeapon(id, getPlayer());
        currentWeapon.reload();
    }

    public CustomWeapon getCurrentWeapon() {
        return currentWeapon;
    }
    
    public void gameOver(){
        gameOver = true;
        ((Sound) WE.getAsset("com/bombinggames/WeaponOfChoice/Sounds/dead.ogg")).play();
        survivedSeconds =(int) ((System.currentTimeMillis()-startingTime)/1000);
        Gdx.app.error("Game over:", "Time:"+survivedSeconds);
        
        getPlayer().dispose();
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

	public Player getPlayer() {
		return player;
	}

	public int getMana() {
		return mana;
	}
	
}
