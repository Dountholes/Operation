package com.example.operation.activity.setting;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.operation.R;

import java.util.ArrayList;
import java.util.List;

/*
**歌曲设置界面                                                    `
 */
public class SongActivity extends AppCompatActivity {
    private List<Songs> songList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Toolbar song_toolbar = (Toolbar) findViewById(R.id.song_toolbar);//为符合materia design模式，设置toolbar
        setSupportActionBar(song_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//返回键
        song_toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                finish();//这个程序中所有的finish都还没考虑数据存储问题，这是一个弊端
            }
        });
        initSongs();
        SongsAdapter songsAdapter = new SongsAdapter(SongActivity.this, songList);
        SongListView listView = (SongListView) findViewById(R.id.list_view);
        listView.setAdapter(songsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Songs song = songList.get(position);
                switch (song.getSongName()) {
                    case "天空之城":
                        Toast.makeText(SongActivity.this, song.getSongName(), Toast.LENGTH_SHORT).show();
                }
            }});

    }

            public void initSongs() {
                for (int i = 0; i < 10; i++) {
                    Songs song = new Songs("天空之城", "李志");
                    songList.add(song);
                }
            }
        }
