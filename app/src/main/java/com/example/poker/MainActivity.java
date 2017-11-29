package com.example.poker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public void onBackPressed() {}
    public void pressPlayGame(View v){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
    public void pressHowToPlay(View v){
        Intent intent = new Intent(this, HowToPlay.class);
        startActivity(intent);
    }
    public void pressHighScore(View v){
        Intent intent = new Intent(this, HighScore.class);
        startActivity(intent);
    }
    public  void pressExit(View v) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
