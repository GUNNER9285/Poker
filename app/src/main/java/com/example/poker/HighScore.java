package com.example.poker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.poker.adapter.ScoreAdapter;
import com.example.poker.db.ScoreDb;
import com.example.poker.model.ScoreItem;

import java.util.ArrayList;

public class HighScore extends AppCompatActivity {

    private ScoreDb helper;
    private SQLiteDatabase db;
    private ArrayList<ScoreItem> scoreItemsList = new ArrayList<>();
    private ScoreAdapter adapter;

    private final ChipsData chipsData = ChipsData.getInstance();   // global

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        helper = new ScoreDb(this);
        db = helper.getReadableDatabase();

        loadDb();

        adapter = new ScoreAdapter(this,R.layout.item,scoreItemsList);

        ListView lv = findViewById(R.id.ListViewG);
        lv.setAdapter(adapter);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        // insert into table
        ContentValues cv = new ContentValues();
        cv.put(ScoreDb.col_score, score);
        db.insert(ScoreDb.table_name,null,cv);
        loadDb();
        adapter.notifyDataSetChanged();
    }

    private void loadDb() {
        Cursor cursor = db.query(
                ScoreDb.table_name,
                null,
                null,
                null,
                null,
                null,
                "score DESC"
        );

        scoreItemsList.clear();

        int i = 1;
        while(cursor.moveToNext())
        {
            if(i == 11){
                break;
            }
            int rank = i;
            int id = cursor.getInt(cursor.getColumnIndex(ScoreDb.col_id));
            int score = cursor.getInt(cursor.getColumnIndex(ScoreDb.col_score));

            ScoreItem item = new ScoreItem(id,score,rank);
            scoreItemsList.add(item);
            ++i;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
