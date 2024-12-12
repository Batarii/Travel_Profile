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

        update.setOnClickListener(this::onClick);

    }

    private void onClick(View v) {
        String nameTXT = name.getText().toString();
        String mobileTXT = mobile.getText().toString();
        String emailTXT = email.getText().toString();

        Boolean checkupdateData = DB.adduserdata(nameTXT, mobileTXT, emailTXT);
        if (checkupdateData) {
            Toast.makeText(MainActivity.this,
                    "Entry Update",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this,
                    "New Entry Not Update",
                    Toast.LENGTH_SHORT).show();
        }
    }
}