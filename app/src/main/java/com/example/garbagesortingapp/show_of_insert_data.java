package com.example.garbagesortingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class show_of_insert_data extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertdata);
        MyDatabaseHelper3 dbHelper = new MyDatabaseHelper3(this,"INSERTDATA",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        TextView textView = findViewById(R.id.insertTextView);
        Cursor cursor =db.query("insertdata",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            StringBuilder data = new StringBuilder();
            do {
                @SuppressLint("Range") String insertdata = cursor.getString(cursor.getColumnIndex("data"));
                data.append(insertdata).append("\n");
                textView.append(data);
            }while (cursor.moveToNext());
        }
        cursor.close();
        Button back = findViewById(R.id.back5);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(this,history_data_search.class);
            startActivity(intent);
        });
    }
}
