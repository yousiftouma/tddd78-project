package com.mygdx.game.highscore;


import java.io.Serializable;

/**
 * Class to hold a score in the game, needs to be serializable so implements the interface
 */
public class Score  implements Serializable {
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }
}