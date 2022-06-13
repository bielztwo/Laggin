package com.example.laggin;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.apache.http.params.CoreConnectionPNames;

public class Database extends SQLiteOpenHelper {
    public  Database (Context context){
        super(context, "loginsql.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase data) {
        data.execSQL("CREATE TABLE session(id integer PRIMARY KEY, login text)");
        data.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, username text, password text)");
        data.execSQL("INSERT INTO session(id, login) VALUES (1, 'No DATA')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase data, int oldVersion, int newVersion) {
        data.execSQL("DROP TABLE IF EXISTS session");
        data.execSQL("DROP TABLE IF EXISTS user");
        onCreate(data);
    }
    public Boolean checkSession(String sessionValues){
     SQLiteDatabase data = this.getReadableDatabase();
     Cursor cursor= data.rawQuery("SELECT * FROM session WHERE login = ?", new String[]{sessionValues});
     if(cursor.getCount()>0){
         return true;
     }
     else{
         return false;
     }
    }
    public Boolean upgradeSession (String sessionValues, int id){
        SQLiteDatabase data = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", sessionValues);
        long update = data.update("session", contentValues, "id" + id, null);
        if(update == -1){
            return false;
        }
        else
        {
            return true;
        }
    }
    public Boolean insertUser(String username, String password){
        SQLiteDatabase data = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long insert = data.insert("user", null, contentValues);
        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean checkLogin(String username, String password) {
        SQLiteDatabase data = this.getReadableDatabase();
        Cursor cursor = data.rawQuery("SELECT * FROM user WHERE username = ? AND password =?", new String[]{username, password});
        if (cursor.getCount() > 0){
            return true;
    }
        else
        {
            return false;
        }
    }
}
