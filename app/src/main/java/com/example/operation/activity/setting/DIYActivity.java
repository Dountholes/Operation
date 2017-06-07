package com.example.operation.activity.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;

import com.example.operation.R;
/*
**首页界面活动
 */
public class DIYActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private SeekBar song_seekbar;
    private SeekBar light_seekbar;
    private SeekBar frequency_seekbar;
    private SeekBar volume_seekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy);
        /*
        **设置toolbar
         */
        Toolbar diyToolbar=(Toolbar)findViewById(R.id.diy_toolbar);
        diyToolbar.setLogo(R.mipmap.icon_diy_tb_logo);
        setSupportActionBar(diyToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置返回图标
        diyToolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                finish();//虽然受effective影响，感觉这里有弊端，但又没有太多精力优化这部分
            }
        });
        song_seekbar = (SeekBar) findViewById(R.id.song_seekBar);
        light_seekbar = (SeekBar) findViewById(R.id.light_seekBar);
        frequency_seekbar = (SeekBar) findViewById(R.id.frequency_seekBar);
        volume_seekbar = (SeekBar) findViewById(R.id.volume_seekBar);
        song_seekbar.setOnSeekBarChangeListener(this);
        light_seekbar.setOnSeekBarChangeListener(this);
        frequency_seekbar.setOnSeekBarChangeListener(this);
        volume_seekbar.setOnSeekBarChangeListener(this);
    }
    /**
     * 拖动条停止拖动的时候调用
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch(seekBar.getId())
        {

        }
    }

    /**
     * 拖动条开始拖动的时候调用
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        switch(seekBar.getId())
        {

        }
    }

    /**
     * 拖动条进度改变的时候调用
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        switch(seekBar.getId())
        {

        }
    }
}
