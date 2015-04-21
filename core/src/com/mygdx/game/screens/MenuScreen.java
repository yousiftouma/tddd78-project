package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.GameWindow;

import javax.swing.*;

/**
 * Displays a menu
 */
public class MenuScreen implements Screen
{

    private GameWindow window;
    private int mapNumber;

    public MenuScreen(GameWindow window) {
	this.window = window;
    }

    @Override public void show() {
	final Object[] maps = { "1" };
	String mapNumberString = (String) JOptionPane.showInputDialog(null, // no parent frame
								      "Choose a map to play!", // text
								      "Mapselector", // title of dialog
								      JOptionPane.PLAIN_MESSAGE, null, // no icon
								      maps, // options
								      "1"); // default
	try {
	    mapNumber = Integer.parseInt(mapNumberString);
	}
	catch (NumberFormatException ignored) { //user presses Avbryt
	    System.exit(0);
	}

	Screen gameScreen = new GameScreen(mapNumber, window);
	window.setScreen(gameScreen);
    }

    @Override public void render(final float delta) {
    }

    @Override public void resize(final int width, final int height) {
    }

    @Override public void pause() {
    }

    @Override public void resume() {
    }

    @Override public void hide() {
    }

    @Override public void dispose() {
    }

}
