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
        db.execSQL("CREATE TABLE users(email TEXT PRIMARY KEY, name TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    public boolean insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",user.getEmail());
        contentValues.put("name",user.getName());
        contentValues.put("password",user.getPassword());
        long result  = db.insert("users", null, contentValues);
        if(result==-1){
            return false;
        } else {
                return true;
        }
    }

    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?",  new String[]{email});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }
    public String getNameByEmail(String email){
        try{SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        if(cursor.getCount()>0){
            int index = cursor.getColumnIndex("name");
            if (index!=-1) {
                return cursor.getString(index);
            }
        }
        return "";
        } catch (Exception e){
            return "";
        }
    }
}
