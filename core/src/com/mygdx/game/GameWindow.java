package com.mygdx.game;

import com.mygdx.game.exceptions.WrongInputException;
import com.mygdx.game.maps.GameMap;
import com.mygdx.game.maps.Map1;
import com.mygdx.game.screens.GameScreen;

import javax.swing.*;

/**
 * Handles the game window
 */
public class GameWindow extends com.badlogic.gdx.Game
{

    private String mapNumberString = null;
    private Integer mapNumber = null;

    /**
     * Runs upon starting launcher, selects screen to show
     */
    @Override public void create() {

	final String wrongInputError = "Incorrect input! Needs to be wholenumber in range 1-10!";

	while (mapNumberString == null || mapNumber == null) {
	    try {
		mapNumberString = JOptionPane.showInputDialog("Choose a map to play! Input a wholenumber in the range 1-10!");
		mapNumber = Integer.parseInt(mapNumberString);
		if (1 <= mapNumber && mapNumber <= 10) setScreen(new GameScreen(mapNumber));
		else throw new WrongInputException(wrongInputError);
	    } catch (WrongInputException | NumberFormatException e) {
		e.printStackTrace();
		mapNumberString = null;
		mapNumber = null;
		//JOptionPane.showMessageDialog(null, wrongInputError);
	    }
	}
    }

}

