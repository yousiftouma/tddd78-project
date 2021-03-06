package com.mygdx.game.highscore;


import java.util.Comparator;

/**
 * A comparator class to compare scores, used for sorting highscore
 */
public class ScoreComparator implements Comparator<Score> {
        public int compare(Score score1, Score score2) {

            int firstScore = score1.getScore();
            int secondScore = score2.getScore();

            if (firstScore > secondScore) {
                return -1;
            } else if (firstScore < secondScore) {
                return 1;
            } else {
                return 0;
            }
        }
}