package com.example.operation.activity.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import com.example.operation.R;

/**
 * Created by 邹特强 on 2017/5/31.
 */

public class SongsAdapter extends BaseAdapter {
    private List<Songs> songs;
    private LayoutInflater mInflater;
    public SongsAdapter(Context context,List<Songs>songs)
    {
        this.songs=songs;
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount()
    {
        return songs.size();
    }
    @Override
    public Object getItem(int position)
    {
        return songs.get(position);
    }
    @Override
    public long getItemId(int pisition)
    {
        return pisition;
    }
    /*
    **利用ListView的缓存机制，提高效率
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)// 每个子项滚动到屏幕时被调用
    {
        ViewHolder holder=null;
        if(convertView==null)
        {
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.song_item,null);
            holder.tvOrder=(TextView)convertView.findViewById(R.id.song_order);
            holder.tvName=(TextView)convertView.findViewById(R.id.song_name);
            holder.tvSinger=(TextView)convertView.findViewById(R.id.song_singer);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        /*
        **初始化每个item的布局
         */
        holder.tvOrder.setText(String.valueOf(position+1));
        holder.tvName.setText(songs.get(position).getSongName());
        holder.tvSinger.setText(songs.get(position).getSongSinger());
        return convertView;
    }
    /*
    **每个item的组件
     */
    public final class ViewHolder
    {
        public TextView tvOrder;
        public TextView tvName;
        public TextView tvSinger;
    }
}

