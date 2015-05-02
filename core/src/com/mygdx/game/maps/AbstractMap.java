package com.mygdx.game.maps;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.movableentity.coins.CoinFactory;
import com.mygdx.game.entity.movableentity.enemy.EnemyFactory;
import com.mygdx.game.entity.obstacle.Wall;
import com.mygdx.game.entity.movableentity.powerups.PowerUpFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Superclass for maps that creates the arraylists of content but never fills them. This class is never instantiated
 * and therefore abstract but needed to reduce code duplication. (like a code skeleton)
 */

public abstract class AbstractMap
{
    /**
     * protected fields since the subclass wants to access their version of the lists directly to fill them
     */
    protected List<Wall> walls;
    /**
     * In libgdx Vector2 can be used as a point
     */
    protected List<Vector2> enemySpawnPoints;
    protected List<Vector2> coinAndPowerUpSpawnPoints;
    protected List<EnemyFactory> enemyFactories;
    protected List<CoinFactory> coinFactories;
    protected List<PowerUpFactory> powerUpFactories;

    private Vector2 playerSpawnPoint;

    /**
     * normal thickness for a wall, used for collisionchecking with walls (thus public)
     * and when creating walls
     */
    public static final int NORMAL_WALL_THICKNESS = 10;

    // following constants are used to set size and position of obstacles like walls
    protected final static float ONE_HALF = 1.0f / 2;

    protected final static float ONE_THIRD = 1.0f / 3;
    protected final static float TWO_THIRDS = 2.0f / 3;

    protected final static float ONE_FOURTH = 1.0f / 4;
    protected final static float THREE_FOURTHS = 3.0f / 4;

    protected final static float ONE_FIFTH = 1.0f / 5;
    protected final static float TWO_FIFTHS = 2.0f / 5;
    protected final static float THREE_FIFTHS = 3.0f / 5;

    protected final static float OUTSIDE_SCREEN_TO_THE_LEFT = -100.0f;
    protected final static float OUTSIDE_SCREEN_TO_THE_RIGHT = 100.0f;

    protected AbstractMap()
    {
	this.walls = new ArrayList<>();
	this.enemySpawnPoints = new ArrayList<>();
	this.coinAndPowerUpSpawnPoints = new ArrayList<>();
	this.enemyFactories = new ArrayList<>();
	this.coinFactories = new ArrayList<>();
	this.powerUpFactories = new ArrayList<>();
	this.playerSpawnPoint = null;
    }

    /**
     * Override these add-methods in subclass to fill map with walls, factories and spawnpoints specific to that map.
     * Call them in constructor together with setting player spawnpoint.
     */
    public abstract void addWalls();

    public abstract void addEnemySpawnPoints();

    public abstract void addCoinSpawnPoints();

    public abstract void addEnemyFactories();

    public abstract void addCoinFactories();

    public abstract void addPowerUpFactories();

    public void setPlayerSpawnPoint(final Vector2 playerSpawnPoint) {
	this.playerSpawnPoint = playerSpawnPoint;
    }

    public List<Wall> getWalls() {
	return walls;
    }

    public Vector2 getPlayerSpawnPoint() {
	return playerSpawnPoint;
    }

    public List<EnemyFactory> getEnemyFactories() {
	return enemyFactories;
    }

    public List<CoinFactory> getCoinFactories() {
	return coinFactories;
    }

    public List<PowerUpFactory> getPowerUpFactories() {
	return powerUpFactories;
    }

}
