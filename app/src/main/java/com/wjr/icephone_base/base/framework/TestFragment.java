package com.wjr.icephone_base.base.framework;

import android.view.View;

public class TestFragment extends BaseFragment {

    ///返回你的布局Xml文件ID（R.id.xxxx)
    @Override
    protected int getContentViewId() {
        return 0;
    }

    ///加载布局
    @Override
    protected void initView(View view) {

    }

    ///加载数据（此方法为懒加载）
    @Override
    protected void initData() {

    }

    ///加载监听器
    @Override
    protected void initListener() {

    }

    ///点击事件（已经进行防抖动处理）
    @Override
    public void onLimitClick(View view) {

    }
}
