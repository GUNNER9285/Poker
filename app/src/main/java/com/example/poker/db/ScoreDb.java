package com.example.poker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GUNNER on 28/11/2560.
 */

// class ที่ใช้สร้าง database ของ Application
public class ScoreDb extends SQLiteOpenHelper{
    private static final String db_name = "score.db";
    private static final int db_version = 1;

    public static final String table_name = "score_table";
    public static final String col_id = "id";
    public static final String col_score = "score";

    private static final String create_table = "create table "+table_name+"("+
            col_id+" integer primary key autoincrement," +
            col_score+" integer" +
            ")";

    public ScoreDb(Context context)
    {
        super(context,db_name,null,db_version);
    }

    // สร้าง Table พร้อมใส่ข้อมูลเริ่มต้นให้กับ Table นั้น
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
        insertdata(db);
    }
    // ใส่ Score เริ่มต้นเป็น 0 ทั้งหมด
    private void insertdata(SQLiteDatabase db) {
        for(int i=0; i<10; ++i){
            ContentValues cv = new ContentValues();
            cv.put(col_score,0);
            db.insert(table_name,null,cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+table_name);
        onCreate(db);
    }
}
