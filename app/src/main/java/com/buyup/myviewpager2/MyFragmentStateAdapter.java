package com.buyup.myviewpager2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * Copyright (C), buyuphk物流中转站
 * author: JianfeiMa
 * email: majianfei93@163.com
 * revised: 2020-10-19 16:08
 * motto: 勇于向未知领域探索
 */
public class MyFragmentStateAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;

    public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    /**
     * 刷新更多数据
     */
    public void setMoreData(Fragment fragment) {
        fragmentList.add(fragment);
    }
}
