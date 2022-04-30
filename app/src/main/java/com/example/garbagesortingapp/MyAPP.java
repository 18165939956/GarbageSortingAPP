package com.example.garbagesortingapp;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class MyAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SpeechUtility.createUtility(MyAPP.this, SpeechConstant.APPID + "=95683b47");
    }
}
