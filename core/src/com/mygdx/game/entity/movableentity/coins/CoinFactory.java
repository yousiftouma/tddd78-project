package com.mygdx.game.entity.movableentity.coins;

/**
 * Factory interface, all factories must be able to create a coin
 */
public interface CoinFactory {
    AbstractCoin createCoin();
}
