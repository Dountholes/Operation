package com.example.operation.activity.setting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.operation.R;

import java.io.File;
/*
**实现预览功能视频的播放，视频不具有暂停功能，播放完毕返回上个界面
 */
public class VideoBroadcastActivity extends AppCompatActivity{
    private VideoView videoView;
    private String filepath;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_broadcast);
        /*
        **隐藏标题栏，防止因为标题栏影响视频播放比例
         */
        ActionBar actionBar=getSupportActionBar();//活动系统的actionBar
        if(actionBar!=null)
        {
            actionBar.hide();
        }
        hideBottomUIMenu();//隐藏华为这样的手机自带的虚拟按钮

        intent=getIntent();
        filepath=intent.getStringExtra("filepath");//从外部传入filepath，可以播放不同视频
        videoView = (VideoView) findViewById(R.id.video_view);
        /*
        **视频播放完毕自动返回上个活动
         */
        videoView.setOnCompletionListener(new MediaPlayer.
                OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {     
                finish();        
            }
        });
        if (ContextCompat.checkSelfPermission(VideoBroadcastActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(VideoBroadcastActivity.this, new String[]
                    {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 1);
        } else {
            initVideoPath();
        }
}
    //初始化视频路径播放指定视频
  private void initVideoPath() {
      File file=new File(Environment.getExternalStorageDirectory(),filepath);
      videoView.setVideoPath(file.getPath());
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[]permissions,int[]grantResults)//三个参数分别为请求码自定义；请求的权限的内容；系统授予权限的结果
        {
            switch(requestCode)//requestcode可以标记请求，然后就可以处理不同的请求
        {
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
           initVideoPath();
        }else{
        Toast.makeText(this,"拒绝权限将无法使用程序",Toast.LENGTH_SHORT).show();
        finish();
        }
        break;
        default:
        }
}
@Override
//活动销毁时停止视频的播放
    protected void onDestroy()
{
    super.onDestroy();
    if(videoView!=null)
    {
        videoView.suspend();
    }
}
@Override
    public void onStart()//活动由不可见到可见时
{
    super.onStart();
    if(videoView!=null)
    videoView.start();//用户跳到别的界面时防止黑屏，同时因为演示视频很短，所以直接重新开始播放
}
/*
**隐藏虚拟按钮和状态栏的封装
 */
protected void hideBottomUIMenu(){
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            //神奇的发现用|可以同时选择多个
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar//可以隐藏虚拟按钮
                   | View.SYSTEM_UI_FLAG_FULLSCREEN// hide status bar
                   | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ;//沉浸式
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

}
