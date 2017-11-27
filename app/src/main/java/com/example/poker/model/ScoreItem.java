package com.example.poker.model;

/**
 * Created by GUNNER on 27/11/2560.
 */

public class ScoreItem {
    public final int _id;
    public final int rank;
    public final int score;

    public ScoreItem(int _id, int rank, int score) {
        this._id = _id;
        this.rank = rank;
        this.score = score;
    }
}
