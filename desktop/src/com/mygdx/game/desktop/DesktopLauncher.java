package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

/**
 * launches game on desktop
 */
public final class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1080;
		config.height = 720;
	    	// result ignored since frame is created upon creation
		new LwjglApplication(new MyGdxGame(), config);
	}

    /**
     * not supposed to be called
     */
    private DesktopLauncher() {
    }
}
