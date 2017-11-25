package com.example.poker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighScore extends AppCompatActivity {
    private final ChipsData chipsData = ChipsData.getInstance();   // global
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        String total = "Point : "+chipsData.point;
        ((TextView) findViewById(R.id.totalPoint)).setText(total);

        Button btnRandom = (Button) findViewById(R.id.btnHome);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighScore.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
