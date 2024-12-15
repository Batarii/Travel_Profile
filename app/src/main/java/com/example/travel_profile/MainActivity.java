package com.example.travel_profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


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


//
}