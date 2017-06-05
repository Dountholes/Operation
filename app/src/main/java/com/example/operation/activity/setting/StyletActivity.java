package com.example.operation.activity.setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.operation.R;
/*
**定时设置界面
 */
public class StyletActivity extends AppCompatActivity implements View.OnClickListener {
    private Button broadcast;//预览按钮
    private Intent intent;
    private String filepath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        /*
        **播放视频部分
         */
        intent = new Intent(StyletActivity.this, VideoBroadcastActivity.class);//启动预览活动
        filepath = "movie.mp4";
        intent.putExtra("filepath", filepath);//向预览活动传送文件路径
        broadcast = (Button) findViewById(R.id.type1);
        broadcast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.type1://模式一监听
                startActivity(intent);//启动活动
        }
    }
}
