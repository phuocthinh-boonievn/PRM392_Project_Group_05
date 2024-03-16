package com.example.minicapstoneproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.minicapstoneproject.Model.User;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "Login.DB";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(name TEXT PRIMARY KEY, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    public boolean insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",user.getName());
        contentValues.put("password",user.getPassword());
        long result  = db.insert("users", null, contentValues);
        if(result==-1){
            return false;
        } else {
                return true;
        }
    }

    public boolean checkName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE name = ?",  new String[]{name});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    public boolean checkNamePassword(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE name = ? AND password = ?", new String[]{name, password});
        return cursor.getCount() > 0;
    }
}
