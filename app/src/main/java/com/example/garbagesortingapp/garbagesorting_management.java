package com.example.garbagesortingapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class garbagesorting_management extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garbagesortingmanagement);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        setListeners();
    }



    private void setListeners(){
        //查看回收物品信息——————————————————————————————————————————————————————————————————————————————
        Button look = findViewById(R.id.Look);
        look.setOnClickListener(view -> {
            Intent intent = new Intent(this,show_of_data_of_recycle_garbage_data.class);
            startActivity(intent);
        });
        //查看回收物品信息——————————————————————————————————————————————————————————————————————————————

        //添加回收物品信息——————————————————————————————————————————————————————————————————————————————
        Button add = findViewById(R.id.add);
        add.setOnClickListener(view -> {
            Intent intent = new Intent(this,add_of_data_recycle_garbage_data.class);
            startActivity(intent);
        });
        //添加回收物品信息——————————————————————————————————————————————————————————————————————————————

        //更改回收物品信息——————————————————————————————————————————————————————————————————————————————
        Button change = findViewById(R.id.change);
        change.setOnClickListener(view -> {
            Intent intent = new Intent(this,change_of_data_recycle_garbage_data.class);
            startActivity(intent);
        });
        //更改回收物品信息——————————————————————————————————————————————————————————————————————————————

        //删除回收物品信息——————————————————————————————————————————————————————————————————————————————
        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(view -> {
            Intent intent = new Intent(this,delete_of_data_recycle_garbage_data.class);
            startActivity(intent);
        });
        //删除回收物品信息——————————————————————————————————————————————————————————————————————————————

        //查询回收物品信息—————————————————————————————————————————————————————————————————————————————
        Button search = findViewById(R.id.search2);
        search.setOnClickListener(view -> {
            Intent intent = new Intent(this,search_of_data_recycle_garbage_data.class);
            startActivity(intent);
        });
        //查询回收物品信息—————————————————————————————————————————————————————————————————————————————

        Button back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
    }
}
