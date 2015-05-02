package com.mygdx.game.exceptions;

import javax.swing.*;

/**
 * Handles wrong input in for example input dialogs
 */
public class WrongInputException extends Exception
{
    public WrongInputException(final String message) {
	JOptionPane.showMessageDialog(null, message);
    }
}
