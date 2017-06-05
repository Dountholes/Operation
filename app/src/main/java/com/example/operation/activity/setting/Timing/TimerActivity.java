package com.example.operation.activity.setting.Timing;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.operation.R;

/*
**定时界面
 */
public class TimerActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏



        Button timer = (Button) findViewById(R.id.timer);
        Button mode = (Button) findViewById(R.id.mode);
        //定时关闭活动跳转
        timer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent serverIntent = new Intent(TimerActivity.this, TimeSettingActivity.class); //跳转程序设置
                startActivity(serverIntent);  //设置返回宏定义
            }
        });

        //定时循环活动跳转
        mode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent serverIntent2 = new Intent(TimerActivity.this, ModeSettingActivity.class); //跳转程序设置
                startActivity(serverIntent2);  //设置返回宏定义
            }
        });
    }



}



