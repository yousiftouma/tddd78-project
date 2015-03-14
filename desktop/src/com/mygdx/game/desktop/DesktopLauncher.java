package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Game;
import com.mygdx.game.GameWindow;

/**
 * launches game on desktop
 */
public final class DesktopLauncher
{

    public static void main(String[] arg) {
	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	config.title = Game.TITLE;
	config.width = Game.FRAME_WIDTH;
	config.height = Game.FRAME_HEIGHT;
	// result ignored since frame is created upon object creation
	new LwjglApplication(new GameWindow(), config);
    }

    /**
     * not supposed to be called
     */
    private DesktopLauncher() {
    }
}
