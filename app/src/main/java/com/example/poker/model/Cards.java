package com.example.poker.model;

import java.util.List;

/**
 * Created by GUNNER on 23/11/2560.
 */

public class Cards {
    public final String name;
    public final int image;
    public boolean status;
    public Cards(String name, int image){
        this.name = name;
        this.image = image;
        status = false;
    }
}
