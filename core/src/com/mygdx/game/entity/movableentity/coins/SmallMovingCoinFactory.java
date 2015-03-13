package com.mygdx.game.entity.movableentity.coins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.entity.movableentity.MovableEntity;

import java.util.List;
import java.util.Random;

/**
 * Factory that creates moving coins, takes an ArrayList of possible spawn points
 */
public class SmallMovingCoinFactory implements CoinFactory
{
    private Sprite sprite;
    private Vector2 size;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Random getRandomSpawnPoint = new Random();
    private List<Vector2> spawnPoints;
    private int damage;
    private int hitPointsMax;
    private boolean movingLeft;

    // If factory shouldnt know spawnpoints, spawn it outside the world first or make createCoin take
    // a position
    public SmallMovingCoinFactory(List<Vector2> spawnPoints, boolean movingLeft)
    {
	this.sprite = new Sprite(new Texture(Gdx.files.internal("goldCoin5.png")));
	this.spawnPoints = spawnPoints;
	this.size = SmallStaticCoin.getCoinSize();
	this.velocity = new Vector2(MovableEntity.getDefaultVelocityX(), 0);
	this.acceleration = new Vector2(0, Game.getGravity());
	this.damage = 0;
	this.hitPointsMax = 1;
	this.movingLeft = movingLeft;
    }

    /**
     * @return returns a new small moving coin at a random spawnpoint
     */
    @Override public AbstractCoin createCoin() {
	Vector2 randomPosition = spawnPoints.get(getRandomSpawnPoint.nextInt());
	return new SmallMovingCoin(sprite, randomPosition, size, velocity, acceleration, damage, hitPointsMax, movingLeft);
    }
}
