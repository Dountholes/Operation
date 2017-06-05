package com.example.operation.activity.setting.Timing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.operation.R;

import java.util.List;


public class TimesettingAdapter extends ArrayAdapter<Timesetting> {
    private int resourceId;

    public TimesettingAdapter(Context context, int textViewResourceId,
                        List<Timesetting> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //重写getView()方法，这个方法在每个子项被滚动到屏幕内的时候会被调用
        //ListView中的每一个Item显示都需要Adapter调用一次getView的方法，这个方法会传入一个convertView的参数，返回的View就是这个Item显示的View。
        //当ListView滑动的过程中，会有item被滑出屏幕而不再被使用，这时候Android会回收这个条目的view 这个view也就是这里的convertView
        Timesetting timesetting = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView fruitName = (TextView) view.findViewById(R.id.timesetting_name);
        //获取ImageView和TextView的实例
        fruitName.setText(timesetting.getName());
        //设置显示的和文字
        return view;
    }

}
