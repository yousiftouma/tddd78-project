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
 * Factory that creates moving coins, takes an ArrayList of possible spawn points and boolean movingLeft
 */
public class SmallMovingCoinFactory implements CoinFactory
{
    private Random getRandomSpawnPoint = new Random();
    private List<Vector2> spawnPoints;
    private boolean movingLeft;

    public SmallMovingCoinFactory(List<Vector2> spawnPoints, boolean movingLeft)
    {

	this.spawnPoints = spawnPoints;
	this.movingLeft = movingLeft;
    }

    /**
     * @return returns a new small moving coin at a random spawnpoint
     */
    @Override public AbstractCoin createCoin() {
	Vector2 size = SmallStaticCoin.getCoinSize();
	Vector2 velocity = new Vector2(MovableEntity.getDefaultVelocityX(), 0);
	Vector2 acceleration = new Vector2(0, -Game.getGravity());
	Sprite sprite = new Sprite(new Texture(Gdx.files.internal("goldCoin5.png")));
	Vector2 randomPosition = spawnPoints.get(getRandomSpawnPoint.nextInt());
	return new SmallMovingCoin(sprite, randomPosition, size, velocity, acceleration, movingLeft);
    }
}
