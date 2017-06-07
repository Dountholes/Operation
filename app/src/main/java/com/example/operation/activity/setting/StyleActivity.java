package com.example.operation.activity.setting;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.operation.R;

import java.io.IOException;
import java.sql.BatchUpdateException;


/*
**模式选择界面
 */
public class StyleActivity extends AppCompatActivity implements View.OnClickListener{
    private Intent intent;
    /*
    **休闲模式
     */
    private ImageView style_rl_img;
    /*
    **动感模式
     */
    private ImageView style_et_img;
    /*
    **浪漫模式
     */
    private ImageView style_rm_img;
    /*
    **夜间模式
     */
    private ImageView style_ng_img;
    /*
    **自定义模式
     */
    private ImageView style_ps_img;
    /*
    **休闲模式的预览
     */
    private Button style_rl_pv;
    /*
    **休闲模式的应用
     */
    private Button style_rl_ap;
    /*
    **动感模式预览
     */
    private Button style_et_pv;
    /*
    **动感模式应用
     */
    private Button style_et_ap;
    /*
    **浪漫模式预览
     */
    private Button style_rm_pv;
    /*
    **浪漫模式应用
     */
    private Button style_rm_ap;
    /*
    **夜间模式预览
     */
    private Button style_ng_pv;
    /*
    **夜间模式应用
     */
    private Button style_ng_ap;
    /*
    **标记发送信息成功，失败状态
     */
    private boolean flag=false;
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
        setContentView(R.layout.activity_style);
        intent=new Intent(this,BlueToothService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
        Toolbar styleToolbar=(Toolbar)findViewById(R.id.style_toolbar);
        setSupportActionBar(styleToolbar);
        /*
        **显示toolbar自带的返回键
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*
        **返回键的监听
         */
        styleToolbar.setNavigationOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
            finish();
        }});
        style_rl_img=(ImageView)findViewById(R.id.style_rl_img);
        style_rl_pv=(Button)findViewById(R.id.style_rl_pv);
        style_rl_ap=(Button)findViewById(R.id.style_rl_ap);
        style_et_img=(ImageView)findViewById(R.id.style_et_img);
        style_et_pv=(Button)findViewById(R.id.style_et_pv);
        style_et_ap=(Button)findViewById(R.id.style_et_ap);
        style_rm_img=(ImageView)findViewById(R.id.style_rm_img);
        style_rm_pv=(Button)findViewById(R.id.style_rm_pv);
        style_rm_ap=(Button)findViewById(R.id.style_rm_ap);
        style_ng_img=(ImageView)findViewById(R.id.style_ng_img);
        style_ng_pv=(Button)findViewById(R.id.style_ng_pv);
        style_ng_ap=(Button)findViewById(R.id.style_ng_ap);
        style_ps_img=(ImageView)findViewById(R.id.style_ps_img);
        style_rl_img.setOnClickListener(this);
        style_rl_pv.setOnClickListener(this);
        style_rl_ap.setOnClickListener(this);
        style_et_img.setOnClickListener(this);
        style_et_pv.setOnClickListener(this);
        style_et_ap.setOnClickListener(this);
        style_rm_img.setOnClickListener(this);
        style_rm_pv.setOnClickListener(this);
        style_rl_ap.setOnClickListener(this);
        style_ng_img.setOnClickListener(this);
        style_ng_pv.setOnClickListener(this);
        style_ng_ap.setOnClickListener(this);
        style_ps_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.style_rl_img:
                style_rl_pv.setVisibility(View.VISIBLE);//使隐藏的按钮显现
                style_rl_ap.setVisibility(View.VISIBLE);
                style_rl_img.setAlpha(0.6f);//使点击的图片透明度变淡
                break;
            case R.id.style_et_img:
                style_et_pv.setVisibility(View.VISIBLE);
                style_et_ap.setVisibility(View.VISIBLE);
                style_et_img.setAlpha(0.6f);
                break;
            case R.id.style_rm_img:
                style_rm_pv.setVisibility(View.VISIBLE);
                style_rm_ap.setVisibility(View.VISIBLE);
                style_rm_img.setAlpha(0.6f);
                break;
            case R.id.style_ng_img:
                style_ng_pv.setVisibility(View.VISIBLE);
                style_ng_ap.setVisibility(View.VISIBLE);
                style_ng_img.setAlpha(0.6f);
                break;
            /*
            **这里要跳到首页
             */
            case R.id.style_ps_img:
                intent=new Intent(StyleActivity.this,MainActivity.class);
                startActivity(intent);
               break;
            case R.id.style_rl_pv:
               intent=new Intent(StyleActivity.this,VideoBroadcastActivity.class);
                intent.putExtra("filepath","VideoView//movie.mp4");//这里可能会出现空指针，不要在测试的时候随便运行
                startActivity(intent);
                break;
            case R.id.style_rl_ap:
                    flag=blueToothBinder.sendMessage("style_relax");
                /*
                **这里貌似有延迟效应，有待解决，检测蓝牙连接状态
                 */
               checkConncect(flag);
                break;
            case R.id.style_et_pv:
                intent=new Intent(StyleActivity.this,VideoBroadcastActivity.class);
                intent.putExtra("filepath","VideoView//movie.mp4");//这里可能会出现空指针，不要在测试的时候随便运行
                break;
            case R.id.style_et_ap:
                blueToothBinder.sendMessage("style_exciting");
                break;
            case R.id.style_rm_pv:
                intent=new Intent(StyleActivity.this,VideoBroadcastActivity.class);
                intent.putExtra("filepath","VideoView//movie.mp4");//这里可能会出现空指针，不要在测试的时候随便运行
                break;
            case R.id.style_rm_ap:
                blueToothBinder.sendMessage("style_romantic");
                break;
            case R.id.style_ng_pv:
                intent=new Intent(StyleActivity.this,VideoBroadcastActivity.class);
                intent.putExtra("filepath","VideoView//movie.mp4");//这里可能会出现空指针，不要在测试的时候随便运行
                break;
            case R.id.style_ng_ap:
                blueToothBinder.sendMessage("style_night");
                break;
            default:break;
        }
    }
    /*
    **神来之笔，检测蓝牙状态，解决所有断开连接的问题，并自动重连//除了开机拒绝打开蓝牙的情况
     */
    public void checkConncect(boolean Flag)
    {
           /*
            **这里貌似有延迟效应，有待解决
            */
        if(!Flag)
        {
            Toast.makeText(this,"设置失败，正在重新连接蓝牙！",Toast.LENGTH_SHORT).show();
            BluetoothAdapter bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
                    /*
                    **在这里检测蓝牙状态是为了比避免麻烦的线程问题,以人工确定简化问题
                     */
            if(bluetoothAdapter.isEnabled())
                blueToothBinder.connectBluetooth();//重新连接
            else {
                Toast.makeText(this,"请打开蓝牙重新设置",Toast.LENGTH_SHORT).show();
                bluetoothAdapter.enable();
            }//先打开蓝牙
        }else{
            Toast.makeText(this,"设置成功！",Toast.LENGTH_SHORT).show();
        }
    }
@Override
    public void onDestroy()
{
    super.onDestroy();
    unbindService(connection);
}
}
