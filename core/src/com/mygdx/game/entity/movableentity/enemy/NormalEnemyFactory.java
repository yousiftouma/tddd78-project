package com.mygdx.game.entity.movableentity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.entity.movableentity.MovableEntity;
import com.mygdx.game.entity.movableentity.coins.SmallMovingCoin;
import com.mygdx.game.entity.movableentity.coins.SmallStaticCoin;

import java.util.List;
import java.util.Random;

/**
 * Factory that creates NormalEnemy
 */
public class NormalEnemyFactory implements EnemyFactory
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

    public NormalEnemyFactory(List<Vector2> spawnPoints, boolean movingLeft)
    {
	this.sprite = new Sprite(new Texture(Gdx.files.internal("enemy.png")));
	this.spawnPoints = spawnPoints;
	this.size = NormalEnemy.getEnemySize();
	this.velocity = new Vector2(MovableEntity.getDefaultVelocityX(), 0);
	this.acceleration = new Vector2(0, Game.NORMAL_GRAVITY);
	this.damage = 0;
	this.hitPointsMax = 10;
	this.movingLeft = movingLeft;
    }

    @Override public AbstractEnemy createEnemy() {
	Vector2 randomPosition = spawnPoints.get(getRandomSpawnPoint.nextInt());
	return new NormalEnemy(sprite, randomPosition, size, velocity, acceleration, damage, hitPointsMax, movingLeft);
    }
}
