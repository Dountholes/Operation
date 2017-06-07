
package com.example.operation.activity.setting;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.operation.R;
import com.example.operation.activity.login.GuideActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.SAXParser;

/*
**首页活动
 */
public class MainActivity extends AppCompatActivity{
    private Intent intent;
    /*
    **获取服务与活动纽带
     */
    private BlueToothService.BlueToothBinder blueToothBinder;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            {
                blueToothBinder=(BlueToothService.BlueToothBinder)service;
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        **启动服务
         */
       intent=new Intent(this,BlueToothService.class);
        /*
        **绑定服务,同时也启动了服务
         */
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);
        /*
        **测试代码
         */
        Handler handler=new Handler();
        Runnable runnable=new Runnable()
         {
          @Override
         public void run()
         {
             if(blueToothBinder==null)
             {
                 Toast.makeText(MainActivity.this,"blueToothBinder连接失败",Toast.LENGTH_SHORT).show();
             }else {
                 Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                 try{
                 blueToothBinder.connectBluetooth();}//进行蓝牙连接,单独做出一个类，这样只要连接一次就够了
                 catch(Exception ex){
                     Toast.makeText(MainActivity.this,"蓝牙连接失败，请检查设备",Toast.LENGTH_SHORT).show();
                 }

             }
         }
         };
           handler.postDelayed(runnable,1000);//开一秒的线程保证连接上，当然其实100ms也可以，保险
        intent=new Intent(MainActivity.this,StyleActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        unbindService(connection);
        intent=new Intent(this,BlueToothService.class);
        stopService(intent);
    }
}
