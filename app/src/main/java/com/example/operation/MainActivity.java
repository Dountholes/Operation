
package com.example.operation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private VideoView videoView;
    private Button broadcast;
    private View video;
    private Context context;
    private VideoBroadcast videoBroadcast;
    private Intent intent;
    private String filepath=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent=new Intent(MainActivity.this,VideoBroadcast.class);//��ʾintent��ͼ����
        filepath="movie.mp4";
        intent.putExtra("filepath",filepath);//��һ������ʱ��ֵ//����һ�����������
        broadcast=(Button)findViewById(R.id.type1);
        broadcast.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.type1:
                startActivity(intent);//����������
        }
    }
}
