package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
import com.mygdx.game.maps.Map2;

import javax.swing.*;

/**
 * Class to draw everything, implements the libgdx screen interface which has
 * some methods seen below
 */
public class GameScreen implements Screen
{
    /**
     * libgdx class SpriteBatch that buffers several sprites to be sent to the graphicscard for rendering
     * simultaneously instead of one by one
     */
    private SpriteBatch batch;
    private Game gameToDraw;
    private String scoreDisplay;
    private String hpDisplay;
    /**
     * libgdx class to display text as bitmaps
     */
    private BitmapFont scoreBmf;
    private BitmapFont hpBmf;
    private BitmapFont quitBmf;
    /**
     *  We think this is the only way to have the sound accessible to whoever needs it (the concrete entities
     *  that needs to play sounds on certain actions)
     *  The problem is that Gdx.audio is non final static
     *  but this is a libgdx class so we don't think we can handle this in any other way
     *  Also, Sound is a libgdx class that handles sound with simple play, pause and similar methods
     */
    private static Sound dealDamageSound = Gdx.audio.newSound(Gdx.files.internal("give_damage_sound.wav"));
    private static Sound takeDamageSound = Gdx.audio.newSound(Gdx.files.internal("take_damage_sound.wav"));
    private static Sound pickUpSound = Gdx.audio.newSound(Gdx.files.internal("pickup_sound.wav"));
    /**
     * Music is a libgdx class to handle longer sounds, in our case backgroundmusic
     */
    private Music gameLoopMusic;
    private GameWindow window;

    private static final float RED = 0.7f;
    private static final float GREEN = 0.5f;
    private static final float BLUE = 0.2f;

    private static final float VOLUME_PERCENTAGE = 0.2f;

    private static final float TWO_FIFTHS = 2/5.0f;
    private static final float ONE_TENTH = 1/10.0f;
    private static final float THREE_FOURTHS = 3/4.0f;
    private static final float MARGIN = 10.0f;

    /**
     * Constructor of the gamescreen
     * @param mapNumber which map to render in screen, this method needs to contain a case for
     *                  each map
     * @param window the window the screen is set in
     */
    public GameScreen(int mapNumber, GameWindow window) {
	this.window = window;
	GameMap mapToPlay = null;
	switch (mapNumber) {
	    case 1:
		mapToPlay = Map1.getInstance();
		break;
	    case 2:
		mapToPlay = Map2.getInstance();
		break;
	}
	assert (mapToPlay != null): "Map to play was null when trying to get instance!";
	this.gameToDraw = new Game(mapToPlay);
	this.batch = new SpriteBatch();
	this.scoreDisplay = "score: 0";
	// 0 for HP just to initialize
	this.hpDisplay = "HP: 0";
	this.quitBmf = new BitmapFont();
	this.scoreBmf = new BitmapFont();
	this.hpBmf = new BitmapFont();
	this.gameLoopMusic = Gdx.audio.newMusic(Gdx.files.internal("gameloop_sound.mp3"));
    }

    /**
     * called when this screen is set with setScreen
     * interfacemethod
     */
    @Override public void show() {
	gameLoopMusic.setLooping(true);
	gameLoopMusic.setVolume(VOLUME_PERCENTAGE);
	gameLoopMusic.play();

    }

    /**
     * called when another screen is set with setScreen
     * interfacemethod
     */
    @Override public void hide() {
	gameLoopMusic.setLooping(false);
	gameLoopMusic.pause();
    }

    /**
     * used to dispose of heavy objects like a spritebatch or other mediaobjects
     * interfacemethod
     */
    @Override public void dispose() {
	batch.dispose();
	scoreBmf.dispose();
	hpBmf.dispose();
	gameLoopMusic.dispose();
    }

    /**
     * interfacemethod
     * draws everything in the gamewindow, called as fast as the computer can handle rendering
     * @param delta is time since last update
     */
    @Override public void render(final float delta) {
	//backgroundcolor, rgba
	Gdx.gl.glClearColor(RED, GREEN, BLUE, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	scoreDisplay = "score: " + gameToDraw.getPlayer().getScore();
	hpDisplay = "HP: " + gameToDraw.getPlayer().getHitPointsLeft();

	if (!gameToDraw.isGameOver()) {
	    // create a spritebatch to send to graphics card for drawing
	    batch.begin();

	    for (Entity object : gameToDraw.getGameObjects()) {
		object.draw(batch);
	    }
	    //sets white color to all bitmapfonts and draws them
	    scoreBmf.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	    scoreBmf.draw(batch, scoreDisplay, Game.FRAME_WIDTH * ONE_TENTH, Game.FRAME_HEIGHT - MARGIN);
	    hpBmf.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	    hpBmf.draw(batch, hpDisplay, Game.FRAME_WIDTH * TWO_FIFTHS, Game.FRAME_HEIGHT - MARGIN);
	    quitBmf.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	    quitBmf.draw(batch, "Quit: CTRL-Q", Game.FRAME_WIDTH * THREE_FOURTHS, Game.FRAME_HEIGHT - MARGIN);

	    batch.end();
	    // sent the spritebatch

	    // Updates here
	    gameToDraw.updateGame(delta);

	    // Controls here
	    gameToDraw.handleMovement(delta);
	    checkForOtherKeyInputs();
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

    /**
     * checks if user presses CTRL-Q to quit
     */
    private void checkForOtherKeyInputs() {
	if (Gdx.input.isKeyPressed(Keys.Q)) {
	    if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
		this.dispose();
		window.dispose();
	    }
	}
    }

    /**
     * interfacemethod, more used for mobile apps
     */
    @Override public void resize(final int width, final int height) {
    }

    /**
     * interfacemethod, also mostly used for mobile applications but used in our case to stop
     * rendering when game over
     */
    @Override public void pause() {
	Gdx.graphics.setContinuousRendering(false);
    }

    /**
     * interfacemethod, also mostly used for mobile apps, but used here to resume rendering
     */
    @Override public void resume() {
	Gdx.graphics.setContinuousRendering(true);
    }

    public static Sound getPickUpSound(){
	return pickUpSound;
    }

    public static Sound getDealDamageSound() {
	return dealDamageSound;
    }

    public static Sound getTakeDamageSound() {
	return takeDamageSound;
    }
}
