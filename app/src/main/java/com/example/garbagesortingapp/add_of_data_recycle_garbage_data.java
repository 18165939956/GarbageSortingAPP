package com.example.garbagesortingapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class add_of_data_recycle_garbage_data extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recycle_garbage_data);
        setListener();
    }

    private void setListener(){
        //声明变量————————————————————————————————————————————————————————————————————————————————————
        MyDatebaseHelper2 dbHelper2 = new MyDatebaseHelper2(this,"recycle_garbage_File",null,1);
        //声明变量————————————————————————————————————————————————————————————————————————————————————

        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————
        Button save = findViewById(R.id.save);
        Button back = findViewById(R.id.back4);
        EditText edit1 = findViewById(R.id.recyclegarbagename);
        EditText edit2 = findViewById(R.id.recycleplace);
        EditText edit3 = findViewById(R.id.recycle_time);
        EditText edit4 = findViewById(R.id.recyclesort);
        EditText edit5 = findViewById(R.id.ps);
        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————

        back.setOnClickListener(view -> {
            Intent intent = new Intent(this,garbagesorting_management.class);
            startActivity(intent);
        });
        save.setOnClickListener(view -> {
            //拿到搜索栏中的内容————————————————————————————————————————————————————————————————————————
            String garbage_name = edit1.getText().toString();
            String recycleplace = edit2.getText().toString();
            String recycle_time = edit3.getText().toString();
            String recyclesort = edit4.getText().toString();
            String ps = edit5.getText().toString();
            //拿到搜索栏中的内容————————————————————————————————————————————————————————————————————————

            //组装数据————————————————————————————————————————————————————————————————————————————————
            SQLiteDatabase db = dbHelper2.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("garbage_name",garbage_name);//蛋壳
            values.put("recycle_time",recycle_time);//2022年4月30号
            values.put("recycleplace",recycleplace);//广东工业大学图书馆垃圾桶
            values.put("recyclesort",recyclesort);//厨余垃圾
            values.put("ps",ps);//建议拿着丢到食堂的垃圾桶去
            //组装数据————————————————————————————————————————————————————————————————————————————————

            //插入数据————————————————————————————————————————————————————————————————————————————————
            db.insert("garbage_book",null,values);
            values.clear();
            //插入数据————————————————————————————————————————————————————————————————————————————————

            Intent intent = new Intent(add_of_data_recycle_garbage_data.this,garbagesorting_management.class);
            startActivity(intent);
            Toast.makeText(this, "save successfully", Toast.LENGTH_SHORT).show();
        });
    }
}
