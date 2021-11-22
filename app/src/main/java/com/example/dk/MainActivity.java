package com.example.dk;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseAccess dbAccess = new DatabaseAccess(this);
        final Cursor CR = dbAccess.getDb().rawQuery("SELECT * FROM User",null);

        final EditText password = findViewById(R.id.password);
        final EditText phone =  findViewById(R.id.phone);

        Button SI =  findViewById(R.id.singin);
        Button SUA =  findViewById(R.id.singup);

        final Intent SIintent = new Intent(this,Listekala.class);
        final Intent SUintent = new Intent(this,SUActivity.class);

        final TextView Errortext = findViewById(R.id.Errortext);

        SI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String phonenum = phone.getText().toString();
                String pass = password.getText().toString();
                CR.moveToFirst();
                int i =0;
                boolean phoneErrorbool = false;
                do{

                    if(phonenum.equals(CR.getString(0)) ){
                        phoneErrorbool = true;
                        if(pass.equals(CR.getString(1))){
                            startActivity(SIintent);
                            break;
                        }
                        else{
                            Errortext.setText("Wrong Password");
                            break;
                        }
                    }

                }while(CR.moveToNext());
                if(phoneErrorbool==false)
                    Errortext.setText("Phone Number Not Found!");

            }
        });

        SUA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivity(SUintent);
            }
        });
    }
}
