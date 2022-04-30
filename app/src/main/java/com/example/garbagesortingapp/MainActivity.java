package com.example.garbagesortingapp;



import static com.example.garbagesortingapp.XunFeiUtil.initXunFei;
import static com.example.garbagesortingapp.XunFeiUtil.parseIatResult;
import static com.example.garbagesortingapp.XunFeiUtil.startVoice;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MyViewPager.OnViewPagerTouchListener {

    //更换app主题—————————————————————————————————————————————————————————————————————————————————————
    private Button changestyle;
    private Boolean mUseMyTheme;
    //更换app主题—————————————————————————————————————————————————————————————————————————————————————
    //轮播图
    private MyViewPager viewPager;
    private looperpagerAdapter mLooperPagerAdapter;
    /*private static List<Integer> sColors = new ArrayList<>();*/
    private static final List<Integer> sPics = new ArrayList<>();
    private Handler handler;
    private LinearLayout pointContainer;
    private boolean mIsTouch = false;
    private EditText mResultText;


    static {
        sPics.add(R.drawable.viewpage_four);
        sPics.add(R.drawable.viewpage_three);
        sPics.add(R.drawable.viewpage_two);
        sPics.add(R.drawable.viewpage_one);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences sharedPreferences = getSharedPreferences("useMyTheme",MODE_PRIVATE);//获取主题更换状态
        mUseMyTheme = sharedPreferences.getBoolean("useMyTheme",true);
        if (!mUseMyTheme){
            setTheme(R.style.NightTheme);//黑色主题
        }else setTheme(R.style.LightTheme);//亮色主题
        setContentView(R.layout.activity_main);
        changestyle = findViewById(R.id.change_style);
        changestyle.setOnClickListener(view -> {
            mUseMyTheme = !mUseMyTheme;
            recreate();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("useMyTheme",mUseMyTheme);//存储主题更换状态
            editor.apply();
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        /*Context context = getApplicationContext();*/
        SpeechUtility.createUtility(MainActivity.this,SpeechConstant.APPID+"=95683b47");
        ImageButton idlistener = findViewById(R.id.listener);
        mResultText = ((EditText) findViewById(R.id.select01));
        idlistener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoice(MainActivity.this,new XunFeiCallbackListener() {
                    @Override
                    public void onFinish(RecognizerResult results) {
                        String text = parseIatResult(results.getResultString());
                        mResultText.append(text);
                    }
                });
            }

        });
        initView();
        handler = new Handler();
        setListeners();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        handler.post(mLooperTask);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(mLooperTask);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    private final Runnable mLooperTask = new Runnable() {

        @Override
        public void run() {
            if (!mIsTouch){
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++currentItem,true);
            }
            handler.postDelayed(this,2000);
        }
    };

    private void initView() {
        viewPager = this.findViewById(R.id.content_pager);
        mLooperPagerAdapter = new looperpagerAdapter();
        mLooperPagerAdapter.setData(sPics);
        viewPager.setAdapter(mLooperPagerAdapter);
        viewPager.SetOnViewPagerTouchListener(this);
        pointContainer = findViewById(R.id.points_container);
        insertPoint();
        viewPager.setCurrentItem(mLooperPagerAdapter.getDataResultSizse() * 100,true);
    }
    public void onPagerTouch(boolean isTouch){
        mIsTouch = isTouch;
    }
    //添加指示点

    //添加小圆点，根据图片的个数来进行添加——————————————————————————————————————————————————————————————————
    @SuppressLint("UseCompatLoadingForDrawables")
    private void insertPoint(){
        for (int i = 0; i < sPics.size(); i++){
            View point = new View(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40,40);
            point.setBackground(getResources().getDrawable(R.drawable.point_01));
            layoutParams.leftMargin = 20;
            point.setLayoutParams(layoutParams);
            pointContainer.addView(point);
        }
    }
    //添加小圆点，根据图片的个数来进行添加——————————————————————————————————————————————————————————————————

    public void onPageScrolled(int position,float positionOffset,int positionOffsetPixels){
    }


    //覆写下面三个方法，用来实现指示点的监听事件——————————————————————————————————————————————————————————————
    public void onPageSelected(int position){
        //这个方法的调用就是viewpager停下来以后选中的位置
        int realPosition;
        if (mLooperPagerAdapter.getDataResultSizse() != 0){
            realPosition = position % mLooperPagerAdapter.getDataResultSizse();
        }else{
            realPosition = 0;
        }
        setSelectedPoint(realPosition);
    }
    //覆写下面三个方法，用来实现指示点的监听事件——————————————————————————————————————————————————————————————

    private void setSelectedPoint(int realPosition) {
        for (int i = 0; i < pointContainer.getChildCount(); i++){
            View childAt = pointContainer.getChildAt(i);
            if (i != realPosition){
                //若当前图片被选中，则就是白色
                childAt.setBackgroundResource(R.drawable.point_01);
            }else{
                //当前图片位置和该图片的真实位置相同，就是选中了，就表现出红色
                childAt.setBackgroundResource(R.drawable.point_02);
            }
        }
    }

    public void onPageScrollStateChanged(int state){
    }

    /*
    点击搜索框跳到活动EnterTheSearchState
     */

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        @SuppressLint("WrongViewCast") Button ETSS_button = findViewById(R.id.select01);
        ETSS_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Enter the ListView",Toast.LENGTH_SHORT).show();
                Intent ETTS_intent = new Intent(MainActivity.this,EnterTheSearchState.class);
                startActivity(ETTS_intent);
            }
        });
    }*/
    private void setListeners(){
        ImageButton ETTS_button = findViewById(R.id.ETTS_button);
        ETTS_button.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this,"Enter the ListView",Toast.LENGTH_SHORT).show();
            Intent ETTS_intent = new Intent(MainActivity.this,EnterTheSearchState.class);
            startActivity(ETTS_intent);
        });

        //主界面中查询垃圾分类信息—————————拿到在EditText中输入的数据———————点击搜索按钮跳转到新活动中进行网络请求———
        ImageButton editText_search = findViewById(R.id.search);
        EditText editText = findViewById(R.id.select01);
        editText_search.setOnClickListener(view -> {
            String garbage_name = editText.getText().toString();
            MyDatabaseHelper3 dbHelper3 = new MyDatabaseHelper3(this, "INSERTDATA", null, 1);
            SQLiteDatabase db = dbHelper3.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("data",garbage_name);
            db.insert("INSERTDATA",null,values);
            Intent intent = new Intent(MainActivity.this,JSON_of_top_search.class);
            intent.putExtra("name",garbage_name);
            startActivity(intent);
        });
        //主界面中查询垃圾分类信息—————————拿到在EditText中输入的数据———————点击搜索按钮跳转到新活动中进行网络请求———

        //查询历史的按钮点击事件——————————点击后跳转到新的界面———————————————————————————————————————————————
        ImageButton history_search = findViewById(R.id.history_search);
        history_search.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,history_data_search.class);
            startActivity(intent);
        });
        //查询历史的按钮点击事件——————————点击后跳转到新的界面———————————————————————————————————————————————

        //回收物品信息管理功能——————————————————————————————————————————————————————————————————————————
        ImageButton garbage_sorting_management = findViewById(R.id.garbage);
        garbage_sorting_management.setOnClickListener(view -> {
           Intent intent = new Intent(MainActivity.this,garbagesorting_management.class);
           startActivity(intent);
        });
        //回收物品信息管理功能——————————————————————————————————————————————————————————————————————————

        //照片识别————————————————————————————————————————————————————————————————————————————————————
        /*ImageButton camera = findViewById(R.id.camera);
        camera.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,search_garbage_by_photo.class);
            startActivity(intent);
        });*/
        //照片识别————————————————————————————————————————————————————————————————————————————————————

    }
}