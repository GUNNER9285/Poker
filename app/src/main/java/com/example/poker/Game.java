package com.example.poker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.poker.model.Cards;
import com.example.poker.model.Deck;
import com.example.poker.model.DeckData;

import java.util.List;

public class Game extends AppCompatActivity {
    private final DeckData deckData = DeckData.getInstance();   // global
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        deckData.deck = new Deck();
        Deck deck = deckData.deck;
        deck.shuffle();
        List<Cards> List = deck.getCard(5);
        ImageView card1 = (ImageView) findViewById(R.id.card1);
        ImageView card2 = (ImageView) findViewById(R.id.card2);
        ImageView card3 = (ImageView) findViewById(R.id.card3);
        ImageView card4 = (ImageView) findViewById(R.id.card4);
        ImageView card5 = (ImageView) findViewById(R.id.card5);
        card1.setImageResource(List.get(0).image);
        card2.setImageResource(List.get(1).image);
        card3.setImageResource(List.get(2).image);
        card4.setImageResource(List.get(3).image);
        card5.setImageResource(List.get(4).image);

    }
}
