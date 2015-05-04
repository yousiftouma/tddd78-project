package com.mygdx.game.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.entity.movableentity.coins.SmallMovingCoinFactory;
import com.mygdx.game.entity.movableentity.coins.SmallStaticCoinFactory;
import com.mygdx.game.entity.movableentity.enemy.NormalEnemyFactory;
import com.mygdx.game.entity.obstacle.Wall;
import com.mygdx.game.entity.movableentity.powerups.NormalStaticPowerUpFactory;

/**
 * Represents the first map
 * Singleton
 */
public final class Map1 extends AbstractMap
{
    private Texture wallTexture = new Texture(Gdx.files.internal("truck_material-new-256.png"));
    static final GameMap INSTANCE = new Map1();

    private final static int PLAYER_SPAWN_X = 100;
    private final static int PLAYER_SPAWN_Y = 300;

    private Map1()
    {
	addWalls();
	addEnemySpawnPoints();
	addCoinAndPowerUpSpawnPoints();
	addCoinFactories();
	addEnemyFactories();
	addPowerUpFactories();
	setPlayerSpawnPoint(new Vector2(PLAYER_SPAWN_X, PLAYER_SPAWN_Y));
    }

    public static GameMap getInstance() {
	return INSTANCE;
    }

    public void addWalls() {
	walls.add(new Wall(new Sprite(wallTexture), new Vector2(OUTSIDE_SCREEN_TO_THE_LEFT, 0),
			   new Vector2(Game.FRAME_WIDTH * ONE_THIRD - OUTSIDE_SCREEN_TO_THE_LEFT, NORMAL_WALL_THICKNESS)));

	walls.add(new Wall(new Sprite(wallTexture), new Vector2(Game.FRAME_WIDTH * TWO_THIRDS, 0),
			   new Vector2(Game.FRAME_WIDTH * ONE_THIRD + OUTSIDE_SCREEN_TO_THE_RIGHT, NORMAL_WALL_THICKNESS)));

	walls.add(new Wall(new Sprite(wallTexture), new Vector2(Game.FRAME_WIDTH * ONE_FIFTH, Game.FRAME_HEIGHT * ONE_THIRD),
			   new Vector2(Game.FRAME_WIDTH * ONE_FIFTH, NORMAL_WALL_THICKNESS)));

	walls.add(new Wall(new Sprite(wallTexture), new Vector2(Game.FRAME_WIDTH * THREE_FIFTHS, Game.FRAME_HEIGHT * ONE_THIRD),
			   new Vector2(Game.FRAME_WIDTH * ONE_FIFTH, NORMAL_WALL_THICKNESS)));
    }

    public void addEnemySpawnPoints() {
	enemySpawnPoints.add(new Vector2(Game.FRAME_WIDTH * THREE_FOURTHS, Game.FRAME_HEIGHT * THREE_FOURTHS));
    }

    public void addCoinAndPowerUpSpawnPoints() {
	coinAndPowerUpSpawnPoints.add(new Vector2(Game.FRAME_WIDTH * ONE_FOURTH, Game.FRAME_HEIGHT * THREE_FOURTHS));
	coinAndPowerUpSpawnPoints.add(new Vector2(Game.FRAME_WIDTH* ONE_HALF, Game.FRAME_HEIGHT* THREE_FOURTHS));
    }

    public void addEnemyFactories() {
	enemyFactories.add(new NormalEnemyFactory(enemySpawnPoints, true));
	enemyFactories.add(new NormalEnemyFactory(enemySpawnPoints, false));
    }

    public void addCoinFactories() {
	coinFactories.add(new SmallStaticCoinFactory(coinAndPowerUpSpawnPoints));
	coinFactories.add(new SmallMovingCoinFactory(coinAndPowerUpSpawnPoints, true));
	coinFactories.add(new SmallMovingCoinFactory(coinAndPowerUpSpawnPoints, false));
    }

    public void addPowerUpFactories() {
	powerUpFactories.add(new NormalStaticPowerUpFactory(coinAndPowerUpSpawnPoints));
    }
}
