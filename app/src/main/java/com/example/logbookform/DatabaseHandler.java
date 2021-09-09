package com.example.logbookform;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "PropertyDetail";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE details " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "property_type TEXT, " +
                "bed_room TEXT, " +
                "adding_date TEXT, " +
                "monthly_rent_price TEXT, " +
                "furniture_type TEXT, " +
                "notes TEXT, " +
                "reporter_name TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS students";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
