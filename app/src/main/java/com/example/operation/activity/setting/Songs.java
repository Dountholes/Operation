package com.example.operation.activity.setting;

import android.view.View;

/**
 * Created by 邹特强 on 2017/5/31.
 */

public class Songs {
    private String songName;
    private String songSinger;
    public Songs(String songName,String songSinger)
    {
        this.songName=songName;
        this.songSinger=songSinger;
    }
    public String getSongName()
    {
        return songName;
    }
    public String getSongSinger()
    {
        return songSinger;
    }
}
