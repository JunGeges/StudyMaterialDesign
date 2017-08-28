package com.jensen.studymaterialdesign;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    private Fragment[] mFragments = {new TabOneFragment(), new TabTwoFragment(), new TabThreeFragment()};
    private String [] tabs = {"TabOne","TabTwo","TabThree"};
    private ArrayList<Fragment> mFragmentArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initEvents();
    }

    private void initData() {
        for (int i = 0; i < mFragments.length; i++) {
            mFragmentArrayList.add(mFragments[i]);
        }
    }

    private void initEvents() {
        mToolbar.inflateMenu(R.menu.tool_bar_menu);
        //通过xml设置title无效
        mToolbar.setTitle("首页");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationOnClickListener(this);

        mNavigationView.setItemIconTintList(null);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), mFragmentArrayList);
        mViewPager.setAdapter(myPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);//设置tabLayout与ViewPager关联
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setText(tabs[i]);
        }
    }

    @Override
    public void onClick(View v) {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }
}
