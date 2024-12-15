package com.example.travel_profile;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
//pembatas
private SQLiteDatabase mDb;
private SQLiteOpenHelper mDbHelper;
//pembatas

    TextView name, mobile, email;
    Button update;
    DBHelper DB;

    //
    de.hdodenhof.circleimageview.CircleImageView profileImageView, cameraButton;
//

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        update = findViewById(R.id.button_update);


        DB = new DBHelper(this);
        //perubahan

//perubahan



        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String mobileTXT = mobile.getText().toString();
                String emailTXT = email.getText().toString();

                if (DB.checkUserExists(nameTXT)) {
                    Boolean checkUpdateData = DB.updateuserdata(nameTXT, mobileTXT, emailTXT);
                    if (checkUpdateData) {
                        Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Boolean checkAddData = DB.adduserdata(nameTXT, mobileTXT, emailTXT);
                    if (checkAddData) {
                        Toast.makeText(MainActivity.this, "New Entry Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Add Entry Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
//
private static final String IMAGE = "image"; // Nama kolom untuk menyimpan gambar
    private static final String IMAGE_ID = "image_id"; // Kolom ID gambar
    private static final String IMAGES_TABLE = "ImagesTable"; // Nama tabel gambar

    public byte[] getImageBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    public void insertImage(byte[] imageBytes){
        ContentValues cv = new ContentValues();
        cv.put(IMAGE, imageBytes);
        mDb.insert(IMAGES_TABLE,null,cv);
    }
    public byte[] retreiveImageFromDB(){
        Cursor cur = mDb.query(false,IMAGES_TABLE,new String[] {IMAGE_ID,IMAGE},
                null, null, null, null,
                IMAGE_ID + "DESC","1");
        if (cur.moveToFirst()){
            @SuppressLint("Range") byte[] blob = cur.getBlob(cur.getColumnIndex(IMAGE));
            cur.close();
            return blob;
        }
        cur.close();
        return null;
    }




//
}