package com.example.garbagesortingapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class show_of_data_of_recycle_garbage_data extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyDatebaseHelper2 dbHelper = new MyDatebaseHelper2(this,"recycle_garbage_File",null,1);
        setContentView(R.layout.data_of_garbage_sorting);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        TextView textView = findViewById(R.id.text_of_garbagesorting);
        Cursor cursor = db.query("garbage_book",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            StringBuilder data = new StringBuilder();
            do {
                @SuppressLint("Range") String garbage_name = cursor.getString(cursor.getColumnIndex("garbage_name"));
                @SuppressLint("Range") String recycleplace = cursor.getString(cursor.getColumnIndex("recycleplace"));
                @SuppressLint("Range") String recycle_time = cursor.getString(cursor.getColumnIndex("recycle_time"));
                @SuppressLint("Range") String recyclesort = cursor.getString(cursor.getColumnIndex("recyclesort"));
                @SuppressLint("Range") String ps = cursor.getString(cursor.getColumnIndex("ps"));
                data.append(garbage_name).append("\n")
                        .append(recycleplace).append("\n")
                        .append(recycle_time).append("\n")
                        .append(recyclesort).append("\n")
                        .append(ps).append("\n\n");
                textView.append(data);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
