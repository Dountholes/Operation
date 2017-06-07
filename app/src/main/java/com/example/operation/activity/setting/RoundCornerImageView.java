package com.example.operation.activity.setting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by 邹特强 on 2017/6/2.
 */
/*
**重写ImageView实现圆角imageView
 */
public class RoundCornerImageView extends ImageView {
    public RoundCornerImageView(Context context) {
        super(context);
    }
    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public RoundCornerImageView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        clipPath.addRoundRect(new RectF(0, 0, w, h), 30.0f, 30.0f, Path.Direction.CW);//参数意义依次为外接正方形，圆弧圆心的x,y坐标，绘画方向
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}
