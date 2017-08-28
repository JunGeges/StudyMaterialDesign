package com.jensen.studymaterialdesign;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/28.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;

    public MyPagerAdapter(FragmentManager fm,ArrayList<Fragment> arrayList) {
        super(fm);
        mFragments=arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
