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
public final class Map1 extends AbstractMap implements GameMap
{
    private Texture wallTexture = new Texture(Gdx.files.internal("truck_material-new-256.png"));
    static final Map1 INSTANCE = new Map1();

    private Map1()
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
	walls.add(new Wall(new Sprite(wallTexture), new Vector2(OUTSIDE_SCREEN_TO_THE_LEFT, 0),
			   new Vector2(Game.FRAME_WIDTH * ONE_THIRD - OUTSIDE_SCREEN_TO_THE_LEFT, getNormalWallThickness())));

	walls.add(new Wall(new Sprite(wallTexture), new Vector2(Game.FRAME_WIDTH * TWO_THIRDS, 0),
			   new Vector2(Game.FRAME_WIDTH * ONE_THIRD + OUTSIDE_SCREEN_TO_THE_RIGHT, getNormalWallThickness())));

	walls.add(new Wall(new Sprite(wallTexture), new Vector2(Game.FRAME_WIDTH * ONE_FIFTH, Game.FRAME_HEIGHT * ONE_THIRD),
			   new Vector2(Game.FRAME_WIDTH * ONE_FIFTH, getNormalWallThickness())));

	walls.add(new Wall(new Sprite(wallTexture), new Vector2(Game.FRAME_WIDTH * THREE_FIFTHS, Game.FRAME_HEIGHT * ONE_THIRD),
			   new Vector2(Game.FRAME_WIDTH * ONE_FIFTH, getNormalWallThickness())));
    }

    @Override public void addEnemySpawnPoints() {
	enemySpawnPoints.add(new Vector2(Game.FRAME_WIDTH * THREE_FOURTHS, Game.FRAME_HEIGHT * THREE_FOURTHS));
    }

    @Override public void addCoinSpawnPoints() {
	coinSpawnPoints.add(new Vector2(Game.FRAME_WIDTH * ONE_FOURTH, Game.FRAME_HEIGHT * THREE_FOURTHS));
    }

    @Override public void addEnemyFactories() {
	enemyFactories.add(new NormalEnemyFactory(enemySpawnPoints, true));
	enemyFactories.add(new NormalEnemyFactory(enemySpawnPoints, false));
    }

    @Override public void addCoinFactories() {
	coinFactories.add(new SmallStaticCoinFactory(coinSpawnPoints));
	coinFactories.add(new SmallMovingCoinFactory(coinSpawnPoints, true));
	coinFactories.add(new SmallMovingCoinFactory(coinSpawnPoints, false));
    }

    @Override public void addPowerUpFactories() {
	powerUpFactories.add(new NormalStaticPowerUpFactory(coinSpawnPoints));
    }
}
