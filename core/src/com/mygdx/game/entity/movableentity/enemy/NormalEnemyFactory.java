package com.mygdx.game.entity.movableentity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.entity.movableentity.MovableEntity;

import java.util.List;
import java.util.Random;

/**
 * Factory that creates NormalEnemy
 */
public class NormalEnemyFactory implements EnemyFactory
{
    private Random getRandomSpawnPoint = new Random();
    private List<Vector2> spawnPoints;
    private int damage;
    private int hitPointsMax;
    private boolean movingLeft;

    public NormalEnemyFactory(List<Vector2> spawnPoints, boolean movingLeft)
    {
	this.spawnPoints = spawnPoints;
	this.damage = 10;
	this.hitPointsMax = 10;
	this.movingLeft = movingLeft;
    }

    @Override public AbstractEnemy createEnemy() {
	Vector2 size = NormalEnemy.getEnemySize();
	Vector2 velocity = new Vector2(MovableEntity.DEFAULT_VELOCITY_X, 0);
	Vector2 acceleration = new Vector2(0, -Game.getGravity());
	Sprite sprite = new Sprite(new Texture(Gdx.files.internal("enemy.png")));
	Vector2 randomPosition = spawnPoints.get(getRandomSpawnPoint.nextInt(spawnPoints.size()));
	return new NormalEnemy(sprite, randomPosition, size, velocity, acceleration, damage, hitPointsMax, movingLeft);
    }
}
