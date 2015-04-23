package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.movableentity.MovableEntity;
import com.mygdx.game.entity.movableentity.coins.AbstractCoin;
import com.mygdx.game.entity.movableentity.coins.CoinFactory;
import com.mygdx.game.entity.movableentity.coins.SmallMovingCoin;
import com.mygdx.game.entity.movableentity.coins.SmallStaticCoin;
import com.mygdx.game.entity.movableentity.enemy.AbstractEnemy;
import com.mygdx.game.entity.movableentity.enemy.EnemyFactory;
import com.mygdx.game.entity.movableentity.player.Player;
import com.mygdx.game.entity.movableentity.player.PlayerMaker;
import com.mygdx.game.entity.movableentity.player.powerup.PoweredUpState;
import com.mygdx.game.entity.movableentity.powerups.AbstractPowerUp;
import com.mygdx.game.entity.movableentity.powerups.PowerUpFactory;
import com.mygdx.game.highscore.HighscoreManager;
import com.mygdx.game.maps.GameMap;
import com.mygdx.game.screens.GameScreen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Represents the game, using GameScreen to draw it up
 */
public class Game
{
    /**
     * Maximal time to be used for gameupdate, used to avoid visual bugs when deltatime from GameScreen is faulty.
     */
    public static final float MAX_DELTA_TIME = 0.1f;
    private List<Entity> gameObjects;
    //private List<Wall> obstacles;
    private List<MovableEntity> objectsToRemove;
    private List<CoinFactory> coinFactories;
    private List<EnemyFactory> enemyFactories;
    private List<PowerUpFactory> powerUpFactories;
    private Random getRandomFactory = new Random();
    private Vector2 playerSpawnPoint;
    private GameMap map;
    private GameScreen gameScreen;
    // player is initialized but through a different method
    private Player player;
    private PlayerMaker playerMaker;

    private float enemySpawnTimer;
    private float powerUpSpawnTimer;

    private HighscoreManager highscoreManager;
    private boolean gameOver;

    private static final int MAX_PLAYER_HP = 20;
    private static final int PLAYER_DMG = 10;
    private static final int PLAYER_WIDTH = 64;
    private static final int PLAYER_HEIGHT = 64;
    private final static int NORMAL_GRAVITY = 982;
    private final static int ENEMY_RESPAWN_TIME = 3;
    private final static int POWER_UP_RESPAWN_TIME = 30;

    private float timePassed = 0;


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


    public Game(GameMap map, GameScreen gameScreen) {
	this.gameObjects = new ArrayList<>();
	//this.obstacles = new ArrayList<>();
	this.objectsToRemove = new ArrayList<>();
	this.map = map;
	this.gameScreen = gameScreen;
	this.enemySpawnTimer = 0;
	this.powerUpSpawnTimer = POWER_UP_RESPAWN_TIME;
	this.playerSpawnPoint = map.getPlayerSpawnPoint();
	this.coinFactories = map.getCoinFactories();
	this.enemyFactories = map.getEnemyFactories();
	this.powerUpFactories = map.getPowerUpFactories();
	this.playerMaker = new PlayerMaker(PLAYER_DMG, MAX_PLAYER_HP, PLAYER_WIDTH, PLAYER_HEIGHT);
	this.highscoreManager = new HighscoreManager();
	this.gameOver = false;
	fetchMapObstacles();
	createPlayer();
    }


    public void updateGame(float delta) {
	if (delta >= MAX_DELTA_TIME) {
	    delta = MAX_DELTA_TIME;
	}
	timePassed += delta; //debug tool
	spawnEnemy(delta);
	spawnCoin();
	spawnPowerUp(delta);
	for (Entity object : gameObjects) {
	    if (object instanceof Player) {
		((Player) object).update(delta);
	    }
	    if (object instanceof MovableEntity) {
		// this is correct, we want it to NOT be the OBJECT player
		if (object != player) {
		    ((MovableEntity) object).update(delta);
		}
		for (Entity otherObject : gameObjects) {
		    if (otherObject instanceof CollisionEntity) {
			if (((MovableEntity) object).hasCollision((CollisionEntity) otherObject)) {
			    ((MovableEntity) object).doAction(otherObject.getGameObjectType(), (CollisionEntity) otherObject);
			}
		    }
		}
		if (((MovableEntity) object).getHitPointsLeft() <= 0) {
		    onObjectDeath(object.getGameObjectType(), (MovableEntity) object);
		}
	    }
	}
	gameObjects.removeAll(objectsToRemove);
	objectsToRemove.clear();
    }


    /*public void updateGame(float delta) {
	if (delta >= MAX_DELTA_TIME) {
	    delta = MAX_DELTA_TIME;
	}

	timePassed += delta; //debug tool
	spawnEnemy(delta);
	doPlayerUpdate(delta);
	updateMovableObjects(delta);
	checkForDeaths();
    }*/

    /*private void updateMovableObjects(final float delta) {
	for (MovableEntity object : gameObjects) {
	    object.update(delta);
	    if (player.hasCollision(object)) {
		player.doAction(object.getGameObjectType(), object);
	    }
	    obstacles.stream().filter(object::hasCollision).forEach(wall -> object.doAction(wall.getGameObjectType(), wall));
	    gameObjects.stream().filter(object::hasCollision)
		    .forEach(otherObject -> object.doAction(otherObject.getGameObjectType(), otherObject));
	}
    }*/

