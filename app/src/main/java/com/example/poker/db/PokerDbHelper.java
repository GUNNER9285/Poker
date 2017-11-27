package com.example.poker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GUNNER on 27/11/2560.
 */

public class PokerDbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "poker.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "score";
    public static final String COL_ID = "_id";
    public static final String COL_RANK = "rank";
    public static final String COL_SCORE = "score";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_RANK + " INTEGER, "
            + COL_SCORE + " INTEGER)";

    public PokerDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) { //insert data
        ContentValues cv = new ContentValues();
        cv.put(COL_RANK, 1);
        cv.put(COL_SCORE, 77777);
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_RANK, 3);
        cv.put(COL_SCORE, 4000);
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_RANK, 2);
        cv.put(COL_SCORE, 5555);
        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
