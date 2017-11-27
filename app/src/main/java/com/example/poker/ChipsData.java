package com.example.poker;

/**
 * Created by GUNNER on 25/11/2560.
 */

public class ChipsData {
    private static ChipsData sInstance;
    public int point = 4000;
    public int bet = 1000;
    public static ChipsData getInstance(){
        if(sInstance == null){
            sInstance = new ChipsData();
        }
        return sInstance;
    }
}
