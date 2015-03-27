package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.movableentity.MovableEntity;
import com.mygdx.game.entity.movableentity.coins.CoinFactory;
import com.mygdx.game.entity.movableentity.coins.SmallMovingCoin;
import com.mygdx.game.entity.movableentity.coins.SmallStaticCoin;
import com.mygdx.game.entity.movableentity.enemy.EnemyFactory;
import com.mygdx.game.entity.movableentity.player.Player;
import com.mygdx.game.entity.movableentity.player.PlayerMaker;
import com.mygdx.game.entity.movableentity.player.powerup.NormalState;
import com.mygdx.game.entity.movableentity.player.powerup.PoweredUpState;
import com.mygdx.game.entity.movableentity.powerups.AbstractPowerUp;
import com.mygdx.game.entity.movableentity.powerups.NormalStaticPowerUp;
import com.mygdx.game.entity.movableentity.powerups.PowerUpFactory;
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


    private static final int MAX_PLAYER_HP = 20;
    private static final int PLAYER_DMG = 10;
    private static final int PLAYER_WIDTH = 48;
    private static final int PLAYER_HEIGHT = 64;
    private final static int NORMAL_GRAVITY = 982;
    private final static int ENEMY_RESPAWN_TIME = 5;


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
	this.playerMaker = new PlayerMaker(PLAYER_DMG, MAX_PLAYER_HP, PLAYER_WIDTH, PLAYER_HEIGHT);
	fetchMapObstacles();
	createPlayer();
    }

    public void updateGame() {
	for (Entity object : gameObjects) {
	    if (object instanceof MovableEntity) {
		((MovableEntity) object).update(Gdx.graphics.getDeltaTime());
		for (Entity otherObject : gameObjects) {
		    if (otherObject instanceof CollisionEntity) {
			if (object != otherObject) {
			    if (((MovableEntity) object).hasCollision((CollisionEntity) otherObject)) {
				((MovableEntity) object)
					.doAction(otherObject.getGameObjectType(), (CollisionEntity) otherObject);
			    }
			}
		    }
		}
		if (((MovableEntity) object).getHitPointsLeft() <= 0) {
		    onObjectDeath(object.getGameObjectType(), object);
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
	//isKeyJustPressed means moment when button is pressed, not while button is pressed
	if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.SPACE)) {
	    player.jump();
	}
    }

    public void onObjectDeath(GameObject type, Entity object){
	gameObjects.remove(object);
	switch (type) {
	    case WALL:
		//this will never occur as wall cannot die
		break;
	    case ENEMY:
	    	//nothing happens to player
		break;
	    case PLAYER:
		//handle game over
		break;
	    case SMALL_STATIC_COIN:
		final SmallStaticCoin smallStaticCoin = (SmallStaticCoin) object;
		addScore(smallStaticCoin.getValue());
		break;
	    case SMALL_MOVING_COIN:
		final SmallMovingCoin smallMovingCoin = (SmallMovingCoin) object;
		addScore(smallMovingCoin.getValue());
		break;
	    case NORMAL_STATIC_POWER_UP:
		player.setpState(new PoweredUpState());
		player.setPowerUpTimer(((AbstractPowerUp) object).getPowerUpTime());
		break;
	}

    }

    public void addScore(final int points) {
	player.setScore(player.getScore() + points);
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
	    collisionGameObjects.add(wall);
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
