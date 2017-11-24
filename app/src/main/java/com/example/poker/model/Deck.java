package com.example.poker.model;

import android.content.Context;

import com.example.poker.Game;
import com.example.poker.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GUNNER on 24/11/2560.
 */

public class Deck {
    public ArrayList<Card> deck = new ArrayList<>();
    private Context context;

    public Deck(Context context){
        addDeck(context);
    }
    public void addDeck(Context context){
        for(int i=1; i<=53; ++i){
            String name = "c"+i;
            int image = context.getResources().getIdentifier("drawable/"+name, null, context.getPackageName());
            Card card = new Card(name, image);
            deck.add(card);
        }
    }
    public ArrayList<Card> shuffle() {
        Collections.shuffle(deck);
        return deck;
    }
    public List<Card> getCard(int num){
        List<Card> list = new ArrayList<>();
        for(int i=0; i<num; ++i){
            Card card = deck.get(i);
            list.add(card);
        }
        return list;
    }
}
