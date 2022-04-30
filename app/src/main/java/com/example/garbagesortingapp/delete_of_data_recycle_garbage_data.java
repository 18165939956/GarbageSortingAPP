package com.example.garbagesortingapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class delete_of_data_recycle_garbage_data extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_recycle_garbage_data);
        setListener();
    }

    private void setListener() {
        //声明变量————————————————————————————————————————————————————————————————————————————————————
        MyDatebaseHelper2 dbHelper2 = new MyDatebaseHelper2(this,"recycle_garbage_File",null,1);
        //声明变量————————————————————————————————————————————————————————————————————————————————————

        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————
        Button delete = findViewById(R.id.delete2);
        EditText editText = findViewById(R.id.delete_garbage_name);
        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————

        delete.setOnClickListener(view -> {
            //拿到搜索栏中的内容————————————————————————————————————————————————————————————————————————
            String garbage_name = editText.getText().toString();//蛋壳
            //拿到搜索栏中的内容————————————————————————————————————————————————————————————————————————

            //组装数据————————————————————————————————————————————————————————————————————————————————
            SQLiteDatabase db = dbHelper2.getWritableDatabase();
            db.delete("garbage_book","garbage_name = ?",new String[]{""+garbage_name});
            //组装数据————————————————————————————————————————————————————————————————————————————————
            Intent intent1 = new Intent(this,garbagesorting_management.class);
            startActivity(intent1);
            Toast.makeText(this, "delete successfully", Toast.LENGTH_SHORT).show();
        });
    }
}
