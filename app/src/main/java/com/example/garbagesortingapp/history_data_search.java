package com.example.garbagesortingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class history_data_search extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_data_search);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        setListeners();
    }

    private void setListeners() {
        //查询回收物品信息——————————————————————————————————————————————————————————————————————————————
        Button recyclegarbagedatas = findViewById(R.id.recycle_garbage_data);
        recyclegarbagedatas.setOnClickListener(view -> {
           Intent intent = new Intent(this,show_of_data_of_recycle_garbage_data.class);
           startActivity(intent);
        });
        //查询回收物品信息——————————————————————————————————————————————————————————————————————————————

        //查询历史垃圾分类信息———————————————————————————————————————————————————————————————————————————
        Button garbagesortingdatas = findViewById(R.id.garbage_sorting_data);
        garbagesortingdatas.setOnClickListener(view -> {
            Intent intent = new Intent(history_data_search.this,show_of_data_of_garbage_sorting.class);
            startActivity(intent);
        });
        //查询历史垃圾分类信息———————————————————————————————————————————————————————————————————————————

        //查询文本输入历史———————————————————————————————————————————————————————————————————————————————
        Button insertdatas = findViewById(R.id.text_insert_data);
        insertdatas.setOnClickListener(view -> {
            Intent intent = new Intent(this,show_of_insert_data.class);
            startActivity(intent);
        });
        //查询文本输入历史——————————————————————————————————————————————————————————————————————————————

        Button back = findViewById(R.id.back6);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
    }
}
