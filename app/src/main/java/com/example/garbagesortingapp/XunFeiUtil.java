package com.example.garbagesortingapp;

import android.content.Context;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class XunFeiUtil {
    public static String appid = "95683b47";

    public static  void initXunFei(Context context){
        SpeechUtility.createUtility(context,SpeechConstant.APPID+"="+appid);
    }

    public static void startVoice(Context context,final XunFeiCallbackListener callbackListener){
        SpeechRecognizer.createRecognizer(context,null);
        RecognizerDialog dialog = new RecognizerDialog(context,null);
        dialog.setParameter(SpeechConstant.CLOUD_GRAMMAR,null);
        dialog.setParameter(SpeechConstant.SUBJECT,null);
        dialog.setParameter(SpeechConstant.RESULT_TYPE,"json");
        dialog.setParameter(SpeechConstant.ENGINE_TYPE,"cloud");
        dialog.setParameter(SpeechConstant.LANGUAGE,"zh_cn");
        dialog.setParameter(SpeechConstant.ACCENT,"mandarin");
        dialog.setParameter(SpeechConstant.ASR_PTT,"0");
        dialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                callbackListener.onFinish(recognizerResult);
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        dialog.show();
    }

    public static String parseIatResult(String json){
        StringBuffer ret = new StringBuffer();
        try{
            JSONTokener tokener = new JSONTokener(json);
            JSONObject joResult = new JSONObject(tokener);
            JSONArray words = joResult.getJSONArray("ws");
            for (int i = 0;i < words.length(); i++){
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                JSONObject obj = items.getJSONObject(0);
                ret.append(obj.getString("w"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret.toString();
    }
}
