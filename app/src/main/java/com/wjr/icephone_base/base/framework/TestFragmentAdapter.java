package com.wjr.icephone_base.base.framework;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TestFragmentAdapter extends BaseFragmentAdapter {
    //你要展示的fragment集合，通过构造方法传入
    List<Fragment> fragments;

    public TestFragmentAdapter(@NonNull @NotNull FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
