package com.mygdx.game.entity.powerups;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;

public class NormalPowerUp extends AbstractPowerUp {

    public NormalPowerUp(Sprite sprite, Vector2 position, Vector2 size, int damage, float powerUpTime) {
        super(sprite, position, size, damage, powerUpTime);
    }
}
