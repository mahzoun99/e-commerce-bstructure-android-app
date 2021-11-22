package com.example.dk;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Nazarat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nazarat);
        final DatabaseAccess dbAccess = new DatabaseAccess(this);
        final Cursor CR2 = dbAccess.getDb().rawQuery("SELECT * FROM Nazar",null);
        TextView showComments = findViewById(R.id.showCommnets);
        showComments.setText("\n\n\n");
        while(!CR2.isLast()) {
            CR2.moveToNext();
            showComments.setText(showComments.getText()+CR2.getString(2)+"\n");

        }
        //showComments.setText(showComments.getText()+"aaaaaaaaaaa\nbbbbbbbbb\n");

        final ContentValues contentValues = new ContentValues();
        final Button submitCM = findViewById(R.id.addCMButton);
        final EditText CM = findViewById(R.id.addCM);
        final Intent donewithcm = new Intent(this,Listekala.class);
        submitCM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                contentValues.put("nazar",CM.getText().toString());
                dbAccess.getDb().insert("Nazar",null,contentValues);
                startActivity(donewithcm);
            }
        });

    }
    public void doIT (View v){
        Intent intent = new Intent(this,Nazarat.class);
        startActivity(intent);
    }
}
