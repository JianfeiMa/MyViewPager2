package com.buyup.myviewpager2;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

/**
 * Copyright (C), buyuphk物流中转站
 * author: JianfeiMa
 * email: majianfei93@163.com
 * revised: 2020-10-19 17:55
 * motto: 勇于向未知领域探索
 */
public class MyCallback extends ViewPager2.OnPageChangeCallback implements Runnable {
    private ViewPager2 viewPager2;
    private MyFragmentStateAdapter myFragmentStateAdapter;
    private int position;

    MyCallback(ViewPager2 viewPager2, MyFragmentStateAdapter myFragmentStateAdapter) {
        this.viewPager2 = viewPager2;
        this.myFragmentStateAdapter = myFragmentStateAdapter;
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        this.position = position;
        Log.d("debug", "打印选中的Fragment->" + position);
        if (position == myFragmentStateAdapter.getItemCount() - 1) {
            Log.d("debug", "已经是最后一个Fragment，现在是创建下一个新的Fragment的时候了");

            Fragment fragment = new MyFragment(position + 1);
            myFragmentStateAdapter.setMoreData(fragment);
            viewPager2.post(this);
        }
    }

    @Override
    public void run() {
        myFragmentStateAdapter.notifyItemInserted(position + 1);
    }
}
