package com.example.garbagesortingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EnterTheSearchState extends AppCompatActivity {
    private final List<top_search> topSearchList = new ArrayList<>();
    TextView responseText;//定义一个TextView给后面热搜返回的数据作为载体

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_the_search_state);

        //隐藏系统自带的标题栏————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        //隐藏系统自带的标题栏————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

        //导入recyleview中的数据————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
        inittop_search();
        //导入recyleview中的数据————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

        //定义各种变量的声明—————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        top_search_Adapter adapter = new top_search_Adapter(topSearchList);
        recyclerView.setAdapter(adapter);
        responseText = findViewById(R.id.top_search);
        EditText editText = findViewById(R.id.select01);
        //定义各种变量的声明—————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————



        //搜索界面中查询垃圾分类信息—————————拿到在EditText中输入的数据———————点击搜索按钮跳转到新活动中进行网络请求——————————————————————————————————————————————————————————————————————————
        ImageButton editText_search = findViewById(R.id.search);
        editText_search.setOnClickListener(view -> {
            String garbage_name = editText.getText().toString();
            //保存输入框中的内容到数据库中————————————————————————————————————————————————————————————————————
            MyDatabaseHelper3 dbHelper3 = new MyDatabaseHelper3(this, "INSERTDATA", null, 1);
            SQLiteDatabase db = dbHelper3.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("data",garbage_name);
            db.insert("INSERTDATA",null,values);
            //保存输入框中的内容到数据库中————————————————————————————————————————————————————————————————————
            Intent intent = new Intent(EnterTheSearchState.this,JSON_of_top_search.class);
            intent.putExtra("name",garbage_name);
            startActivity(intent);
        });
        //搜索界面中查询垃圾分类信息—————————拿到在EditText中输入的数据———————点击搜索按钮跳转到新活动中进行网络请求——————————————————————————————————————————————————————————————————————————

        //快速搜索中的item点击事件及其响应结果——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
        adapter.setOnItemClickListener(new top_search_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String top_search) {
                //因为携带的数据较少，直接在intent中使用.put函数携带数据即可
                Intent intent = new Intent(EnterTheSearchState.this,JSON_of_top_search.class);
                intent.putExtra("name",top_search);
                startActivity(intent);
            }
        });
        //快速搜索中的item点击事件及其响应结果——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————


        //通过热搜按钮请求网络数据————网络上垃圾的热搜————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
        Button real_top_search = findViewById(R.id.real_top_search);
        real_top_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String con_url = "http://api.tianapi.com/hotlajifenlei/index?key=246c49fc2f943c1188064bde17885c0e";
                String jsonResult = sendHttprequest_get(con_url);
                System.out.println(jsonResult);
                /*HttpUtils.sendHttpRequest(con_url, new HttpUtils.HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        Log.d("ENTER","SUCCESS!");
                    }
                    @Override
                    public void onError(Exception e) {
                    }
                });*/
            }
        });
        //通过热搜按钮请求网络数据————网络上垃圾的热搜———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    }

    //通过热搜按钮请求网络数据————网络上垃圾的热搜——————————子线程中得到数据并返回————————————————————————————————————————————————————————————————————————————————————————————————
    private String sendHttprequest_get(String con_url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader;
                String result ;
                StringBuilder sbf = new StringBuilder();
                try {
                    URL url = new URL(con_url);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();//返回一个HttpURLConnection对象
                    connection.setRequestMethod("GET");
                    InputStream is = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    String strRead;
                    while ((strRead = reader.readLine()) != null) {
                        sbf.append(strRead);
                        sbf.append("\r\n");
                    }
                    reader.close();
                    result = sbf.toString();
                    showResponse(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return con_url;
    }
    //通过热搜按钮请求网络数据————网络上垃圾的热搜—————子线程中得到数据并返回———————————————————————————————————————————————————————————————————————————————————————————————————————


    //通过热搜按钮请求网络数据————网络上垃圾的热搜—————子线程中得到数据并返回—————解析JSON数据并显示————————————————————————————————————————————————————————————————————————————————————
    private void showResponse(final String result) {
        runOnUiThread(() -> {
            try {
                JSONObject jsonObjectALL = new JSONObject(result);
                JSONArray jsonArray = jsonObjectALL.getJSONArray("newslist");
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.optString("name",null);
                    int type = jsonObject.optInt("type",0);
                    int index = jsonObject.optInt("index",0);
                    Log.d("EnterTheSearchState","name" + name +"type" + type +"index" + index);
                    StringBuilder finalresult = new StringBuilder();
                    finalresult.append("名字：  ").append(name).append("\n").append("所属类型：  ").append(type).append("\n").append("点击次数：   ").append(index).append("\n\n\n");
                    String show = finalresult.toString();
                    responseText.append(show);
                }
                /*reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String strRead;
                while ((strRead = reader.readLine()) != null) {
                    sbf.append(strRead);
                    sbf.append("\r\n");
                }
                reader.close();
                result = sbf.toString();*/
                /*JSONArray jsonArray = new JSONArray(result);*/
                /*JSONObject object = new JSONObject(result);
                JSONArray jsonArray = object.getJSONArray("newlists");

                for (int i = 0;i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    TOP_SEARCH_response top_search_response = new TOP_SEARCH_response();
                    top_search_response.setCode(jsonObject.getInt("code"));
                    top_search_response.setMsg(jsonObject.getString("msg"));*/
                    /*top_search_response.setTop_sear_list();
                    String name = jsonObject.getString("name");
                    int type = jsonObject.getInt("index");*/
                    /*TOP_SEARCH_response top_search_response = new TOP_SEARCH_response();
                    top_search_response.setTop_sear_list(jsonArray.);
                    top_search_response.setType(jsonObject.getInt("type"));
                    top_search_response.setIndex(jsonObject.getInt("index"));
                    responseText.append(top_search_response.toString());*/
//                    }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            /*responseText.setText(result);*/
        });
    }
    //通过热搜按钮请求网络数据————网络上垃圾的热搜—————子线程中得到数据并返回—————解析JSON数据并显示————————————————————————————————————————————————————————————————————————————————————



    //定义快速搜索中的内容——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    private void inittop_search() {
        top_search milk_carton = new top_search("牛奶盒",R.drawable.point_01);
        topSearchList.add(milk_carton);
        top_search plastic_bags = new top_search("塑料袋",R.drawable.point_01);
        topSearchList.add(plastic_bags);
        top_search egg_shell = new top_search("鸡蛋壳",R.drawable.point_01);
        topSearchList.add(egg_shell);
        top_search glass_bottles = new top_search("玻璃瓶",R.drawable.point_01);
        topSearchList.add(glass_bottles);
        top_search reed_leaves = new top_search("粽叶",R.drawable.point_01);
        topSearchList.add(reed_leaves);
        top_search hair = new top_search("头发",R.drawable.point_01);
        topSearchList.add(hair);
        top_search corn_cob = new top_search("蛋壳",R.drawable.point_01);
        topSearchList.add(corn_cob);
        top_search melon_seed_shells = new top_search("瓜子壳",R.drawable.point_01);
        topSearchList.add(melon_seed_shells);
        top_search pets = new top_search("粪便",R.drawable.point_01);
        topSearchList.add(pets);
    }
    //定义快速搜索中的内容——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
}
