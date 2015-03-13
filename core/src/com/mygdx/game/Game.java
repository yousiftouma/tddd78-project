package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.movableentity.player.Player;
import com.mygdx.game.entity.movableentity.player.PlayerMaker;
import com.mygdx.game.entity.obstacle.Wall;
import com.mygdx.game.maps.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game, using GameRenderer to draw it up
 */
public class Game
{
    private List<Entity> gameObjects;
    private List<Vector2> coinSpawnPoints;
    private List<Vector2> enemySpawnPoints;
    private Vector2 playerSpawnPoint;
    private GameMap map;
    private Player player;

    private final static int NORMAL_GRAVITY = 982;

    /**
     * height of game window, should be accessible to anyone who wants it
     */
    public static final int FRAME_HEIGHT = 480;
    /**
     * width of game window, should be accessible to anyone who wants it
     */
    public static final int FRAME_WIDTH = 640;


    public Game(GameMap map) {
	this.gameObjects = new ArrayList<>();
	this.map = map;
	this.coinSpawnPoints = map.getCoinSpawnPoints();
	this.enemySpawnPoints = map.getEnemySpawnPoints();
	this.playerSpawnPoint = map.getPlayerSpawnPoint();
	fetchMap();
	this.player = createPlayer();
	gameObjects.add(player);
	player.setPosition(playerSpawnPoint);
    }

    public void updateGame() {
	player.update(Gdx.graphics.getDeltaTime());
	for (Entity object : gameObjects) {
	    if (object.getGameObjectType() == GameObject.WALL) {
		if (player.hasCollision((CollisionEntity) object)) {
		    player.doAction(GameObject.WALL, (CollisionEntity) object);
		}
	    }
	}
    }

    public void handleMovement() {
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

    public Player createPlayer() {
	return new PlayerMaker().createPlayer();
    }

    public void fetchMap() {
	for (Wall wall : map.getWalls()) {
	    gameObjects.add(wall);
	}
    }

    public static int getGravity() {
	final int gravity = NORMAL_GRAVITY;
	return gravity;
    }

    public Iterable<Entity> getGameObjects() {
	return gameObjects;
    }
}
