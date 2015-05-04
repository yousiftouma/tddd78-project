package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.GameWindow;

import javax.swing.*;

/**
 * Displays a simple menu, also implements screen (libgdx), with its interfacemethods
 */
public class MenuScreen implements Screen
{

    private GameWindow window;
    private int mapNumber;

    /**
     * constructor, used to keep hold of window we occupy
     * @param window window to display screen in
     */
    public MenuScreen(GameWindow window) {
	this.window = window;
    }

    /**
     * interfacemethod
     * called when screen is set with SetScreen
     */
    @Override public void show() {
	final Object[] maps = { "1", "2" };
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

    /**
     * interfacemethod
     * if anything needs to be rendered which is not the case in this class
     */
    @Override public void render(final float delta) {
    }

    /**
     * interfacemethod, used mostly for mobile apps, not needed for us
     */
    @Override public void resize(final int width, final int height) {
    }

    /**
     * interfacemethod, used mostly in mobile apps, not needed for us
     */
    @Override public void pause() {
    }

    /**
     * interfacemethod, used mostly in mobile apps, not needed for us
     */
    @Override public void resume() {
    }

    /**
     * interfacemethod, used mostly in mobile apps, not needed for us
     */
    @Override public void hide() {
    }

    /**
     * interfacemethod, used mostly in mobile apps, not needed for us since
     * we don't have heavy objects to dispose in this screen (like a spritebatch)
     */
    @Override public void dispose() {
    }

}
