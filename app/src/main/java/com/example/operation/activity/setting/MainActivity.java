
package com.example.operation.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.operation.R;
import com.example.operation.activity.setting.Timing.TimerActivity;

/*
**首页活动
 */
public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    //"implements SeekBar.OnSeekBarChangeListener"实现SeekBar的监听

    //调试用
    private static final String TAG = "MainActivity";

    //声明SeekBar及其对应TextView（TextView用来显示SeekBar的进度）
    private TextView intensity_show;
    private TextView frequency_show;
    private TextView volume_show;
    private SeekBar intensity_control;
    private SeekBar frequency_control;
    private SeekBar volume_control;


    private Switch timing_switch;//定时模式开关

    private Button DIY_mode;//“自定义模式”按钮
    private Button confirm;//“确定选择”按钮


    private ImageButton ask_button;//提示图片按钮
    private ImageButton mode_setting_button;//模式选择图片按钮

    private TextView now_mode_name;//当前模式名显示框
    private TextView now_song_name;//当前歌曲名显示框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
        setContentView(R.layout.activity_main);
        TimingSwitchDo();//定时模式开关功能实现
        SeekBarInit();//配置SeekBar
        SeekBarDo();//自定义模式中SeekBar功能的实现
        //待完善功能：点击模式图片选择模式、启动停止、当前模式当前歌曲的实时显示、提示按钮的功能实现
    }

    //定时模式开关功能实现
    private void TimingSwitchDo(){
        timing_switch = (Switch) findViewById(R.id.timing_switch);
        timing_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(getApplicationContext(), "定时模式开启", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,TimerActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "定时模式关闭", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //配置SeekBar
    private void SeekBarInit(){
        intensity_show = (TextView) findViewById(R.id.intensity_show);
        intensity_control = (SeekBar) findViewById(R.id.intensity_control);
        intensity_control.setOnSeekBarChangeListener(this);
        intensity_show.setText(getResources().getString(R.string.intensity_show) + " : " + String.valueOf(intensity_control.getProgress()));
        intensity_control.setEnabled(false);


        frequency_show = (TextView) findViewById(R.id.frequency_show);
        frequency_control = (SeekBar) findViewById(R.id.frequency_control);
        frequency_show.setText(getResources().getString(R.string.frequency_show) + " : " + String.valueOf(frequency_control.getProgress()));
        frequency_control.setOnSeekBarChangeListener(this);
        frequency_control.setEnabled(false);


        volume_show = (TextView) findViewById(R.id.volume_show);
        volume_control = (SeekBar) findViewById(R.id.volume_control);
        volume_show.setText(getResources().getString(R.string.volume_show) + " : " + String.valueOf(volume_control.getProgress()));
        volume_control.setOnSeekBarChangeListener(this);
        volume_control.setEnabled(false);

    }






    //自定义模式中SeekBar功能的实现
    private void SeekBarDo(){
        DIY_mode = (Button) findViewById(R.id.DIY_mode);
        confirm = (Button) findViewById(R.id.confirm);
        confirm.setEnabled(false);//确认按钮开始不可点击

        //点击“自定义模式”按钮后：使三个属性设置SeekBar可滑动，确认选择可点击
        DIY_mode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "自定义模式开启，您现在可以改变更改属性设置", Toast.LENGTH_SHORT).show();
                intensity_control.setEnabled(true);
                frequency_control.setEnabled(true);
                volume_control.setEnabled(true);


                DIY_mode.setEnabled(false);
                confirm.setEnabled(true);

            }

        });


        //点击“确认选择”按钮后：使三个属性设置SeekBar不可滑动，确认选择不可点击。
        //请在这里添加创建自定义模式的逻辑！（待完成）
        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "已确认更改属性，设为新自定义模式", Toast.LENGTH_SHORT).show();

                intensity_control.setEnabled(false);
                frequency_control.setEnabled(false);
                volume_control.setEnabled(false);

                confirm.setEnabled(false);
                DIY_mode.setEnabled(true);
            }

        });

    }


    /*
     * SeekBar停止滚动的回调函数
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.e(TAG, "hi");
    }

    /*
     * SeekBar开始滚动的回调函数
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Log.e(TAG, "hi");
    }


    /*
     * SeekBar滚动时的回调函数
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        Log.e(TAG, "seekid:" + seekBar.getId() + ", progess" + progress);
        switch (seekBar.getId()) {
            // // 注意这里的“intensity_control”是layout中控件的id，不是我们自己命名的控件变量名
            case R.id.intensity_control: {
                //使得SeekBar对应的Text上显示拖动后的新进度值
                intensity_show.setText(getResources().getString(R.string.intensity_show) + " : " + String.valueOf(seekBar.getProgress()));
                break;
            }
            case R.id.frequency_control: {
                frequency_show.setText(getResources().getString(R.string.frequency_show) + " : " + String.valueOf(seekBar.getProgress()));
                break;
            }
            case R.id.volume_control: {
                volume_show.setText(getResources().getString(R.string.volume_show) + " : " + String.valueOf(seekBar.getProgress()));
                break;
            }
            default:
                break;
        }


    }

}
