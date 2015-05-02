package com.mygdx.game.entity.movableentity.powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.entity.movableentity.MovableEntity;

import java.util.List;
import java.util.Random;

/**
 * Factory for creating normal moving powerdowns
 */
public class NormalMovingPowerDownFactory implements PowerUpFactory
{
    private List<Vector2> spawnPoints;
    private Random getRandomSpawnPoint = new Random();
    private boolean movingLeft;

    public NormalMovingPowerDownFactory(List<Vector2> spawnPoints, boolean movingLeft)
    {
	this.spawnPoints = spawnPoints;
	this.movingLeft = movingLeft;
    }

    @Override public AbstractPowerUp createPowerUp() {
	Vector2 size = NormalMovingPowerDown.getPowerUpSize();
	Vector2 velocity = new Vector2(MovableEntity.DEFAULT_VELOCITY_X,0);
	Vector2 acceleration = new Vector2(0, -Game.getGravity());
	Sprite sprite = new Sprite(new Texture(Gdx.files.internal("powerdown_50.png")));
	Vector2 randomPosition = spawnPoints.get(getRandomSpawnPoint.nextInt(spawnPoints.size()));
	return new NormalMovingPowerDown(sprite, randomPosition, size, velocity, acceleration, movingLeft);
    }
}
