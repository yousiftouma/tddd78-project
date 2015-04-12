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
    private Random getRandomSpawnPoint = new Random();
    private List<Vector2> spawnPoints;

    public SmallStaticCoinFactory(List<Vector2> spawnPoints)
    {
	this.spawnPoints = spawnPoints;
    }

    /**
     * @return returns a new small static coin at a random spawnpoint
     */
    @Override public AbstractCoin createCoin() {
	Vector2 size = SmallStaticCoin.getCoinSize();
	Vector2 velocity = new Vector2(0, 0);
	Vector2 acceleration = new Vector2(0, -Game.getGravity());
	Sprite sprite = new Sprite(new Texture(Gdx.files.internal("goldCoin5.png")));
	Vector2 randomPosition = spawnPoints.get(getRandomSpawnPoint.nextInt());
	return new SmallStaticCoin(sprite, randomPosition, size, velocity, acceleration);
    }
}

