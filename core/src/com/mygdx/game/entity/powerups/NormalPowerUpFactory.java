package com.mygdx.game.entity.powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Random;

/**
 * Factory for creating normal power ups
 */
public class NormalPowerUpFactory implements PowerUpFactory
{

    private Sprite sprite;
    private Vector2 size;
    private float powerUpTime;
    private List<Vector2> spawnPoints;
    private Random getRandomSpawnPoint = new Random();

    public NormalPowerUpFactory(List<Vector2> spawnPoints)
    {
	this.sprite = new Sprite(new Texture(Gdx.files.internal("enemy.png")));
	this.spawnPoints = spawnPoints;
	this.size = NormalPowerUp.getPowerUpSize();
	this.powerUpTime = 10;
    }

    @Override public AbstractPowerUp createPowerUp() {
	Vector2 randomPosition = spawnPoints.get(getRandomSpawnPoint.nextInt());
	return new NormalPowerUp(sprite, randomPosition, size, powerUpTime);
    }
}
