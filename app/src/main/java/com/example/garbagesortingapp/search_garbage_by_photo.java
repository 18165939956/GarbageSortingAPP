package com.example.garbagesortingapp;

import static com.example.garbagesortingapp.JSON_of_top_search.md5;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class search_garbage_by_photo /*extends AppCompatActivity*/ {
}
/*
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_garbage_by_photo);
        ImageButton garbage_camera = findViewById(R.id.camera_Button);
*/


/*<<<<<<< HEAD
        String imgBase64 = "drawable://" + R.drawable.camera;
        if (TextUtils.isEmpty(imgBase64)){
        }


        //获取时间戳——————————————————————————————————————————————————————————————————————————————————
        long datas = System.currentTimeMillis();
        long strNowDataTime = getCurrentTime(datas);
        //获取时间戳——————————————————————————————————————————————————————————————————————————————————

        //直接定义城市的地址
        String city_id = "310000";
=======*/
        /*String imgBase64 = "drawable://" + R.drawable.camera;
        if (TextUtils.isEmpty(imgBase64)){
        }*/


        /*//获取时间戳——————————————————————————————————————————————————————————————————————————————————
        long datas = System.currentTimeMillis();
        long strNowDataTime = getCurrentTime(datas);
        //获取时间戳——————————————————————————————————————————————————————————————————————————————————
*/
        //直接定义城市的地址
        /*String city_id = "310000";
>>>>>>> 0bf80c1 (Initial commit)
        //给secretkey+timeMD5加密————————————————————————————————————————————————————————————————————
        String md5 = "ac3f294250da776cbf2df73874c17e6e" + strNowDataTime;
        String sign = md5(md5);
        //给secretkey+timeMD5加密————————————————————————————————————————————————————————————————————
        //定义快捷搜索中的请求体并输入数据—————————————————————————————————————————————————————————————————
        JSONObject jsonParam = new JSONObject();
        try {

            String imgBase64 = imageToBase64("drawable//" + R.drawable.camera);
            jsonParam.put("imgBase64",imgBase64);
            jsonParam.put("cityId",city_id);
            String json = jsonParam.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //定义快捷搜索中的请求体并输入数据—————————————————————————————————————————————————————————————————
<<<<<<< HEAD

        new Thread(new Runnable() {
=======
*/
        /*new Thread(new Runnable() {
>>>>>>> 0bf80c1 (Initial commit)
            @SuppressLint("LongLogTag")
            @Override
            public void run() {
                //发送请求——————获取网络数据————————————————————————————————————————————————————————————
                try {

                    String stringurl = "https://aiapi.jd.com/jdai/garbageTextSearch?appkey=6d7e974611787152cdba1cd943c9fcb4&timestamp="+strNowDataTime+"&sign="+sign;
                    URL url = new URL(stringurl);

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                    httpURLConnection.setRequestProperty("Content-Length",String.valueOf(data.length));
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(jsonParam.toString().getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                    outputStream.close();
                    int response = httpURLConnection.getResponseCode();
                    if (response == 200){
                        //处理数据
                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        String resultData = reader.readLine();
                        //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        // byte[] data1 = new byte[1024];
                        // int len = data.length;
                        // try {
                        //     byteArrayOutputStream.write(data1,0,len);
                        // } catch (Exception exception) {
                        //     exception.printStackTrace();
                        // }
                        // String resultData;
                        // BufferedReader reader;
                        // StringBuilder str = new StringBuilder();
                        // InputStream is =httpURLConnection.getInputStream();
                        // reader = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8));
                        // while((resultData = reader.readLine()) != null){
                        //     str.append(resultData);
                        //     str.append("\r\n");
                        // }
                        // reader.close();
                        // resultData = str.toString();
                        Log.d("dddddddddddddddddddddddddddddd",resultData);
                        showResponse(resultData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //发送请求——————获取网络数据————————————————————————————————————————————————————————————————

            //发送请求——————获取网络数据———————解析数据—————————主线程进行UI操作显示数据——————————————————————
<<<<<<< HEAD
    }

    //获取当前时间—————————————————————————————————————————————————————————————————————————————————————
=======
    }*/

    /*//获取当前时间—————————————————————————————————————————————————————————————————————————————————————
>>>>>>> 0bf80c1 (Initial commit)
    private long getCurrentTime(long datas) {
        long data1 = new Date().getTime();
        Log.d("ddddddddddddddddd", String.valueOf(data1));
        return datas;
    }
    //获取当前时间—————————————————————————————————————————————————————————————————————————————————————
    String DFSJFDS = imageToBase64("drawable//"+R.drawable.camera);
    TextView textView = findViewById(R.id.camera_textview);

    //图片转imgBase64格式—————————————————————————————————————————————————————————————————————————————
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String imageToBase64(String path){
        if (TextUtils.isEmpty(path)){
            return null;
        }
        InputStream is = null;

        byte[] data ;
        String result = null;
        try{
            is = new FileInputStream(path);
            data = new byte[is.available()];
            is.read(data);
            result = Base64.getEncoder().encodeToString(data);
            StringBuilder db = new StringBuilder();
            db.append(result);
            textView.append(db);
            Log.d("dddddddddddddddddd",result);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    return result;
    }
    //图片转imgBase64格式—————————————————————————————————————————————————————————————————————————————
<<<<<<< HEAD
=======
*/
 /*   }

    ;
>>>>>>> 0bf80c1 (Initial commit)
}*/
     /*   )*/