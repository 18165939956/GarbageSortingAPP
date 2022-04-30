package com.example.garbagesortingapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class search_by_recycle_garbage_name extends AppCompatActivity {
    @SuppressLint("LongLogTag")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        setContentView(R.layout.search_for_recyclegarbage_data);
        /*setListener();*/
        //声明变量————————————————————————————————————————————————————————————————————————————————————
        MyDatebaseHelper2 dbHelper2 = new MyDatebaseHelper2(this,"recycle_garbage_File",null,1);
        //声明变量————————————————————————————————————————————————————————————————————————————————————

        //拿到上一个程序的intent中携带的数据——————————————————————————————————————————————————————————————
        Intent intent = getIntent();
        String the_style_to_search = intent.getStringExtra("what_style_to_search");
        //拿到上一个程序的intent中携带的数据——————————————————————————————————————————————————————————————

        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————
        Button search = findViewById(R.id.button_search);
        EditText editText = findViewById(R.id.search_for_data);
        Button back = findViewById(R.id.back3);
        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————

        search.setOnClickListener(view -> {
            //拿到EditText中的内容————————————————————————————————————————————————————————————————————————
            String data_base = editText.getText().toString();
            //拿到EdixText中的内容————————————————————————————————————————————————————————————————————————

            TextView textView = findViewById(R.id.textViewinsearch);
            SQLiteDatabase db = dbHelper2.getWritableDatabase();
            Cursor cursor = db.query("garbage_book",null,""+ the_style_to_search + " = ?",new String[]{""+data_base},null,null,null );
            if (cursor.moveToFirst()){
                do{
                    //遍历Cursor对象,并将结果显示在界面上(c)
                    StringBuilder stringBuilder = new StringBuilder();
                    @SuppressLint("Range") String garbage_name = cursor.getString(cursor.getColumnIndex("garbage_name"));
                    @SuppressLint("Range") String recycle_time = cursor.getString(cursor.getColumnIndex("recycle_time"));
                    @SuppressLint("Range") String recycleplace = cursor.getString(cursor.getColumnIndex("recycleplace"));
                    @SuppressLint("Range") String recyclesort = cursor.getString(cursor.getColumnIndex("recyclesort"));
                    @SuppressLint("Range") String ps = cursor.getString(cursor.getColumnIndex("ps"));
                    Log.d("ddddddddddddddddddddddddddddddddddddddddddddddddd",garbage_name);
                    stringBuilder.append(garbage_name).append("\n")
                            .append(recycle_time).append("\n")
                            .append(recycleplace).append("\n")
                            .append(recyclesort).append("\n")
                            .append(ps).append("\n\n");
                    Log.d("ddddddddddddddddddddddddddddddddddddddddddddddddd",stringBuilder.toString());
                    textView.append(stringBuilder);
                }while (cursor.moveToNext());
            }
            cursor.close();
        });

        back.setOnClickListener(view -> {
            Intent intent1 = new Intent(this,search_of_data_recycle_garbage_data.class);
            startActivity(intent1);
        });


    }
    }

    /*@SuppressLint("LongLogTag")*/
    /*private void setListener() {*/
        /*//声明变量————————————————————————————————————————————————————————————————————————————————————
        MyDatebaseHelper2 dbHelper2 = new MyDatebaseHelper2(this,"recycle_garbage_File",null,1);
        //声明变量————————————————————————————————————————————————————————————————————————————————————

        //拿到上一个程序的intent中携带的数据——————————————————————————————————————————————————————————————
        Intent intent = getIntent();
        String the_style_to_search = intent.getStringExtra("what_style_to_search");
        //拿到上一个程序的intent中携带的数据——————————————————————————————————————————————————————————————

        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————
        Button search = findViewById(R.id.button_search);
        EditText editText = findViewById(R.id.search_for_data);
        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————

        //拿到EditText中的内容————————————————————————————————————————————————————————————————————————
        String data_base = editText.getText().toString();
        //拿到EdixText中的内容————————————————————————————————————————————————————————————————————————

        search.setOnClickListener(view -> {
                TextView textView = findViewById(R.id.textViewinsearch);
                SQLiteDatabase db = dbHelper2.getWritableDatabase();
                Cursor cursor = db.query("garbage_book",null,""+ the_style_to_search + " = ?",new String[]{""+data_base},null,null,null );
                if (cursor.moveToFirst()){
                    do{
                        //遍历Cursor对象,并将结果显示在界面上
                        StringBuilder stringBuilder = new StringBuilder();
                        @SuppressLint("Range") String garbage_name = cursor.getString(cursor.getColumnIndex("garbage_name"));
                        @SuppressLint("Range") String recycle_time = cursor.getString(cursor.getColumnIndex("recycle_time"));
                        @SuppressLint("Range") String recycleplace = cursor.getString(cursor.getColumnIndex("recycleplace"));
                        @SuppressLint("Range") String recyclesort = cursor.getString(cursor.getColumnIndex("recyclesort"));
                        @SuppressLint("Range") String ps = cursor.getString(cursor.getColumnIndex("ps"));
                        stringBuilder.append(garbage_name).append("\n")
                                .append(recycle_time).append("\n")
                                .append(recycleplace).append("\n")
                                .append(recyclesort).append("\n")
                                .append(ps).append("\n");
                        Log.d("ddddddddddddddddddddddddddddddddddddddddddddddddd",stringBuilder.toString());
                        textView.append(stringBuilder);
                    }while (cursor.moveToNext());
                }
                cursor.close();
        });


    }*/
/*}*/
