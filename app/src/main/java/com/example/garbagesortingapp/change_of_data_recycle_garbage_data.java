package com.example.garbagesortingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class change_of_data_recycle_garbage_data extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_recycle_garbage_data);
        setListener();
    }

    private void setListener() {

        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————
        Button garbage_name = findViewById(R.id.garbage_name);
        Button garbage_place = findViewById(R.id.garbage_place);
        Button garbage_time = findViewById(R.id.garbage_time);
        Button garbage_sort = findViewById(R.id.garbage_sort);
        Button garbage_ps = findViewById(R.id.garbage_ps);
        Button back = findViewById(R.id.back);
        //找到布局中的控件——————————————————————————————————————————————————————————————————————————————

        //garbage_name——————————————————————————————————————————————————————————————————————————————
        garbage_name.setOnClickListener(view -> {
            String garbage_name1 = "garbage_name";
            Intent intent = new Intent(this,change_garbage_data.class);
            intent.putExtra("what_data_change",garbage_name1);
            startActivity(intent);
        });
        //garbage_name——————————————————————————————————————————————————————————————————————————————

        //garbage_place—————————————————————————————————————————————————————————————————————————————
        garbage_place.setOnClickListener(view -> {
            String garbage_place1 = "recycleplace";
            Intent intent = new Intent(this,change_garbage_data.class);
            intent.putExtra("what_data_change",garbage_place1);
            startActivity(intent);
        });
        //garbage_place—————————————————————————————————————————————————————————————————————————————

        //garbage_time—————————————————————————————————————————————————————————————————————————————
        garbage_time.setOnClickListener(view -> {
            String garbage_time1 = "recycle_time";
            Intent intent = new Intent(this,change_garbage_data.class);
            intent.putExtra("what_data_change",garbage_time1);
            startActivity(intent);
        });
        //garbage_time—————————————————————————————————————————————————————————————————————————————

        //garbage_sort—————————————————————————————————————————————————————————————————————————————
        garbage_sort.setOnClickListener(view -> {
            String garbage_sort1 = "recyclesort";
            Intent intent = new Intent(this,change_garbage_data.class);
            intent.putExtra("what_data_change",garbage_sort1);
            startActivity(intent);
        });
        //garbage_sort—————————————————————————————————————————————————————————————————————————————

        //garbage_ps—————————————————————————————————————————————————————————————————————————————————
        garbage_ps.setOnClickListener(view -> {
            String garbage_ps1 = "ps";
            Intent intent = new Intent(this,change_garbage_data.class);
            intent.putExtra("what_data_change",garbage_ps1);
            startActivity(intent);
        });
        //garbage_ps—————————————————————————————————————————————————————————————————————————————————

        //退出————————————————————————————————————————————————————————————————————————————————————————
        back.setOnClickListener(view -> {
            Intent intent = new Intent(this,garbagesorting_management.class);
            startActivity(intent);
        });
        //退出————————————————————————————————————————————————————————————————————————————————————————
    }


}
