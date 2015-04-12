package com.mygdx.game.highscore;


import java.util.*;
import java.io.*;

/**
 * Class to manage highscore. Writing to and reading from file and displaying highscore
 */
public class HighscoreManager
{
    private ArrayList<Score> scores;
    private ObjectOutputStream outputStream = null;

    private static final String HIGHSCORE_FILENAME = "highscore.dat";

    public HighscoreManager() {
	scores = new ArrayList<>();
    }

    public ArrayList<Score> getScores() {
	loadScoreFile();
	sort();
	return scores;
    }

    private void sort() {
	//We want it to be specifically ScoreComparator
	ScoreComparator comparator = new ScoreComparator();
	Collections.sort(scores, comparator);
    }

    public void addScore(String name, int score) {
	loadScoreFile();
	scores.add(new Score(name, score));
	updateScoreFile();
    }

    public void loadScoreFile() {
	try {
	    try (ObjectInput inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILENAME))) {
		//we know it is an arraylist
		scores = (ArrayList<Score>) inputStream.readObject();
	    }
	} catch (ClassNotFoundException | IOException e) {
	    System.out.println("error loading scores: " + e.getMessage());
	} finally {
	    try {
		if (outputStream != null) {
		    outputStream.flush();
		    outputStream.close();
		}
	    } catch (IOException e) {
		System.out.println("error loading scores: " + e.getMessage());
	    }
	}
    }

    public void updateScoreFile() {
	try {
	    outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILENAME));
	    outputStream.writeObject(scores);
	} catch (FileNotFoundException e) {
	    System.out.println("error updating scores: " + e.getMessage() + "new file will be created");
	} catch (IOException e) {
	    System.out.println("error updating scores: " + e.getMessage());
	} finally {
	    try {
		if (outputStream != null) {
		    outputStream.flush();
		    outputStream.close();
		}
	    } catch (IOException e) {
		System.out.println("error updating scores: " + e.getMessage());
	    }
	}
    }

    public String getHighscoreString() {
	StringBuilder highscoreString = new StringBuilder();
	int listSize = 10;
	List<Score> scores = getScores();
	int position = 0;

	if (scores.size() < listSize){
	    listSize = scores.size();
	}

	while (position < listSize) {
	    highscoreString.append(position + 1).append(". ").append(scores.get(position).getName()).append(" ")
		    .append(scores.get(position).getScore()).append("\n");
	    position++;
	}

	return highscoreString.toString();
    }
}