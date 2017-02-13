package com.haiying.p2papp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/1/3.
 */
public class NoScrollViewPager extends ViewPager {


    //构造方法，使用父亲的
    public NoScrollViewPager(Context context) {
        super(context);
    }
    //构造方法，使用父亲的
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 中断 滑动事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

}
