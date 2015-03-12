package com.mygdx.game.entity.coins;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;

/**
 * Created by Yousif Touma on 2015-03-06.
 */
public class GoldCoins extends CollisionEntity
{


    protected GoldCoins(Sprite sprite, Vector2 position, Vector2 size, int damage) {
        super(sprite, position, size, damage);
    }
}
