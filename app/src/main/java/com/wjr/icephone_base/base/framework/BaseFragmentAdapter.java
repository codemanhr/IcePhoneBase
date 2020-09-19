package com.wjr.icephone_base.base.framework;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * created by @文景睿
 * description：支持懒加载的ViewPager的adapter
 */
public abstract class BaseFragmentAdapter extends FragmentPagerAdapter {
    public BaseFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }
}