   /* private void checkForDeaths() {
	if (player.getHitPointsLeft() <= 0) {
	    onObjectDeath(player.getGameObjectType(), player);
	} else gameObjects.stream().filter(object -> object.getHitPointsLeft() <= 0)
		.forEach(object -> onObjectDeath(object.getGameObjectType(), object));
	gameObjects.removeAll(objectsToRemove);
	objectsToRemove.clear();
    }*/

    /*private void doPlayerUpdate(final float delta) {
	player.update(delta);
	obstacles.stream().filter(player::hasCollision).forEach(wall -> player.doAction(wall.getGameObjectType(), wall));
    }*/

    private void onObjectDeath(GameObject type, MovableEntity object) {
	switch (type) {
	    case WALL:
		//this will never occur as wall cannot die
		break;
	    case ENEMY:
		objectsToRemove.add(object);
		addScore(1);
		break;
	    case PLAYER:
		//handle game over
		System.out.println("player dead");
		String name = (String) JOptionPane
			.showInputDialog(null, "Please enter your name!", "Adding highscore...", JOptionPane.PLAIN_MESSAGE,
					 null, // no icon
					 null, // no options
					 null); // no default
		highscoreManager.addScore(name, player.getScore());
		gameOver = true;
		break;
	    case SMALL_STATIC_COIN:
		final SmallStaticCoin smallStaticCoin = (SmallStaticCoin) object;
		addScore(smallStaticCoin.getValue());
		objectsToRemove.add(object);
		break;
	    case SMALL_MOVING_COIN:
		final SmallMovingCoin smallMovingCoin = (SmallMovingCoin) object;
		addScore(smallMovingCoin.getValue());
		objectsToRemove.add(object);
		break;
	    case NORMAL_STATIC_POWER_UP:
		player.setpState(new PoweredUpState());
		player.setPowerUpTimer(((AbstractPowerUp) object).getPowerUpTime());
		objectsToRemove.add(object);
		break;
	}
    }

    public void handleMovement(float delta) {
	    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
		player.moveLeft(delta);
		System.out.println("movingleft");
	    }
	    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
		player.moveRight(delta);
		System.out.println("movingright");
	    }
	    //isKeyJustPressed means moment when button is pressed, not while button is pressed
	    if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.SPACE)) {
		player.jump();
	    }
    }

    private void addScore(final int points) {
	player.setScore(player.getScore() + points);
    }

    private void createPlayer() {
	this.player = playerMaker.createPlayer();
	gameObjects.add(player);
	player.setPosition(playerSpawnPoint);
	System.out.println("player spawned: " + player);
    }

    /**
     * spawns a enemy if enough time has passed, otherwise reduces time from timer
     * @param delta time since last check
     */
    private void spawnEnemy(float delta) {
	if (enemySpawnTimer <= 0) {
	    int numberOfFactories = enemyFactories.size();
	    int random = getRandomFactory.nextInt(numberOfFactories);
	    EnemyFactory factory = enemyFactories.get(random);
	    AbstractEnemy enemy = factory.createEnemy();
	    gameObjects.add(enemy);
	    enemySpawnTimer = ENEMY_RESPAWN_TIME;
	    System.out.println(timePassed);
	} else enemySpawnTimer -= delta;
    }

    /**
     * spawns a coin if there is currently none available
     */
    private void spawnCoin() {
	if (!containsInstance(gameObjects, AbstractCoin.class)) {
	    int numberOfFactories = coinFactories.size();
	    int random = getRandomFactory.nextInt(numberOfFactories);
	    CoinFactory factory = coinFactories.get(random);
	    AbstractCoin coin = factory.createCoin();
	    gameObjects.add(coin);
	}
    }

    /**
     * spawns a powerup if there is currently none available
     * and enough time has passed
     * otherwise reduces time from counter
     * @param delta time since last check
     */
    private void spawnPowerUp(float delta) {
	if (powerUpSpawnTimer <= 0) {
	    if (!containsInstance(gameObjects, AbstractPowerUp.class)) {
		int numberOfFactories = powerUpFactories.size();
		int random = getRandomFactory.nextInt(numberOfFactories);
		PowerUpFactory factory = powerUpFactories.get(random);
		AbstractPowerUp pwrUp = factory.createPowerUp();
		gameObjects.add(pwrUp);
		powerUpSpawnTimer = POWER_UP_RESPAWN_TIME;
	    }
	}
	else powerUpSpawnTimer -= delta;
    }


    /**
     * generic method to check if a collection contains a specific class
     * @param list collection to check
     * @param clazz class to check occurance for
     * @param <E> generic class
     * @return boolean, true if collection contains class
     */
    private static <E> boolean containsInstance(Collection<E> list, Class<? extends E> clazz) {
        return list.stream().anyMatch(clazz::isInstance);
    }

    //may need to change return type
    private void fetchMapObstacles() {
	gameObjects.addAll(map.getWalls().stream().collect(Collectors.toList()));
    }

    public static int getGravity() {
	final int gravity = NORMAL_GRAVITY;
	return gravity;
    }

    public Iterable<Entity> getGameObjects() {
	return gameObjects;
    }

    /*public List<Wall> getObstacles() {
	return obstacles;
    }*/

    public Player getPlayer() {
	return player;
    }

    public boolean isGameOver() {
	return gameOver;
    }

    public HighscoreManager getHighscoreManager() {
	return highscoreManager;
    }
}
