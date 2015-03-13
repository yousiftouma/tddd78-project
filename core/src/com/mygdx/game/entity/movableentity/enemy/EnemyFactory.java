package com.mygdx.game.entity.movableentity.enemy;

/**
 * EnemyFactory interface that states that all enemyfactories must be able to create enemy
 */
public interface EnemyFactory
{
    AbstractEnemy createEnemy();
}
