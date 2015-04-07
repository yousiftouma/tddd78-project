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
    private Vector2 size;
    private Vector2 velocity;
    private Vector2 acceleration;
    private List<Vector2> spawnPoints;
    private Random getRandomSpawnPoint = new Random();

    public NormalStaticPowerUpFactory(List<Vector2> spawnPoints)
    {
	this.spawnPoints = spawnPoints;
	this.size = NormalStaticPowerUp.getPowerUpSize();
	this.velocity = new Vector2(0,0);
	this.acceleration = new Vector2(0, -Game.getGravity());
    }

    @Override public AbstractPowerUp createPowerUp() {
	Sprite sprite = new Sprite(new Texture(Gdx.files.internal("powerup.png")));
	Vector2 randomPosition = spawnPoints.get(getRandomSpawnPoint.nextInt());
	return new NormalStaticPowerUp(sprite, randomPosition, size, velocity, acceleration);
    }
}
