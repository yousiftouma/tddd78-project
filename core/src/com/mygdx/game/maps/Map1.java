package com.mygdx.game.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.entity.obstacle.Wall;

/**
 * Represents the first map
 */
final class Map1 extends MapSkeleton
{
    private Texture wallTexture = new Texture(Gdx.files.internal("truck_material-new-256.png"));
    private final static int PLAYER_SPAWN_X = 100;
    private final static int PLAYER_SPAWN_Y = 300;

    private Map1()
    {
	addWalls();
	addEnemySpawnPoints();
	addCoinSpawnPoints();
	setPlayerSpawnPoint(new Vector2(PLAYER_SPAWN_X, PLAYER_SPAWN_Y));
    }

    @Override public void addWalls() {
	super.addWalls();
	walls.add(new Wall(new Sprite(wallTexture), new Vector2(0, 0),
			   new Vector2(Game.FRAME_WIDTH / 3.0f, NORMAL_WALL_THICKNESS), 0));
	walls.add(new Wall(new Sprite(wallTexture), new Vector2(2 * (Game.FRAME_WIDTH / 3.0f), 0),
			   new Vector2(Game.FRAME_WIDTH / 3.0f, NORMAL_WALL_THICKNESS), 0));
	walls.add(new Wall(new Sprite(wallTexture), new Vector2((Game.FRAME_WIDTH / 5.0f), 2 * Game.FRAME_HEIGHT / 6.0f),
			   new Vector2(Game.FRAME_WIDTH / 5.0f, NORMAL_WALL_THICKNESS), 0));
	walls.add(new Wall(new Sprite(wallTexture), new Vector2((3 * Game.FRAME_WIDTH / 5.0f), 2 * Game.FRAME_HEIGHT / 6.0f),
			   new Vector2(Game.FRAME_WIDTH / 5.0f, NORMAL_WALL_THICKNESS), 0));
    }

    @Override public void addEnemySpawnPoints() {
	super.addEnemySpawnPoints();
	enemySpawnPoints.add(new Vector2(Game.FRAME_WIDTH / 2.0f, Game.FRAME_HEIGHT * 0.75f));
    }

    @Override public void addCoinSpawnPoints() {
	super.addCoinSpawnPoints();
	coinSpawnPoints.add(new Vector2(Game.FRAME_WIDTH / 4.0f, Game.FRAME_HEIGHT * 0.75f));
    }
}
