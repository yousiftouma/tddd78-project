package com.mygdx.game.entity.movableentity.coins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import java.util.List;
import java.util.Random;

/**
 * Factory that produces small static coins, takes an ArrayList of possible spawnpoints
 */
public class SmallStaticCoinFactory implements CoinFactory
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
    public SmallStaticCoinFactory(List<Vector2> spawnPoints)
    {
	this.sprite = new Sprite(new Texture(Gdx.files.internal("goldCoin5.png")));
	this.spawnPoints = spawnPoints;
	this.size = SmallStaticCoin.getCoinSize();
	this.velocity = new Vector2(0, 0);
	this.acceleration = new Vector2(0, Game.NORMAL_GRAVITY);
	this.damage = 0;
	this.hitPointsMax = 1;
	this.movingLeft = false;
    }

    /**
     * @return returns a new small static coin at a random spawnpoint
     */
    @Override public AbstractCoin createCoin() {
	Vector2 randomPosition = spawnPoints.get(getRandomSpawnPoint.nextInt());
	return new SmallStaticCoin(sprite, randomPosition, size, velocity, acceleration, damage, hitPointsMax, movingLeft);
    }
}

