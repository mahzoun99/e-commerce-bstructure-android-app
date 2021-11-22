package com.example.dk;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SUActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su);

        final EditText userSU = findViewById(R.id.phoneSU);
        final EditText passSU = findViewById(R.id.passwordSU);
        Button SUN = findViewById(R.id.singupSU);

        final DatabaseAccess dbAccess = new DatabaseAccess(this);

        final Intent BacktoSI = new Intent(this,MainActivity.class);


        SUN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String passworD = userSU.getText().toString();
                String usernamE = passSU.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put("user",usernamE);
                contentValues.put("pass",passworD);

                dbAccess.getDb().insert("User",null , contentValues);

                startActivity(BacktoSI);
            }
        });
    }
}
