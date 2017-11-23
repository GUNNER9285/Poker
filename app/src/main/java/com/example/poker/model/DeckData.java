package com.example.poker.model;

/**
 * Created by GUNNER on 23/11/2560.
 */

public class DeckData {
    private static DeckData sInstance;
    public Deck deck;
    public static DeckData getInstance(){
        if(sInstance == null){
            sInstance = new DeckData();
        }
        return sInstance;
    }
}
