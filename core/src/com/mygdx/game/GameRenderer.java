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
import com.mygdx.game.entity.movableentity.MovableEntity;
import com.mygdx.game.entity.movableentity.player.Player;
import com.mygdx.game.entity.obstacle.Wall;

/**
 * Handles the rendering of the game
 */
public class GameRenderer extends ApplicationAdapter {
    private SpriteBatch batch;

    /**
     * Size of player, (width, height)
     */
    public static final Vector2 PLAYER_SIZE = new Vector2(64,64);

    /**
     * Initial player position, (x,y)
     */
    public static final Vector2 PLAYER_SPAWN_POINT = new Vector2(100, 300);


    /**
     * Runs upon starting launcher, creates all content
     */
    @Override
    public void create() {
        createTextures();
        batch = new SpriteBatch();
        createGameObjects();
    }

    private void createGameObjects() {
        player = new Player(new Sprite(playerTestTexture), PLAYER_SPAWN_POINT, PLAYER_SIZE,
                new Vector2(MovableEntity.getDefaultVelocityX(), 0), new Vector2(0, NORMAL_GRAVITY), 5, 5);
        /*
        enemy = new Enemy(new Sprite(enemyTestTexture), ENEMY_SPAWN_POINT, ENEMY_SIZE,
                new Vector2(DEFAULT_VELOCITY_X, 0), new Vector2(0, NORMAL_GRAVITY), false);*/

    }

    /**
     * Closes the program when window is closed
     */
    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Renders everything, like a component
     */
    @Override
    public void render() {
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
