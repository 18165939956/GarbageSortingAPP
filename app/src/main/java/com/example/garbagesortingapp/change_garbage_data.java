package com.example.garbagesortingapp;

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

public class change_garbage_data extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_data);
        setOnListener();
    }



    private void setOnListener() {
        //声明变量————————————————————————————————————————————————————————————————————————————————————
        MyDatebaseHelper2 dbHelper2 = new MyDatebaseHelper2(this,"recycle_garbage_File",null,1);
        Intent intent = getIntent();
        String what_data_change = intent.getStringExtra("what_data_change");
        //声明变量————————————————————————————————————————————————————————————————————————————————————

        //找到布局中的控件—————————————————————————————————————————————————————————————————————————————
        Button updata = findViewById(R.id.update);
        EditText updata_data = findViewById(R.id.data_need_to_change);//鸡蛋壳  图书馆垃圾桶 2022年4月30号 可回收物 建议拿着丢食堂垃圾桶去
        EditText name_of_garbage = findViewById(R.id.data_need_how_to_change);//蛋壳
        //找到布局中的控件—————————————————————————————————————————————————————————————————————————————

        updata.setOnClickListener(view -> {
            //拿到搜索栏中的内容————————————————————————————————————————————————————————————————————————
            String data_need_to_be_change = updata_data.getText().toString();
            String data_name_of_garbage = name_of_garbage.getText().toString();
            //拿到搜索栏中的内容————————————————————————————————————————————————————————————————————————

            //组装数据————————————————————————————————————————————————————————————————————————————————
            SQLiteDatabase db = dbHelper2.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(""+what_data_change,data_need_to_be_change);
            db.update("garbage_book",values,"garbage_name = ?",new String[]{""+data_name_of_garbage});
            //组装数据————————————————————————————————————————————————————————————————————————————————
            Intent intent1 = new Intent(this,garbagesorting_management.class);
            startActivity(intent1);
            Toast.makeText(this, "updata successfully", Toast.LENGTH_SHORT).show();
        });
    }

}
