package com.mygdx.game.maps;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.obstacle.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * Superclass for maps that creates the arraylists of content but never fills them. This class is never instantiated
 * and therefore abstract but needed to reduce codeduplication.
 */

public abstract class AbstractMap
{
    /**
     * protected fields since the subclass wants to access their version of the lists directly to fill them
     */
    protected List<Wall> walls;
    protected List<Vector2> enemySpawnPoints;
    protected List<Vector2> coinSpawnPoints;

    private Vector2 playerSpawnPoint;

    protected static final int NORMAL_WALL_THICKNESS = 10;

    protected AbstractMap()
    {
	this.walls = new ArrayList<>();
	this.enemySpawnPoints = new ArrayList<>();
	this.coinSpawnPoints = new ArrayList<>();
	this.playerSpawnPoint = null;
    }

    /**
     * Override these 3 methods in subclass to fill map with walls and spawnpoints specific to that map.
     * Call them in constructor.
     */
    public abstract void addWalls();

    public abstract void addEnemySpawnPoints();

    public abstract void addCoinSpawnPoints();

    public List<Wall> getWalls() {
	return walls;
    }

    public List<Vector2> getEnemySpawnPoints() {
	return enemySpawnPoints;
    }

    public List<Vector2> getCoinSpawnPoints() {
	return coinSpawnPoints;
    }

    public Vector2 getPlayerSpawnPoint() {
	return playerSpawnPoint;
    }

    public void setPlayerSpawnPoint(final Vector2 playerSpawnPoint) {
	this.playerSpawnPoint = playerSpawnPoint;
    }

}
