package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screens.MenuScreen;


/**
 * Handles the game window
 */
public class GameWindow extends com.badlogic.gdx.Game
{
    /**
     * Runs upon starting launcher, selects screen to show
     */

    @Override public void create(){
	Screen menuScreen = new MenuScreen(this);
	setScreen(menuScreen);
    }

    @Override public void dispose() {
	super.dispose();
	Gdx.app.exit();
	System.exit(0);
    }
}

