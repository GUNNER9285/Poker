package com.example.poker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.poker.adapter.ScoreListAdapter;
import com.example.poker.db.PokerDbHelper;
import com.example.poker.model.ScoreItem;

import java.util.ArrayList;

public class HighScore extends AppCompatActivity {
    private final ChipsData chipsData = ChipsData.getInstance();   // global
    private PokerDbHelper mHelper;
    private SQLiteDatabase mDb;

    private ArrayList<ScoreItem> mScoreList = new ArrayList<>();
    private ScoreListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        mHelper = new PokerDbHelper(this);
        mDb = mHelper.getReadableDatabase();

        loadDataFromDb();

        mAdapter = new ScoreListAdapter(
                this,
                R.layout.item,
                mScoreList
        );

        ListView lv = findViewById(R.id.list_view);
        lv.setAdapter(mAdapter); // TODO: fix bug
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void loadDataFromDb() {
        Cursor cursor = mDb.query(
                PokerDbHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        mScoreList.clear(); // Clear List

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(PokerDbHelper.COL_ID));
            int rank = cursor.getInt(cursor.getColumnIndex(PokerDbHelper.COL_RANK));
            int score = cursor.getInt(cursor.getColumnIndex(PokerDbHelper.COL_SCORE));

            ScoreItem item = new ScoreItem(id, rank, score);
            mScoreList.add(item);
        }
    }
}
