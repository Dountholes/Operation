package com.example.operation.activity.setting.Timing;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.operation.R;

import java.io.Serializable;

public class ModeSettingActivity extends Activity {
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private TextView textView;
    private Button button;
    Remember remember = new Remember();//建立一个专用类的实例remember来存放各个CheckBox被点击的顺序

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);  //设置窗口显示模式为窗口方式
        setContentView(R.layout.activity_mode_setting);
        cb1 = (CheckBox) findViewById(R.id.ModeBox1);
        cb2 = (CheckBox) findViewById(R.id.ModeBox2);
        cb3 = (CheckBox) findViewById(R.id.ModeBox3);
        cb4 = (CheckBox) findViewById(R.id.ModeBox4);
        cb5 = (CheckBox) findViewById(R.id.ModeBox5);
        button = (Button) findViewById(R.id.modesetting) ;//确认选择按钮

        //监听复选框选择事件
        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //counter的值是已点击的CheckBox的个数
                int counter=count();
                //remember中Xxmode的值设为counter的值，由此实现记忆顺序地功能
                remember.setXxmode(counter);
                //已点击的CheckBox不可取消/再点击
                cb1.setClickable(false);
                //显示当前点击的CheckBox是第几个被点击的CheckBox
                cb1.setText(remember.getXxmode()+"");
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter=count();
                remember.setDgmode(counter);
                cb2.setClickable(false);
                cb2.setText(remember.getDgmode()+"");
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter=count();
                remember.setLmmode(counter);
                cb3.setClickable(false);
                cb3.setText(remember.getLmmode()+"");
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter=count();
                remember.setYjmode(counter);
                cb4.setClickable(false);
                cb4.setText(remember.getYjmode()+"");
            }
        });
        cb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter=count();
                remember.setZdymode(counter);
                cb5.setClickable(false);
                cb5.setText(remember.getZdymode()+"");
            }
        });


        //点击确认选择按钮后：将弹窗出定时窗口活动，同时传递remember对象给该活动
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModeSettingActivity.this, TimeSettingActivity.class); //跳转程序设置
                button.setClickable(false);
                //设置返回宏定义
                intent.setClass(ModeSettingActivity.this, TimeSettingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Remember", (Serializable) remember);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

        public int count() {
            //用于计算已经被选中的CheckBox的个数，同时据此来改变活动Title
        int counter = 0;
        if (cb1.isChecked()) {
            counter++;
        }
        if (cb2.isChecked()) {
            counter++;
        }
        if (cb3.isChecked()) {
            counter++;
        }
        if (cb4.isChecked()) {
            counter++;
        }
        if (cb5.isChecked()) {
            counter++;
        }
        if (counter == 1) {
            setTitle("定时关机模式");
            textView =(TextView)findViewById(R.id.mosetting);
            textView.setText("选择单模式，定时完毕后将关机");
        }

        if (counter > 1) {
            setTitle("定时循环模式");
            textView =(TextView)findViewById(R.id.mosetting);
            textView.setText("选择多模式，将按顺序循环播放");
        }
        return counter;
    }
}










