package com.example.garbagesortingapp;

import android.os.Bundle;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

public class MscUtils {
    public  static void initIflytek(){
        SpeechUtility.createUtility(JSON_of_top_search.CONTEXT, SpeechConstant.APPID + "=95683b47");
    }

    public static  void speakText(String text){
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(JSON_of_top_search.CONTEXT,null);
        mTts.setParameter(SpeechConstant.VOICE_NAME,"xiaoyan");
        mTts.setParameter(SpeechConstant.SPEED,"50");
        mTts.setParameter(SpeechConstant.VOLUME,"100");
        mTts.setParameter(SpeechConstant.ENGINE_TYPE,SpeechConstant.TYPE_CLOUD);
        mTts.startSpeaking(text,new MySynthesizerListener());
    }

    static class MySynthesizerListener implements SynthesizerListener {
        @Override
        public void onSpeakBegin() {

        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,String info) {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {

        }

        @Override
        public void onCompleted(SpeechError error) {

        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {

        }
    }
}
