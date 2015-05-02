package com.mygdx.game.highscore;

import java.util.*;
import java.io.*;

/**
 * Class to manage highscore. Writing to and reading from file and displaying highscore
 */
public class HighscoreManager
{
    private List<Score> scores;
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;
    private ScoreComparator comparator = new ScoreComparator();

    private static final String HIGHSCORE_FILENAME = "highscore.dat";

    public HighscoreManager() {
	this.scores = new ArrayList<>();
	this.comparator = new ScoreComparator();
    }

    public List<Score> getScores() {
	loadScoreFile();
	sort();
	return scores;
    }

    private void sort() {
	Collections.sort(scores, comparator);
    }

    public void addScore(String name, int score) {
	loadScoreFile();
	scores.add(new Score(name, score));
	updateScoreFile();
    }

    public void loadScoreFile() {
            try {
		inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILENAME));
		// we know the type to be List
		scores = (List<Score>) inputStream.readObject();

            } catch (FileNotFoundException e) {
                System.out.println("Error loading highscorefile, File Not Found Exception," +
				   " will try to create file: " + e.getMessage());
		e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error loading highscorefile, IO Exception: " + e.getMessage());
		e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Error loading highscorefile, Class Not Found Exception: " + e.getMessage());
		e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error loading highscorefile, IO Error: " + e.getMessage());
		    e.printStackTrace();
                }
            }
    }

    public void updateScoreFile() {
	try {
	    outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILENAME));
	    outputStream.writeObject(scores);
	} catch (FileNotFoundException e) {
	    System.out.println("error updating scores: " + e.getMessage() + "new file will be created");
	    e.printStackTrace();
	} catch (IOException e) {
	    System.out.println("error updating scores: " + e.getMessage());
	    e.printStackTrace();
	} finally {
	    try {
		if (outputStream != null) {
		    outputStream.flush();
		    outputStream.close();
		}
	    } catch (IOException e) {
		System.out.println("error updating scores: " + e.getMessage());
		e.printStackTrace();
	    }
	}
    }

    public String getHighscoreString() {
	StringBuilder highscoreString = new StringBuilder();
	int listSize = 10;
	List<Score> scores = getScores();
	int position = 0;

	if (scores.size() < listSize) {
	    listSize = scores.size();
	}

	while (position < listSize) { //output example: 1. Mikael 50 *newline* 2. Jonas 45
	    highscoreString.append(position + 1).append(". ").append(scores.get(position).getName()).append(" ")
		    .append(scores.get(position).getScore()).append("\n");
	    position++;
	}

	return highscoreString.toString();
    }
}