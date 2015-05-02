package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screens.MenuScreen;


/**
 * Handles the gamewindow, extends the libgdx class Game
 */
public class GameWindow extends com.badlogic.gdx.Game
{
    /**
     * Runs upon starting launcher, selects screen to show, sets to menuscreen
     */
    @Override public void create(){
	Screen menuScreen = new MenuScreen(this);
	setScreen(menuScreen);
    }

    /**
     * Overrided method in Game, used in our case to shut down the game
     */
    @Override public void dispose() {
	super.dispose();
	Gdx.app.exit();
	System.exit(0);
    }
}

