package com.example.poker.model;

/**
 * Created by GUNNER on 28/11/2560.
 */

// class ที่เป็น model ของ Score
public class ScoreItem {
    public final int id;
    public final int score;
    public final int rank;

    public ScoreItem(int id, int score,int rank) {
        this.id = id;
        this.score = score;
        this.rank = rank;
    }
}
