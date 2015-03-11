package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.movableentity.player.Player;
import com.mygdx.game.entity.obstacle.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the rendering of the game
 */
public class Game extends ApplicationAdapter
{
    private SpriteBatch batch;
    private Player player;
    private Texture wallTexture;
    private Sprite wallSprite;
    private Texture playerTestTexture;
    private List<Wall> walls;

    /**
     * height of game window
     */
    public static final int FRAME_HEIGHT = 480;
    /**
     * width of game window
     */
    public static final int FRAME_WIDTH = 640;
    /**
     * the thickness of a platform
     */
    public static final int PLATFORM_THICKNESS = 10;

    /**
     * Simulates normal gravity in gameworld
     */
    public static final float NORMAL_GRAVITY = 9.82f * 100;

    /**
     * Default speed
     */
    public static final int DEFAULT_VELOCITY_X = 200;

    /**
     * Runs upon starting game, like a main method, creates all content
     */
    @Override public void create() {
	createTextures();
	batch = new SpriteBatch();
	createGameObjects();
    }

    private void createGameObjects() {
	walls = new ArrayList<Wall>();
	walls.add(new Wall(new Sprite(wallTexture), new Vector2(0, 0), new Vector2(FRAME_WIDTH / 3.0f, PLATFORM_THICKNESS)));
	walls.add(new Wall(new Sprite(wallTexture), new Vector2(2 * (FRAME_WIDTH / 3.0f), 0),
			   new Vector2(FRAME_WIDTH / 3.0f, PLATFORM_THICKNESS)));
	walls.add(new Wall(new Sprite(wallTexture), new Vector2((FRAME_WIDTH / 5.0f), 2*FRAME_HEIGHT / 6.0f),
			   new Vector2(FRAME_WIDTH / 5.0f, PLATFORM_THICKNESS)));
	walls.add(new Wall(new Sprite(wallTexture), new Vector2((3 * FRAME_WIDTH / 5.0f), 2*FRAME_HEIGHT / 6.0f),
			   new Vector2(FRAME_WIDTH / 5.0f, PLATFORM_THICKNESS)));
	player = new Player(new Sprite(playerTestTexture), new Vector2(100, 300), new Vector2(64, 64),
			    new Vector2(DEFAULT_VELOCITY_X, 0), new Vector2(0, NORMAL_GRAVITY));
    }

    private void createTextures() {
	wallTexture = new Texture(Gdx.files.internal("truck_material-new-256.png"));
	playerTestTexture = new Texture(Gdx.files.internal("playertestbox.png"));
    }

    /**
     * Closes the program when window is closed
     */
    @Override public void dispose() {
	super.dispose();
    }

    /**
     * Renders everything, like a component
     */
    @Override public void render() {
	//backgroundcolor, rgba
	Gdx.gl.glClearColor(0.7F, 0.5F, 0.2F, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	// begin drawing here
	batch.begin();
	for (Wall wall : walls) {
	    wall.draw(batch);
	}
	player.draw(batch);
	batch.end();
	// stopped drawing here

	// Updates here
	player.update(Gdx.graphics.getDeltaTime());
	for (Wall wall : walls) {
	    if (player.hasCollision(wall)) {
		player.doAction(GameObject.WALL, wall);
	    }
	}

	// Controls here
	if (Gdx.input.isKeyPressed(Keys.LEFT)) {
	    player.moveLeft(Gdx.graphics.getDeltaTime());
	}
	if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
	    player.moveRight(Gdx.graphics.getDeltaTime());
	}
	//isKeyJustPressed means when button i pressed, not while button is pressed
	if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.SPACE)) {
	    player.jump();
	}
    }
}
