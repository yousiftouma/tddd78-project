package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;
import com.mygdx.game.GameWindow;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.maps.GameMap;
import com.mygdx.game.maps.Map1;

import javax.swing.*;

/**
 * Renders everything for the game
 */
public class GameScreen implements Screen
{
    private static final float RED = 0.7f;
    private static final float GREEN = 0.5f;
    private static final float BLUE = 0.2f;

    private static final float VOLUME_PERCENTAGE = 0.2f;


    // we assert initialization as show() is always run when this screen is set
    private SpriteBatch batch;
    private Game gameToDraw;
    private String scoreDisplay;
    private String hpDisplay;
    private BitmapFont scoreBmf;
    private BitmapFont hpBmf;
    private static Sound dealDamageSound = Gdx.audio.newSound(Gdx.files.internal("give_damage_sound.wav"));
    private static Sound takeDamageSound = Gdx.audio.newSound(Gdx.files.internal("take_damage_sound.wav"));
    private static Sound pickUpSound = Gdx.audio.newSound(Gdx.files.internal("pickup_sound.wav"));
    private Music gameLoopMusic;
    private GameWindow window;

    /**
     * Contains the map to render
     *
     * @param mapNumber which map to render in screen, must be within range
     */
    public GameScreen(int mapNumber, GameWindow window) {
	assert (0 <= mapNumber && mapNumber <= 10); //necessary?
	this.window = window;
	GameMap mapToPlay;
	switch (mapNumber) {
	    case 1:
		mapToPlay = Map1.getInstance();
		break;
	    default:
		mapToPlay = Map1.getInstance();
	}
	this.gameToDraw = new Game(mapToPlay, this);
	this.batch = new SpriteBatch();
	this.scoreDisplay = "score: 0";
	this.hpDisplay = "HP: 0"; //just initialize
	this.scoreBmf = new BitmapFont();
	this.hpBmf = new BitmapFont();
	this.gameLoopMusic = Gdx.audio.newMusic(Gdx.files.internal("gameloop_sound.mp3"));
    }

    /**
     * called when this screen is set with setScreen
     */
    @Override public void show() {
	gameLoopMusic.setLooping(true);
	gameLoopMusic.setVolume(VOLUME_PERCENTAGE);
	gameLoopMusic.play();

    }

    /**
     * called when another screen is set with setScreen
     */
    @Override public void hide() {
	gameLoopMusic.setLooping(false);
	gameLoopMusic.pause();
    }

    /**
     * used to dispose of heavy objects
     */
    @Override public void dispose() {
	batch.dispose();
	scoreBmf.dispose();
	hpBmf.dispose();
	gameLoopMusic.dispose();
    }

    /**
     * draws libgdx objects
     *
     * @param delta is time since last update
     */
    @Override public void render(final float delta) {
	//backgroundcolor, rgba
	Gdx.gl.glClearColor(RED, GREEN, BLUE, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	scoreDisplay = "score: " + gameToDraw.getPlayer().getScore();
	hpDisplay = "HP: " + gameToDraw.getPlayer().getHitPointsLeft();

	if (!gameToDraw.isGameOver()) {
	    // begin drawing here
	    batch.begin();
	    /*for (Wall wall : gameToDraw.getObstacles()) {
		wall.draw(batch);
	    }*/
	    for (Entity object : gameToDraw.getGameObjects()) {
		object.draw(batch);
	    }
	    //gameToDraw.getPlayer().draw(batch);
	    scoreBmf.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	    scoreBmf.draw(batch, scoreDisplay, 10, Game.FRAME_HEIGHT - 10);
	    hpBmf.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	    hpBmf.draw(batch, hpDisplay, 10*10, Game.FRAME_HEIGHT - 10);
	    batch.end();
	    // stopped drawing here

	    // Updates here
	    gameToDraw.updateGame(delta);

	    // Controls here
	    gameToDraw.handleMovement(delta);
	}
	else {
	    this.pause();
	    String highscore = gameToDraw.getHighscoreManager().getHighscoreString();
	    Object[] options = {"Yes, please",
	                        "No way!"};
	    int userChoice = JOptionPane.showOptionDialog(null, highscore, "Here is the highscore, play more?",
						 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						 //do not use a custom Icon
						 options,  //the titles of buttons
						 options[0]); //default button title
	    if (userChoice == JOptionPane.YES_OPTION) {
		this.resume();
		window.setScreen(new MenuScreen(window));
	    }
	    else {
		this.dispose();
		window.dispose();
	    }
	}

    }

    @Override public void resize(final int width, final int height) {
    }

    @Override public void pause() {
	Gdx.graphics.setContinuousRendering(false);
    }

    @Override public void resume() {
	Gdx.graphics.setContinuousRendering(true);
    }

    public static Sound getDealDamageSound() {
	return dealDamageSound;
    }

    public static Sound getTakeDamageSound() {
	return takeDamageSound;
    }

    public static Sound getPickUpSound() {
	return pickUpSound;
    }
}
