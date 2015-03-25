package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.movableentity.MovableEntity;
import com.mygdx.game.entity.movableentity.coins.CoinFactory;
import com.mygdx.game.entity.movableentity.enemy.EnemyFactory;
import com.mygdx.game.entity.movableentity.player.Player;
import com.mygdx.game.entity.movableentity.player.PlayerMaker;
import com.mygdx.game.entity.obstacle.Wall;
import com.mygdx.game.entity.powerups.PowerUpFactory;
import com.mygdx.game.maps.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the game, using GameRenderer to draw it up
 */
public class Game
{
    private List<Entity> gameObjects;
    private List<CoinFactory> coinFactories;
    private List<EnemyFactory> enemyFactories;
    private List<PowerUpFactory> powerUpFactories;
    private Vector2 playerSpawnPoint;
    private GameMap map;
    // player is initialized but through a different method
    private Player player;
    private PlayerMaker playerMaker;

    private final static int NORMAL_GRAVITY = 982;
    private final static int ENEMY_SPAWN_TIMER = 5;
    private final static int COIN_SPAWN_TIMER = 5;
    private final static int POWER_UP_SPAWN_TIMER = 5;

    /**
     * Title of the game
     */
    public static final String TITLE = "The Game Title";

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
	this.playerSpawnPoint = map.getPlayerSpawnPoint();
	this.coinFactories = map.getCoinFactories();
	this.enemyFactories = map.getEnemyFactories();
	this.powerUpFactories = map.getPowerUpFactories();
	this.playerMaker = new PlayerMaker();
	fetchMapObstacles();
	createPlayer();
    }

    public void updateGame() {
	player.update(Gdx.graphics.getDeltaTime());
	for (Entity object : gameObjects) { // should seperate objects that can collide from other entitys that should just be drawn (background for example)
	    if (player.hasCollision((CollisionEntity) object)) {
		player.doAction(object.getGameObjectType(), (CollisionEntity) object);
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
	//isKeyJustPressed means moment when button is pressed, not while button is pressed
	if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.SPACE)) {
	    player.jump();
	}
    }

    public void createPlayer() {
	this.player = playerMaker.createPlayer();
	gameObjects.add(player);
	player.setPosition(playerSpawnPoint);
    }

    public void fetchMapObstacles() {
	gameObjects.addAll(map.getWalls().stream().collect(Collectors.toList()));
    }

    /*
        public void fetchMapObstacles() {
	for (Wall wall : map.getWalls()) {
	    gameObjects.add(wall);
	}
    }

     */

    public static int getGravity() {
	final int gravity = NORMAL_GRAVITY;
	return gravity;
    }

    public Iterable<Entity> getGameObjects() {
	return gameObjects;
    }
}
