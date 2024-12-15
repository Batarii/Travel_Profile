package com.example.travel_profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,"Userdata.db", null,2);

    }

    @Override
    public void onCreate(SQLiteDatabase db)  {
        db.execSQL("create Table Userdetails(name TEXT primary key, mobile TEXT, email TEXT, image TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists UserDetails");
        onCreate(db);
    }
    public Boolean checkUserExists(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from UserDetails where name = ?", new String[]{name});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return exists;
    }

    public boolean updateuserdata(String name, String mobile, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("mobile", mobile);
        contentValues.put("email", email);
        int result = db.update("UserDetails", contentValues, "name=?", new String[]{name});
        db.close();
        return result > 0;
    }

    public boolean adduserdata(String name, String mobile, String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("mobile", mobile);
        contentValues.put("email", email);
        long result = DB.insert("UserDetails", null, contentValues);
        DB.close();
        return result != -1;
    }
}



