package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "MY_DATABASE.DB";
    static final int DATABASE_VERSION = 1;

    static  final String DATABASE_TABLE = "USER_TABLE";
    static final String USER_ID = "_id";
    static final String USER_NAME = "username";
    static final String USER_PASSWORD = "password";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE +
            "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " TEXT NOT NULL, " + USER_PASSWORD + " TEXT NOT NULL);";


    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DATABASE_TAG" ,CREATE_DB_QUERY);
        db.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
    }


    public Boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.DATABASE_TABLE + " WHERE username = ?", new String[]{username});

        if (cursor.getCount()>0){
            return true;

        }
        else
            return false;
    }

    public Boolean check_user(String username, String password) {
        SQLiteDatabase database = this.getWritableDatabase();

        // Add log statements for debugging
        Log.d("DATABASE_TAG", "Checking user password - Username: " + username + ", Password: " + password);

        Cursor cursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE username = ? AND password = ?", new String[]{username.trim(), password.trim()});


        // Add log statement to print the count of matching rows
        Log.d("DATABASE_TAG", "Number of matching rows: " + cursor.getCount());

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }



}

