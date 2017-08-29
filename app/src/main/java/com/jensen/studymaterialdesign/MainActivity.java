package com.jensen.studymaterialdesign;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    private TabOneFragment mTabOneFragment = new TabOneFragment();
    private TabTwoFragment mTabTwoFragment = new TabTwoFragment();
    private TabThreeFragment mTabThreeFragment = new TabThreeFragment();
    private Fragment[] mFragments = {mTabOneFragment, mTabTwoFragment, mTabThreeFragment};
    private String[] tabs = {"球星", "简书", "新闻"};
    private ArrayList<Fragment> mFragmentArrayList = new ArrayList<>();
    private MyPagerAdapter mMyPagerAdapter;
    private Fragment mVisibleFragment;
    private Unbinder mUnbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
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
        mToolbar.setNavigationOnClickListener(this);

        mNavigationView.setItemIconTintList(null);

        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), mFragmentArrayList);
        mViewPager.setAdapter(mMyPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);//设置tabLayout与ViewPager关联
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setText(tabs[i]);
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                mVisibleFragment = mMyPagerAdapter.getItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    private long tempTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
/*        if (getVisibleFragment()  TabTwoFragment) {
                    //判断当前是不是two Fragment

        }*/
        if (mVisibleFragment!=null&&mVisibleFragment instanceof TabTwoFragment) {
            if (keyCode == KeyEvent.KEYCODE_BACK && mTabTwoFragment.mWebView.canGoBack()) {
                mTabTwoFragment.mWebView.goBack();
                return true;
            }else {
                if (System.currentTimeMillis() - tempTime < 2000) {
                    finish();
                    System.exit(0);
                } else {
                    Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                    tempTime = System.currentTimeMillis();
                    return false;
                }
            }
        } else {
            if (System.currentTimeMillis() - tempTime < 2000) {
                finish();
                System.exit(0);
            } else {
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                tempTime = System.currentTimeMillis();
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
