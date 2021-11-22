package com.example.dk;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Listekala extends AppCompatActivity {

    //a list to store all the products
    List<kala> productList;
    int finalprice =0 ;
    //the recyclerview
    TextView fullPricetextview;

    RecyclerView recyclerView;
//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            RecyclerView.ViewHolder viewHolder = ((RecyclerView.ViewHolder) v.getTag());
//            if(viewHolder.getItemId()==R.id.addCMButton){
//                Intent intent = new Intent(this, Nazarat.class);
//                startActivity(intent);
//            }
//            else {
//                int a = viewHolder.getAdapterPosition();
//                finalprice += productList.get(a).getPrice();
//                fullPricetextview.setText(String.valueOf(finalprice));
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listekala);
        fullPricetextview = findViewById(R.id.allprices);
        finalprice =0;
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();


        //adding some items to our list
        DatabaseAccess dbAccess = new DatabaseAccess(this);
        final Cursor CR = dbAccess.getDb().rawQuery("SELECT * FROM Kala",null);
        CR.moveToFirst();
//        do {
//            productList.add(
//                    new kala(
//                            Integer.valueOf(CR.getString(0)),
//                            CR.getString(1),
//                            CR.getString(3)+" % off",
//                            Double.parseDouble(CR.getString(4)),
//                            Double.parseDouble(CR.getString(2)),
//                            0
//            ));
//        }while(CR.moveToNext());

        productList.add(
                new kala(
                        1,
                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "0% off",
                        10,
                        60000,
                        R.drawable.macbook));

        productList.add(
                new kala(
                        2,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "0% off",
                        9,
                        20000,
                        R.drawable.dellinspiron));

        productList.add(
                new kala(
                        3,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "0% off",
                        7,
                        35000,
                        R.drawable.surface));

        //creating recyclerview adapter
        ProductAdapter adapter = new ProductAdapter(this, productList);
        adapter.setOnClickListener((v,i) -> {
            RecyclerView.ViewHolder viewHolder = ((RecyclerView.ViewHolder) v.getTag());
            if(i==1){
                Intent intent = new Intent(this, Nazarat.class);
                startActivity(intent);
            }
            else {
                int a = viewHolder.getAdapterPosition();
                finalprice += productList.get(a).getPrice();
                fullPricetextview.setText(String.valueOf(finalprice));
            }
        });
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        fullPricetextview.setText(String.valueOf(finalprice));


        //gotoMAP
        final Intent GTM = new Intent(this,MapsActivity.class);
        final Intent GTNAZARAT = new Intent(this,Nazarat.class);
        final Button gotoMap =  findViewById(R.id.gotoMap);
        //final Button gotoNazaratButton = findViewById(R.id.nazarat);
        gotoMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivity(GTM);
            }
        });
//        gotoNazaratButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View V) {
//                startActivity(GTNAZARAT);
//            }
//        });


    }

}
