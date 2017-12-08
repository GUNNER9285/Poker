package com.example.poker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // ป้องกันไม่ให้ผู้เล่นกด back
    @Override
    public void onBackPressed() {

    }
    // เมื่อแตะที่ Play Game จะไปเรียก Game.class
    public void pressPlayGame(View v){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
    // เมื่อแตะที่ How to Play จะไปเรียก HowToPlay.class
    public void pressHowToPlay(View v){
        Intent intent = new Intent(this, HowToPlay.class);
        startActivity(intent);
    }
    // เมื่อแตะที่ High Score จะไปเรียก HighScore.class
    public void pressHighScore(View v){
        Intent intent = new Intent(this, HighScore.class);
        startActivity(intent);
    }
    // เมื่อแตะที่ Quit จจะทำการปิดหน้า Activity ทั้งหมด
    public  void pressExit(View v) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
