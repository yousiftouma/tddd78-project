package com.mygdx.game;

import com.mygdx.game.entity.movableentity.MovableEntity;

public class Pair {
    private MovableEntity firstObject;
    private MovableEntity secondObject;

    public Pair(final MovableEntity firstObject, final MovableEntity secondObject) {
	this.firstObject = firstObject;
	this.secondObject = secondObject;
    }

    public MovableEntity getFirstObject() {
	return firstObject;
    }

    public MovableEntity getSecondObject() {
	return secondObject;
    }
}
