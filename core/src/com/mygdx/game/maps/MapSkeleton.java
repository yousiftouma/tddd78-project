package com.mygdx.game.maps;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.obstacle.Wall;

import java.util.ArrayList;

/**
 * Superclass for maps that creates the arraylists of content but never fills them.
 * Each map is a singleton with lazy initialization, since we don't need to create a map unless
 * the game wants to fetch it.
 */
public class MapSkeleton implements GameMap
{
    protected ArrayList<Wall> walls;
    protected ArrayList<Vector2> enemySpawnPoints;
    protected ArrayList<Vector2> coinSpawnPoints;
    private static MapSkeleton instance = null;
    private Vector2 playerSpawnPoint;

    protected static final int NORMAL_WALL_THICKNESS = 10;

    protected MapSkeleton()
    {
	this.walls = new ArrayList<Wall>();
	this.enemySpawnPoints = new ArrayList<Vector2>();
	this.coinSpawnPoints = new ArrayList<Vector2>();
	this.playerSpawnPoint = null;
    }

    /**
     * Override these methods in subclass to fill world with walls and spawnpoints.
     * call them in constructor.
     */
    public void addWalls(){}
    public void addEnemySpawnPoints(){}
    public void addCoinSpawnPoints(){}

    @Override public MapSkeleton getInstance() {
	if (instance == null) {
	    instance = new MapSkeleton();
	}
	return instance;
    }

    @Override public ArrayList<Wall> getWalls() {
	return walls;
    }

    @Override public ArrayList<Vector2> getEnemySpawnPoints() {
	return enemySpawnPoints;
    }

    @Override public ArrayList<Vector2> getCoinSpawnPoints() {
	return coinSpawnPoints;
    }

    @Override
    public Vector2 getPlayerSpawnPoint() {
	return playerSpawnPoint;
    }

    public void setPlayerSpawnPoint(final Vector2 playerSpawnPoint) {
	this.playerSpawnPoint = playerSpawnPoint;
    }
}
