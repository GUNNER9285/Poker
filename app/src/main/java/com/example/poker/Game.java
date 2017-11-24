package com.example.poker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.poker.model.Card;
import com.example.poker.model.Deck;

import java.util.List;

public class Game extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Deck deck = new Deck(this);
        deck.shuffle();
        List<Card> List = deck.getCard(5);
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