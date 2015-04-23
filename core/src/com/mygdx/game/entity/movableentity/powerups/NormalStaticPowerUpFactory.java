package com.mygdx.game.entity.movableentity.powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

import java.util.List;
import java.util.Random;

/**
 * Factory for creating normal power ups
 */
public class NormalStaticPowerUpFactory implements PowerUpFactory
{
    private List<Vector2> spawnPoints;
    private Random getRandomSpawnPoint = new Random();

    public NormalStaticPowerUpFactory(List<Vector2> spawnPoints)
    {
	this.spawnPoints = spawnPoints;
    }

    @Override public AbstractPowerUp createPowerUp() {
	Vector2 size = NormalStaticPowerUp.getPowerUpSize();
	Vector2 velocity = new Vector2(0,0);
	Vector2 acceleration = new Vector2(0, -Game.getGravity());
	Sprite sprite = new Sprite(new Texture(Gdx.files.internal("powerup.png")));
	Vector2 randomPosition = spawnPoints.get(getRandomSpawnPoint.nextInt(spawnPoints.size()));
	return new NormalStaticPowerUp(sprite, randomPosition, size, velocity, acceleration);
    }
}
