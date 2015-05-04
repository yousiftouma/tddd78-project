package com.mygdx.game.maps;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.movableentity.coins.CoinFactory;
import com.mygdx.game.entity.movableentity.enemy.EnemyFactory;
import com.mygdx.game.entity.obstacle.Wall;
import com.mygdx.game.entity.movableentity.powerups.PowerUpFactory;

import java.util.List;

/**
 * Interface for maps. Each map is singleton and can only be
 * created once which is logical since it is unnecessary to have multiple map objects that all will stay the same
 * Must be able to return the factories, walls and spawnpoints of the map.
 *
 * We use eager initialization since the scale of the game allows it. It's ok to create all maps upon starting the game
 * instead of having lazy initialization where the map is only created when called. If the game becomes too big so that
 * it takes time to load textures, it would be appropriate to have lazy initialization or similar to avoid loading maps
 * when unecessary.
 */
public interface GameMap
{
    List<Wall> getWalls();
    List<CoinFactory> getCoinFactories();
    List<EnemyFactory> getEnemyFactories();
    List<PowerUpFactory> getPowerUpFactories();
    Vector2 getPlayerSpawnPoint();

}
