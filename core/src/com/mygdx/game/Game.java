package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Entity;
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

    private final static int NORMAL_GRAVITY = 982;

    /**
     * height of game window
     */
    public static final int FRAME_HEIGHT = 480;
    /**
     * width of game window
     */
    public static final int FRAME_WIDTH = 640;

    public Game(GameMap map) {
	this.gameObjects = new ArrayList<Entity>();
	this.map = map.getInstance();
	fetchMap();
	createPlayer();
    }

    public void createPlayer() {
	final Player player = new PlayerMaker().createPlayer();
	player.setPosition(map.getPlayerSpawnPoint());
    }

    public void fetchMap(){
	coinSpawnPoints = map.getCoinSpawnPoints();
	enemySpawnPoints = map.getEnemySpawnPoints();
	for (Wall wall : map.getWalls()){
	    gameObjects.add(wall);
	}
    }

    public static int getGravity() {
	final int gravity = NORMAL_GRAVITY;
	return gravity;
    }

    public List<Entity> getGameObjects() {
	return gameObjects;
    }


}
