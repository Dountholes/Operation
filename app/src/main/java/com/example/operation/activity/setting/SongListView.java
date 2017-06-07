package com.example.operation.activity.setting;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by 邹特强 on 2017/5/31.
 */
/*
**自定义ListView实现弹性效果
 */
public class SongListView extends ListView {
    private static final int MAX_OVER_DISTANCE = 50;
    private int mMaxOverDistance;

    public SongListView(Context context) {
        this(context, null);
    }

    public SongListView(Context context,AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SongListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    /*
    **根据不同屏幕做适配
     */
    private void initView() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density;
        mMaxOverDistance = (int) (MAX_OVER_DISTANCE * density);
    }
/*
**自定义maxOverDistance，实现弹性效果
 */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);
    }
}
