package com.example.garbagesortingapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class show_of_data_of_garbage_sorting extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this,"garbage_sorting_File",null,1);
        setContentView(R.layout.data_of_garbage_sorting);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        TextView textView = findViewById(R.id.text_of_garbagesorting);
        Cursor cursor = db.query("garbageSORTING",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            StringBuilder data = new StringBuilder();
            do {
                @SuppressLint("Range") String garbage_name = cursor.getString(cursor.getColumnIndex("garbage_name"));
                @SuppressLint("Range") String cata_name = cursor.getString(cursor.getColumnIndex("cata_name"));
                @SuppressLint("Range") String city_id = cursor.getString(cursor.getColumnIndex("city_id"));
                @SuppressLint("Range") String city_name = cursor.getString(cursor.getColumnIndex("city_name"));
                @SuppressLint("Range") String ps = cursor.getString(cursor.getColumnIndex("ps"));
                data.append(garbage_name).append("\n")
                        .append(cata_name).append("\n")
                        .append(city_id).append("\n")
                        .append(city_name).append("\n")
                        .append(ps).append("\n\n");
                textView.append(data);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
