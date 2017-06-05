/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.operation.activity.setting.Timing;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.operation.R;

import java.util.ArrayList;
import java.util.List;

public class TimeSettingActivity extends Activity {

    // 成员域
    private List<Timesetting> TimeSettingList = new ArrayList<>();//可选时间配适器

    //自定义时间待完善

    //调试用
    public static final String TAG = "MainActivity";

    private Context mContext;
    private Intent mIntent;
    private Button btnCountdown;


    // 广播接收者
    private final BroadcastReceiver mUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            switch (action) {
                case RegisterCodeTimerService.IN_RUNNING:
                    if (btnCountdown.isEnabled())
                        btnCountdown.setEnabled(false);
                    // 正在倒计时
                    btnCountdown.setText("倒计时中(" + intent.getStringExtra("time") + ")");
                    Log.e(TAG, "倒计时中(" + intent.getStringExtra("time") + ")");
                    break;
                case RegisterCodeTimerService.END_RUNNING:
//                     完成倒计时

                    btnCountdown.setEnabled(true);
                    btnCountdown.setText("开始定时");

                    break;
            }
        }
    };


    //可选时间列表初始化
    private void initTimeSetting() {
        Timesetting T1 = new Timesetting("1分钟",60*1000);
        TimeSettingList.add(T1);
        Timesetting T2 = new Timesetting("5分钟",5*60*1000);
        TimeSettingList.add(T2);
        Timesetting T3 = new Timesetting("10分钟",10*60*1000);
        TimeSettingList.add(T3);
        Timesetting T4 = new Timesetting("15分钟",15*60*1000);
        TimeSettingList.add(T4);
        Timesetting T5 = new Timesetting("30分钟",30*60*1000);
        TimeSettingList.add(T5);
        Timesetting T6 = new Timesetting("60分钟",60*60*1000);
        TimeSettingList.add(T6);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);  //设置窗口显示模式为窗口方式
        setContentView(R.layout.activity_time_setting);

        //广播内容//
        mContext = this;
        Log.e(TAG, "onStart 方法调用");

        Button back = (Button) findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initTimeSetting();

        TimesettingAdapter timesettingAdapter = new TimesettingAdapter(TimeSettingActivity.this,
                R.layout.timesetting_item,TimeSettingList);
        ListView TimesettingListView = (ListView) findViewById(R.id.TimeSettingList);
        TimesettingListView.setAdapter(timesettingAdapter);//配适内容

            TimesettingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Timesetting timesetting = TimeSettingList.get(position);
                    mIntent = new Intent(mContext, RegisterCodeTimerService.class);
                    mIntent.putExtra("TIMESETTING", timesetting.getTime());
                    setTitle("你选择了定时："+timesetting.getName());
                    btnCountdown.setClickable(true);
                }
            });


            btnCountdown = (Button) findViewById(R.id.BeginTiming);
            btnCountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCountdown = (Button) findViewById(R.id.BeginTiming);
                // 将按钮设置为不可用状态
                btnCountdown.setEnabled(false);
                // 启动倒计时的服务
                startService(mIntent);
            }
        });



        // mNewDevicesArrayAdapter = new ArrayAdapter(this, R.layout.device_name,data2);
        // 设置新查找设备列表
//        ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
        //newDevicesListView.setAdapter(mNewDevicesArrayAdapter);
        //newDevicesListView.setOnItemClickListener(mDeviceClickListener);
    }








    @Override
    protected void onResume() {
        super.onResume();
        // 注册广播
        registerReceiver(mUpdateReceiver, updateIntentFilter());
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 移除注册
        unregisterReceiver(mUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy 方法调用");
    }

    // 注册广播
    private static IntentFilter updateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RegisterCodeTimerService.IN_RUNNING);
        intentFilter.addAction(RegisterCodeTimerService.END_RUNNING);
        return intentFilter;
    }



    public void OnCancel(View v){
        final Intent intent = new Intent();
        intent.setAction("ITOP.MOBILE.SIMPLE.SERVICE.SENSORSERVICE");
        stopService(intent);

    }
}

