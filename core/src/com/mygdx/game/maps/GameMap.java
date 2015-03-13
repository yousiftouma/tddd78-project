package com.mygdx.game.maps;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.obstacle.Wall;

import java.util.ArrayList;

/**
 * Interface for maps, must be able to return themselves to anyone who wants it, but each map
 * is singleton and can only be created once
 */
public interface GameMap
{
    MapSkeleton getInstance();
    ArrayList<Wall> getWalls();
    ArrayList<Vector2> getEnemySpawnPoints();
    ArrayList<Vector2> getCoinSpawnPoints();
    Vector2 getPlayerSpawnPoint();


}
