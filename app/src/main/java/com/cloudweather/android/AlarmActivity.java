package com.cloudweather.android;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //获取闹钟管理者
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    ///这里编写button的点击事件
    public  void setAlarm(View view){
        //获取系统当前时间
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);

        //弹出时间对话框
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c=Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                c.set(Calendar.MINUTE,minute);

                Intent intent=new Intent();
                intent.setAction("com.cloudweather.android");

                //将来时态的跳转
                PendingIntent pendingIntent=PendingIntent.getBroadcast(AlarmActivity.this,0x101,intent,0);


                //获取闹钟管理者
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                //设置闹钟
                alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
                //时间一到，发送广播（闹钟响了）//广播接收者中（跳转Activity）//跳转Activity，在这个Activity中播放音乐
            }


        },hour,minute,true);
        timePickerDialog.show();

    }


    //周期性闹钟
    public  void setAlarmCycle(View view){
        //获取系统当前时间
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);

        //弹出时间对话框
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c=Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                c.set(Calendar.MINUTE,minute);
                Intent intent=new Intent();
                intent.setAction("com.cloudweather.android");

                //将来时态的跳转
                PendingIntent pendingIntent=PendingIntent.getBroadcast(AlarmActivity.this,0x101,intent,0);

                //获取闹钟管理者
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                //设置周期性闹钟
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),1000*60*3,pendingIntent);

                //时间一到，发送广播（闹钟响了）
                //广播接受者中（跳转Activity）
                // 跳转Activity，在这个Activity中播放音乐

            }
        },hour,minute,true);
        timePickerDialog.show();
    }


}
