package com.wjr.icephone_base.base.framework;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wjr.icephone_base.base.listener.OnLimitClickListener;
import com.wjr.icephone_base.base.util.OnLimitClickListenerHelper;


/**
 * created by @文景睿
 * description：Fragment基类，支持懒加载
 */
public abstract class BaseFragment extends Fragment implements OnLimitClickListener {
    protected Activity activity;
    private boolean isFirstTime = true;

    protected OnLimitClickListenerHelper onLimitClickListenerHelper;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(activity).inflate(getContentViewId(), null);
        onLimitClickListenerHelper = new OnLimitClickListenerHelper(this);
        initView(view);
        return view;
    }

    protected abstract int getContentViewId();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstTime) {
            isFirstTime = false;
            initData();
            initListener();
        }
    }
}
