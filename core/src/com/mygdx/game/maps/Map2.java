package com.mygdx.game.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.entity.movableentity.coins.SmallMovingCoinFactory;
import com.mygdx.game.entity.movableentity.coins.SmallStaticCoinFactory;
import com.mygdx.game.entity.movableentity.enemy.NormalEnemyFactory;
import com.mygdx.game.entity.movableentity.powerups.NormalMovingPowerDownFactory;
import com.mygdx.game.entity.movableentity.powerups.NormalStaticPowerUpFactory;
import com.mygdx.game.entity.obstacle.Wall;

/**
 * Represents the second map
 * Singleton
 */
public final class Map2 extends AbstractMap implements GameMap
{
    private Texture wallTexture = new Texture(Gdx.files.internal("truck_material-new-256.png"));
    // we need this to be a Map2 since it is a singleton
    static final Map2 INSTANCE = new Map2();

    private final static int PLAYER_SPAWN_X = 100;
    private final static int PLAYER_SPAWN_Y = 300;

    private Map2()
    {
	addWalls();
	addEnemySpawnPoints();
	addCoinSpawnPoints();
	addCoinFactories();
	addEnemyFactories();
	addPowerUpFactories();
	setPlayerSpawnPoint(new Vector2(PLAYER_SPAWN_X, PLAYER_SPAWN_Y));
    }

    public static GameMap getInstance() {
	return INSTANCE;
    }

    @Override public void addWalls() {
	walls.add(new Wall(new Sprite(wallTexture), new Vector2(0, 0),
			   new Vector2(Game.FRAME_WIDTH * TWO_FIFTHS, NORMAL_WALL_THICKNESS)));

	walls.add(new Wall(new Sprite(wallTexture), new Vector2(Game.FRAME_WIDTH * THREE_FIFTHS, 0),
			   new Vector2(Game.FRAME_WIDTH * TWO_FIFTHS, NORMAL_WALL_THICKNESS)));

	walls.add(new Wall(new Sprite(wallTexture), new Vector2(0, 0),
				   new Vector2(NORMAL_WALL_THICKNESS, Game.FRAME_HEIGHT)));

	walls.add(new Wall(new Sprite(wallTexture), new Vector2(Game.FRAME_WIDTH - NORMAL_WALL_THICKNESS, 0),
					   new Vector2(NORMAL_WALL_THICKNESS, Game.FRAME_HEIGHT)));

	walls.add(new Wall(new Sprite(wallTexture), new Vector2(Game.FRAME_WIDTH * ONE_FIFTH, Game.FRAME_HEIGHT * ONE_THIRD),
			   new Vector2(Game.FRAME_WIDTH * ONE_FIFTH, NORMAL_WALL_THICKNESS)));

	walls.add(new Wall(new Sprite(wallTexture), new Vector2(Game.FRAME_WIDTH * THREE_FIFTHS, Game.FRAME_HEIGHT * ONE_THIRD),
			   new Vector2(Game.FRAME_WIDTH * ONE_FIFTH, NORMAL_WALL_THICKNESS)));
    }

    @Override public void addEnemySpawnPoints() {
	enemySpawnPoints.add(new Vector2(Game.FRAME_WIDTH * THREE_FOURTHS, Game.FRAME_HEIGHT * THREE_FOURTHS));
    }

    @Override public void addCoinSpawnPoints() {
	coinAndPowerUpSpawnPoints.add(new Vector2(Game.FRAME_WIDTH * ONE_FOURTH, Game.FRAME_HEIGHT * THREE_FOURTHS));
	coinAndPowerUpSpawnPoints.add(new Vector2(Game.FRAME_WIDTH* ONE_HALF, Game.FRAME_HEIGHT* THREE_FOURTHS));
    }

    @Override public void addEnemyFactories() {
	enemyFactories.add(new NormalEnemyFactory(enemySpawnPoints, true));
	enemyFactories.add(new NormalEnemyFactory(enemySpawnPoints, false));
    }

    @Override public void addCoinFactories() {
	coinFactories.add(new SmallStaticCoinFactory(coinAndPowerUpSpawnPoints));
	coinFactories.add(new SmallMovingCoinFactory(coinAndPowerUpSpawnPoints, true));
	coinFactories.add(new SmallMovingCoinFactory(coinAndPowerUpSpawnPoints, false));
    }

    @Override public void addPowerUpFactories() {
	powerUpFactories.add(new NormalStaticPowerUpFactory(coinAndPowerUpSpawnPoints));
	powerUpFactories.add(new NormalMovingPowerDownFactory(coinAndPowerUpSpawnPoints, false));
	powerUpFactories.add(new NormalMovingPowerDownFactory(coinAndPowerUpSpawnPoints, true));
    }
}
