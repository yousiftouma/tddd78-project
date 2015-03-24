package com.mygdx.game;

import com.mygdx.game.exceptions.WrongInputException;
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
    /*
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
	    }
	}
    }
    */

    @Override public void create(){

	final Object[] maps = {"1"};
	mapNumberString = (String)JOptionPane.showInputDialog(
	                    null, // no parent frame
	                    "Chose a map to play!", // text
	                    "Mapselector", // title of dialog
	                    JOptionPane.PLAIN_MESSAGE,
			    null, // no icon
			    maps, // options
	                    "1"); // default
	mapNumber = Integer.parseInt(mapNumberString);
	setScreen(new GameScreen(mapNumber));
    }

}

