package com.wjr.icephone_base.base.util;

import android.view.View;

import com.wjr.icephone_base.base.listener.OnLimitClickListener;

import java.util.Calendar;

public final class OnLimitClickListenerHelper implements View.OnClickListener {

/**
 * created by @文景睿
 * description：View防抖动效果Helper类
 */

    /**
     * 使用方法
     * 任何View都可以使用,不局限与按钮！
     * <p>
     * 方法1：
     * 内部类方法
     * button.setOnClickListener(new OnLimitClickListenerHelper(new OnLimitClickListener() {
     *
     * @Override public void onLimitClick(View view)
     * {
     * 在此做点击操作
     * }
     * }));
     * <p>
     * <p>
     * 方法二：实现OnLimitClickListener接口
     * <p>
     * 让当前Activity实现OnLimitClickListener
     * 之后让需要的控件做如下操作就可以
     * button.setOnClickListener(new OnLimitClickListenerHelper(this));
     * <p>
     * 在生成的实现方法中按如下方式写：
     * @Override public void onLimitClick(View view) {
     * <p>
     * switch (view.getId()) {
     * case XXXXX
     * XXXXX
     * break;
     * <p>
     * case XXXX:
     * XXXXXX
     * break;
     * }
     * }
     */

    private int limitTime = 600;//两次点击的屏蔽间隔为0.6秒
    private long lastTimeWhenClick = 0;
    private OnLimitClickListener onLimitClickListener;

    public OnLimitClickListenerHelper(OnLimitClickListener OnLimitClickListener) {
        this.onLimitClickListener = OnLimitClickListener;
    }

    /**
     * add by @jizhangxiong
     * description： 改变间隔时间
     */
    public void changeLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    @Override
    public void onClick(View v) {
        long currentTimeWhenClick = Calendar.getInstance().getTimeInMillis();
        if (this.onLimitClickListener != null && currentTimeWhenClick - lastTimeWhenClick > limitTime) {
            onLimitClickListener.onLimitClick(v);//响应接口回调
            lastTimeWhenClick = currentTimeWhenClick;
        }
    }
}

