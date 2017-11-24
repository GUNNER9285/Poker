package com.example.poker.model;

/**
 * Created by GUNNER on 24/11/2560.
 */

public class Card {
    public String name;
    public int image;
    public boolean keep;

    public Card(String name, int image){
        this.name = name;
        this.image = image;
        keep = false;
    }
}
