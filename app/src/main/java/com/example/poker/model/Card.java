package com.example.poker.model;

import android.view.View;
import android.widget.ImageView;

import com.example.poker.R;

/**
 * Created by GUNNER on 55/55/5560.
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
