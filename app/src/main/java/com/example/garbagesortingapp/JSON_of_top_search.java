package com.example.garbagesortingapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class JSON_of_top_search extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static Context CONTEXT;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_of_top_search);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        //声明变量找到控件—————————————————————————————————————————————————————————————————————————————
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        TextView textView = findViewById(R.id.JSONresponse_text);
        textView.append("你将要分类的垃圾为"  + name + "\n");
        //声明变量找到控件—————————————————————————————————————————————————————————————————————————————

        //获取时间戳——————————————————————————————————————————————————————————————————————————————————
        long datas = System.currentTimeMillis();
        long strNowDataTime = getCurrentTime(datas);
        //获取时间戳——————————————————————————————————————————————————————————————————————————————————

        //直接定义城市的地址
        String city_id = "310000";
        //给secretkey+timeMD5加密————————————————————————————————————————————————————————————————————
        String md5 = "ac3f294250da776cbf2df73874c17e6e" + strNowDataTime;
        String sign = md5(md5);
        //给secretkey+timeMD5加密————————————————————————————————————————————————————————————————————

        //定义快捷搜索中的请求体并输入数据—————————————————————————————————————————————————————————————————
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("cityId",city_id);
            jsonParam.put("text",name);
            /*String json = jsonParam.toString();*/

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //定义快捷搜索中的请求体并输入数据—————————————————————————————————————————————————————————————————

        //定义语音播报功能—————————————————————————————————————————————————————————————————————————————
        KqwSpeechCompound kqwSpeechCompound = new KqwSpeechCompound(this);
        //定义语音播报功能—————————————————————————————————————————————————————————————————————————————

        new Thread(new Runnable() {
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
                    /*httpURLConnection.setRequestProperty("Content-Length",String.valueOf(data.length));*/
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
                }//发送请求——————获取网络数据———————————————————————————————————————————————————————————
            }


            //发送请求——————获取网络数据———————解析数据—————————主线程进行UI操作显示数据——————————————————————
            private void showResponse(String resultData) {
                StringBuilder finalresult = new StringBuilder();
                runOnUiThread(() -> {
                    try {
                        JSONObject jsonObjectALL = new JSONObject(resultData);
                        JSONObject jsonObject_garbage = jsonObjectALL.getJSONObject("result");
                        JSONArray jsonArray = jsonObject_garbage.getJSONArray("garbage_info");
                        for (int i = 0;i < jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String cate_name = jsonObject.optString("cate_name");
                            String city_id = jsonObject.optString("city_id");
                            String city_name = jsonObject.optString("city_name");
                            String garbage_name = jsonObject.optString("garbage_name");
                            String ps = jsonObject.optString("ps");
                            finalresult.append("垃圾分类:   ").append(cate_name).append("\n")
                                    .append("垃圾名称:   ").append(garbage_name).append("\n")
                                    .append("城市id:   ").append(city_id).append("\n")
                                    .append("城市名称:   ").append(city_name).append("\n")
                                    .append("分类建议：   ").append(ps).append("\n");
                            textView.append(finalresult);
                            //语音播报——播报垃圾分类建议

                            //保存数据
                            Button save_response = findViewById(R.id.save_response);
                            save_response.setOnClickListener(view -> {
                                MyDatabaseHelper dbHelper = new MyDatabaseHelper(JSON_of_top_search.this, "garbage_sorting_File", null, 1);
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put("garbage_name",garbage_name);
                                values.put("cata_name",cate_name);
                                values.put("city_id",city_id);
                                values.put("city_name",city_name);
                                values.put("ps",ps);
                                db.insert("garbageSORTING",null,values);
                                Log.d("ddddddddddddddddddd", values.toString());
                                /*//语音播报——播报垃圾分类建议
                                KqwSpeechCompound kqwSpeechCompound = new KqwSpeechCompound(JSON_of_top_search.this);
                                if (values.toString().equals("")){
                                    //判断内容是否为空
                                    Log.d("res1","没有找到");
                                }else {
                                    Log.d("res1",values.toString());
                                }
                                kqwSpeechCompound.speaking(values.toString().trim());*/
                                //按了保存后回到搜索界面
                                Intent intent1 = new Intent(JSON_of_top_search.this,EnterTheSearchState.class);
                                startActivity(intent1);
                                Toast.makeText(JSON_of_top_search.this, "save successfully", Toast.LENGTH_SHORT).show();
                            });
                        }
                        //String firstresult = jsonObjectALL.optString("result");
                        // JSONArray secondresult = new JSONArray(firstresult);
                        // for (int i = 0;i < secondresult.length(); i++){
                        //     JSONObject jsonObject = secondresult.getJSONObject(i);
                        //     String cate_name = jsonObject.optString("cate_name");
                        //     String city_id = jsonObject.optString("city_id");
                        //     String city_name = jsonObject.optString("city_name");
                        //     String garbage_name = jsonObject.optString("garbage_name");
                        //     String ps = jsonObject.optString("ps");
                        //     StringBuilder finalresult = new StringBuilder();
                        //     finalresult.append("垃圾分类:   ").append(cate_name)
                        //             .append("垃圾名称:   ").append(garbage_name)
                        //             .append("城市id:   ").append(city_id)
                        //             .append("城市名称:   ").append(city_name)
                        //             .append("分类建议：   ").append(ps);
                        //     textView.append(finalresult);
                        // }
                        //JSONArray result = jsonObjectALL.getJSONArray("result");
                        //String result = jsonObjectALL.optString("result");
                        // JSONObject garbage_infoObject = new JSONObject(result);
                        // String garbage_info = garbage_infoObject.optString("garbage_info");
                        // try {
                        //     JSONArray garbage_infoArray = new JSONArray(garbage_info);
                        //     for (int i = 0; i < garbage_infoArray.length(); i++){
                        //         JSONObject jsonObject = garbage_infoArray.getJSONObject(i);
                        //         String cate_name = jsonObject.optString("cate_name");
                        //         String city_id = jsonObject.optString("city_id");
                        //         String city_name = jsonObject.optString("city_name");
                        //         String garbage_name = jsonObject.optString("garbage_name");
                        //         String ps = jsonObject.optString("ps");
                        //         StringBuilder finalresult = new StringBuilder();
                        //         finalresult.append("垃圾分类:   ").append(cate_name)
                        //                 .append("垃圾名称:   ").append(garbage_name)
                        //                 .append("城市id:   ").append(city_id)
                        //                 .append("城市名称:   ").append(city_name)
                        //                 .append("分类建议：   ").append(ps);
                        //         textView.append(finalresult);
                        //     }
                        // } catch (JSONException e) {
                        //     e.printStackTrace();
                        // }


                        //JSONArray jsonArray = jsonObjectALL.getJSONArray("garbage_info");
                        // for (int i = 0; i < jsonArray.length(); i++){
                        //     JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //     String cata_name = jsonObject.optString("cata-name",null);
                        //     int confidence = jsonObject.optInt("confidence",0);
                        //     String ps = jsonObject.optString("ps",null);
                        //     Log.d("EnterTheSearchState","name" + cata_name +"ps" + ps +"confidence" + confidence);
                        //     StringBuilder finalresult = new StringBuilder();
                        //     finalresult.append("垃圾类型为：  ").append(cata_name).append("\n").append("投放建议：  ").append(ps).append("\n").append("confidence：   ").append(confidence).append("\n\n\n");
                        //     String show = finalresult.toString();
                        //     textView.append(show);
                        // }
                        //reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                        // String strRead;
                        // while ((strRead = reader.readLine()) != null) {
                        //     sbf.append(strRead);
                        //     sbf.append("\r\n");
                        // }
                        // reader.close();
                        // result = sbf.toString();
                        /*JSONArray jsonArray = new JSONArray(result);*/
                /*JSONObject object = new JSONObject(result);
                JSONArray jsonArray = object.getJSONArray("newlists");

                for (int i = 0;i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    TOP_SEARCH_response top_search_response = new TOP_SEARCH_response();
                    top_search_response.setCode(jsonObject.getInt("code"));
                    top_search_response.setMsg(jsonObject.getString("msg"));*/
                        //top_search_response.setTop_sear_list();
                        // String name = jsonObject.getString("name");
                        // int type = jsonObject.getInt("index");
                    /*TOP_SEARCH_response top_search_response = new TOP_SEARCH_response();
                    top_search_response.setTop_sear_list(jsonArray.);
                    top_search_response.setType(jsonObject.getInt("type"));
                    top_search_response.setIndex(jsonObject.getInt("index"));
                    responseText.append(top_search_response.toString());*/
//                    }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            }
            //发送请求——————获取网络数据———————解析数据—————————主线程进行UI操作显示数据———————————————————————
        }).start();//球球了，别再忘记写了
    }

    //定义语音播报功能———————实例化自带语音对象——————————————————————————————————————————————————————————
    // private void initTTS() {
    //     //实例化自带语音对象
    //     textToSpeech = new TextToSpeech(this, status -> {
    //         if (status == textToSpeech.SUCCESS) {
    //             textToSpeech.setPitch(1.0f);//方法用来控制音调
    //             textToSpeech.setSpeechRate(1.0f);//用来控制语速
    //             //判断是否支持下面两种语言（英文/中文）
    //             int result1 = textToSpeech.setLanguage(Locale.US);
    //             int result2 = textToSpeech.setLanguage(Locale.SIMPLIFIED_CHINESE);
    //             boolean a = (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED);
    //             boolean b = (result2 == TextToSpeech.LANG_MISSING_DATA || result2 == TextToSpeech.LANG_NOT_SUPPORTED);
    //             Log.i("TTS", "US是否支持？--》" + a + "\nzh-CN是否支持？--》" + b);
    //         } else {
    //             Toast.makeText(JSON_of_top_search.this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
    //         }
    //
    //     });
    // }
    // //定义语音播报功能———————实例化自带语音对象————————————————————————————————————————————————————————————

    ////定义语音播报功能———————实例化自带语音对象———————设置播报的风格————————————————————————————————————————
    // private void startTTS(String data) {
    //     // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
    //     textToSpeech.setPitch(0.1f);
    //     // 设置语速
    //     textToSpeech.setSpeechRate(1.5f);
    //     //输入中文，若不支持的设备则不会读出来
    //     textToSpeech.speak(data,TextToSpeech.QUEUE_FLUSH, null);
    // }
    // //定义语音播报功能———————实例化自带语音对象————————设置播报的风格—————————————————————————————————————————


    //给字符串进行MD5加密————并将得到加密的值返回———————————————————————————————————————————————————————————
    @SuppressLint("LongLogTag")
    public static String md5(String string) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
                Log.d("md5md5md5md5md5md5md5md5md5md5md5", String.valueOf(result));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    //给字符串进行MD5加密————并将得到加密的值返回———————————————————————————————————————————————————————————


    //获取当前时间—————————————————————————————————————————————————————————————————————————————————————
    private long getCurrentTime(long datas) {

        long data1 = new Date().getTime();
        Log.d("ddddddddddddddddd", String.valueOf(data1));
        return datas;
    }
    //获取当前时间——————————————————————————————————————————————————————————————————————————————————————

}

