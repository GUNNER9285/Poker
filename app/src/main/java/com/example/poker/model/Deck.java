package com.example.poker.model;

import com.example.poker.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GUNNER on 23/11/2560.
 */

public class Deck {
    public List<Cards> deck = new ArrayList<>();
    public Deck(){
        addDeck();
    }
    public Deck(List<Cards> deck){
        this.deck = deck;
        addDeck();
    }
    public void addDeck(){
        Cards c1 = new Cards("c1", R.drawable.c1);
        deck.add(c1);
        Cards c2 = new Cards("c2", R.drawable.c2);
        deck.add(c2);
        Cards c3 = new Cards("c3", R.drawable.c3);
        deck.add(c3);
        Cards c4 = new Cards("c4", R.drawable.c4);
        deck.add(c4);
        Cards c5 = new Cards("c5", R.drawable.c5);
        deck.add(c5);
        Cards c6 = new Cards("c6", R.drawable.c6);
        deck.add(c6);
        Cards c7 = new Cards("c7", R.drawable.c7);
        deck.add(c7);
        Cards c8 = new Cards("c8", R.drawable.c8);
        deck.add(c8);
        Cards c9 = new Cards("c9", R.drawable.c9);
        deck.add(c9);
        Cards c10 = new Cards("c10", R.drawable.c10);
        deck.add(c10);
        Cards c11 = new Cards("c11", R.drawable.c11);
        deck.add(c11);
        Cards c12 = new Cards("c12", R.drawable.c12);
        deck.add(c12);
        Cards c13 = new Cards("c13", R.drawable.c13);
        deck.add(c13);
        Cards c14 = new Cards("c14", R.drawable.c14);
        deck.add(c14);
        Cards c15 = new Cards("c15", R.drawable.c15);
        deck.add(c15);
        Cards c16 = new Cards("c16", R.drawable.c16);
        deck.add(c16);
        Cards c17 = new Cards("c17", R.drawable.c17);
        deck.add(c17);
        Cards c18 = new Cards("c18", R.drawable.c18);
        deck.add(c18);
        Cards c19 = new Cards("c19", R.drawable.c19);
        deck.add(c19);
        Cards c20 = new Cards("c20", R.drawable.c20);
        deck.add(c20);
        Cards c21 = new Cards("c21", R.drawable.c21);
        deck.add(c21);
        Cards c22 = new Cards("c22", R.drawable.c22);
        deck.add(c22);
        Cards c23 = new Cards("c23", R.drawable.c23);
        deck.add(c23);
        Cards c24 = new Cards("c24", R.drawable.c24);
        deck.add(c24);
        Cards c25 = new Cards("c25", R.drawable.c25);
        deck.add(c25);
        Cards c26 = new Cards("c26", R.drawable.c26);
        deck.add(c26);
        Cards c27 = new Cards("c27", R.drawable.c27);
        deck.add(c27);
        Cards c28 = new Cards("c28", R.drawable.c28);
        deck.add(c28);
        Cards c29 = new Cards("c29", R.drawable.c29);
        deck.add(c29);
        Cards c30 = new Cards("c30", R.drawable.c30);
        deck.add(c30);
        Cards c31 = new Cards("c31", R.drawable.c31);
        deck.add(c31);
        Cards c32 = new Cards("c32", R.drawable.c32);
        deck.add(c32);
        Cards c33 = new Cards("c33", R.drawable.c33);
        deck.add(c33);
        Cards c34 = new Cards("c34", R.drawable.c34);
        deck.add(c34);
        Cards c35 = new Cards("c35", R.drawable.c35);
        deck.add(c35);
        Cards c36 = new Cards("c36", R.drawable.c36);
        deck.add(c36);
        Cards c37 = new Cards("c37", R.drawable.c37);
        deck.add(c37);
        Cards c38 = new Cards("c38", R.drawable.c38);
        deck.add(c38);
        Cards c39 = new Cards("c39", R.drawable.c39);
        deck.add(c39);
        Cards c40 = new Cards("c40", R.drawable.c40);
        deck.add(c40);
        Cards c41 = new Cards("c41", R.drawable.c41);
        deck.add(c41);
        Cards c42 = new Cards("c42", R.drawable.c42);
        deck.add(c42);
        Cards c43 = new Cards("c43", R.drawable.c43);
        deck.add(c43);
        Cards c44 = new Cards("c44", R.drawable.c44);
        deck.add(c44);
        Cards c45 = new Cards("c45", R.drawable.c45);
        deck.add(c45);
        Cards c46 = new Cards("c46", R.drawable.c46);
        deck.add(c46);
        Cards c47 = new Cards("c47", R.drawable.c47);
        deck.add(c47);
        Cards c48 = new Cards("c48", R.drawable.c48);
        deck.add(c48);
        Cards c49 = new Cards("c49", R.drawable.c49);
        deck.add(c49);
        Cards c50 = new Cards("c50", R.drawable.c50);
        deck.add(c50);
        Cards c51 = new Cards("c51", R.drawable.c51);
        deck.add(c51);
        Cards c52 = new Cards("c52", R.drawable.c52);
        deck.add(c52);
        Cards c53 = new Cards("c53", R.drawable.c53);
        deck.add(c53);
    }
    public List<Cards> shuffle() {
        Collections.shuffle(deck);
        return deck;
    }
    public List<Cards> getCard(int num){
        List<Cards> list = new ArrayList<>();
        for(int i=0; i<num; ++i){
            Cards card = deck.get(i);
            list.add(card);
        }
        return list;
    }
}
