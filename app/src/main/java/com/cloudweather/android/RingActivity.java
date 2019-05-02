package com.cloudweather.android;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cloudweather.android.gson.Weather;
import com.cloudweather.android.util.Utility;

public class RingActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);

        ////////播放音乐////////
        mediaPlayer=MediaPlayer.create(this,R.raw.alarm);
        mediaPlayer.start();

        tv =findViewById(R.id.txt_Alarm);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        if (weatherString != null) {
            // 有缓存时直接解析天气数据
            Weather weather = Utility.handleWeatherResponse(weatherString);
            //调用init()方法
            init(weather);
        } else {
            tv.setText("今天又是元气满满的一天~");
            // 无缓存时去服务器查询天气

        }

    }

    public void stop(View view){
        mediaPlayer.stop();
        finish();
    }
    //提醒文字
    public void init(Weather weather){
        String str="今天也是元气满满的一天~\n";
        String s1=" \n";
        String s2="温馨提示：" + weather.suggestion.comfort.info;
        tv.setText(str+s1+s2);

    }
}
