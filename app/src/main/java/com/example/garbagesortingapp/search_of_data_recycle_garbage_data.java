package com.example.garbagesortingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class search_of_data_recycle_garbage_data extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_of_data_recycle_garbage_data);
        setListener();
    }

    private void setListener() {
        //声明变量————————————————————————————————————————————————————————————————————————————————————
        MyDatebaseHelper2 dbHelper2 = new MyDatebaseHelper2(this,"recycle_garbage_File",null,1);
        //声明变量————————————————————————————————————————————————————————————————————————————————————

        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————
        Button search1 = findViewById(R.id.search_by_garbage_name);
        Button search2 = findViewById(R.id.search_by_garbage_time);
        Button search3 = findViewById(R.id.search_by_garbage_style);
        Button back = findViewById(R.id.back2);
        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————

        //通过物品名字进行查询——————————————————————————————————————————————————————————————————————————
        search1.setOnClickListener(view -> {
            String search_by_garbage = "garbage_name";
            Intent intent = new Intent(this,search_by_recycle_garbage_name.class);
            intent.putExtra("what_style_to_search",search_by_garbage);
            startActivity(intent);
        });
        //通过物品名字进行查询——————————————————————————————————————————————————————————————————————————

        //通过物品回收时间进行查询——————————————————————————————————————————————————————————————————————————
        search2.setOnClickListener(view -> {//2022年4月29日 2022年4月30号
            String search_by_garbage = "recycle_time";
            Intent intent = new Intent(this,search_by_recycle_garbage_name.class);
            intent.putExtra("what_style_to_search",search_by_garbage);
            startActivity(intent);
        });
        //通过物品回收时间进行查询——————————————————————————————————————————————————————————————————————————

        //通过回收物品类别进行查询——————————————————————————————————————————————————————————————————————————
        search3.setOnClickListener(view -> {
            String search_by_garbage = "recyclesort";
            Intent intent = new Intent(this,search_by_recycle_garbage_name.class);
            intent.putExtra("what_style_to_search",search_by_garbage);
            startActivity(intent);
        });
        //通过回收物品类别进行查询——————————————————————————————————————————————————————————————————————————

        back.setOnClickListener(view -> {
          Intent intent = new Intent(this,garbagesorting_management.class);
          startActivity(intent);
        });

    }
}
