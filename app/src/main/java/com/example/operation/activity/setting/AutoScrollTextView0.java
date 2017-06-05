package com.example.operation.activity.setting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

//暂未用到的类，用于实现滚动效果,使用会报错，不知为什么//
//暂未用到的类，用于实现滚动效果//
//暂未用到的类，用于实现滚动效果//
//暂未用到的类，用于实现滚动效果//
//暂未用到的类，用于实现滚动效果//


public class AutoScrollTextView0 extends TextView implements View.OnClickListener {
    public final static String TAG = AutoScrollTextView0.class.getSimpleName();

    private float textLength = 0f;//文本长度
    private float viewWidth = 0f;
    private float step = 0f;//文字的横坐标
    private float  y = 0f;//文字的纵坐标
    private float temp_view_plus_text_length = 0.0f;//用于计算的临时变量
    private float temp_view_plus_two_text_length = 0.0f;//用于计算的临时变量
    public boolean isStarting = false;//是否开始滚动
    private Paint paint = null;//绘图样式
    private String text = "";//文本内容

    private int scrollTimes = 1;// 滚动次数

    private int downX;
    private int upX;

    public AutoScrollTextView0(Context context) {
        super(context);
        initView();
    }

    public AutoScrollTextView0(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AutoScrollTextView0(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }


    private void initView() {
        setOnClickListener(this);
    }

    /**
     * 设置显示的滚动文本内容
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
        initView();
    }

    /**
     * 设置文字颜色
     *
     * @param color
     */
    public void setTextColor(int color) {
        paint.setColor(color);
        initView();
    }

    public void init(WindowManager windowManager) {
        paint = getPaint();
        text = getText().toString();
        textLength = paint.measureText(text);
        viewWidth = getWidth();
        if (viewWidth == 0) {
            if (windowManager != null) {
                Display display = windowManager.getDefaultDisplay();
                viewWidth = display.getWidth();
            }
        }
        step = textLength;
        temp_view_plus_text_length = viewWidth + textLength;
        temp_view_plus_two_text_length = viewWidth + textLength * 2;
        y = getTextSize() + getPaddingTop();
        setMarqueeRepeatLimit(scrollTimes);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);

        ss.step = step;
        ss.isStarting = isStarting;

        return ss;

    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        step = ss.step;
        isStarting = ss.isStarting;

    }

    public static class SavedState extends BaseSavedState {
        public boolean isStarting = false;
        public float step = 0.0f;

        SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeBooleanArray(new boolean[]{isStarting});
            out.writeFloat(step);
        }


        public static final Creator<SavedState> CREATOR
                = new Creator<SavedState>() {

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }

            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }
        };

        private SavedState(Parcel in) {
            super(in);
            boolean[] b = null;
            in.readBooleanArray(b);
            if (b != null && b.length > 0)
                isStarting = b[0];
            step = in.readFloat();
        }
    }


    public void startScroll() {
        isStarting = true;
        invalidate();
    }


    public void stopScroll() {
        isStarting = false;
        invalidate();
    }

    public void emptyText() {
        setText("");
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawText(text,0,text.length(), temp_view_plus_text_length - step, y, paint);
        if (!isStarting) {
            return;
        }
        step += 2.5;//0.5为文字滚动速度。
        if (step > temp_view_plus_two_text_length)
            step = textLength;
        invalidate();

    }

    @Override
    public void onClick(View v) {
        if (isStarting)
            stopScroll();
        else
            startScroll();
    }

    /**
     * 触摸屏幕事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();//float DownX
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX() - downX;//X轴距离
                //float moveY = event.getY() - downY;//y轴距离
                //long moveTime = System.currentTimeMillis() - currentMS搜索;//移动时间
                if (Math.abs(moveX) > 200) {
                    emptyText();
                }
                break;
            case MotionEvent.ACTION_UP:
                upX = (int) event.getX();//float upX
                if (downX == upX) {
                    if (isStarting)
                        stopScroll();
                    else
                        startScroll();
                }
                break;
            default:
                return true;
        }
        return true;
    }
}