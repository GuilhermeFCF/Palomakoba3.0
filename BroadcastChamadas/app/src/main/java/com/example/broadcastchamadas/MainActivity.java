package com.example.broadcastchamadas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<IncomingNumber> numeros = new ArrayList<IncomingNumber>();
    private RecyclerAdapter recyclerAdapter;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        textView = findViewById(R.id.textView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(numeros);
        recyclerView.setAdapter(recyclerAdapter);
        readFromDb();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                readFromDb();
            }
        };

    }
    //@SuppressLint("Range");
    private void readFromDb(){
        numeros.clear();
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.readNumber(database);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                int id;
                String number = cursor.getString(cursor.getColumnIndex(DbContract.INCOMING_NUMBER));
                id = cursor.getInt(cursor.getColumnIndex("id"));
                numeros.add(new IncomingNumber(id,number));
            }
            cursor.close();
            dbHelper.close();
            recyclerAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }

    }
    protected void onResume(){
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(DbContract.UPDATE_UI_FILTER));

    }
    protected void onPause(){
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

}