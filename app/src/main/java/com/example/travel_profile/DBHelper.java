package com.example.travel_profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,"Userdata.sqlite", null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)  {
        db.execSQL("create Table Userdetails(name TEXT primary key, mobile TEXT, email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists UserDetails");
    }

    public Boolean updateuserdata(String name, String mobile, String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("mobile", mobile);
        contentValues.put("email", email);
        DB.update("UserDetails", contentValues, "name=?", new String[]{name});
        DB.update("UserDetails", contentValues, "mobile=?", new String[]{mobile});
        DB.update("UserDetails", contentValues, "email=?", new String[]{email});
        DB.close();
//        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
//        if (cursor.getCount() > 0) {
//            long result = DB.update("UserDetails", contentValues, "name=?", new String[]{name});
//            return result != -1;
//        }
//        else {
//            return false;
//        }
        return null;
    }
    public Boolean adduserdata(String name, String mobile, String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("mobile", mobile);
        contentValues.put("email", email);
        long result = DB.insert("UserDetails", null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }

    }

}


