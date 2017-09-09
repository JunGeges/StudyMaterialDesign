package com.jensen.studymaterialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/28.
 */

public class TabThreeFragment extends Fragment {
    @BindView(R.id.tab_three_recycler_view)
    RecyclerView mRecyclerView;
    private ArrayList<NewsBean> mBeanArrayList = new ArrayList<>();
    private String[] titles = {"新闻", "首页", "劲爆", "发现", "设置", "缓存"};
    private ArrayList<GvItemBean> mGvItemBeanArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_three, container, false);
        ButterKnife.bind(this, view);
        initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        TabRecyclerViewAdapter tabRecyclerViewAdapter = new TabRecyclerViewAdapter(mBeanArrayList, getActivity(),mGvItemBeanArrayList);
        mRecyclerView.setAdapter(tabRecyclerViewAdapter);
        tabRecyclerViewAdapter.setOnItemListener(new TabRecyclerViewAdapter.OnItemListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void initData() {
        ArrayList<Integer> newsIconList_1 = new ArrayList<>();
        newsIconList_1.add(R.drawable.ai);
        ArrayList<Integer> newsIconList_2 = new ArrayList<>();
        newsIconList_2.add(R.drawable.curry);
        newsIconList_2.add(R.drawable.tmac);
        newsIconList_2.add(R.drawable.james);
        //初始化头布局 假数据 为了适配器 添加头布局
        ArrayList<Integer> headerList = new ArrayList<>();
        headerList.add(R.mipmap.ic_launcher);
        NewsBean newsHeaderBean = new NewsBean("titile头布局", "23232", headerList);
        mBeanArrayList.add(newsHeaderBean);
        for (int i = 0; i < 5; i++) {
            NewsBean news_1 = new NewsBean("titletitletitletitletitletitle", "2012.01.01", newsIconList_1);
            NewsBean news_2 = new NewsBean("titletitletitletletitle", "2012.01.01", newsIconList_2);
            mBeanArrayList.add(news_1);
            mBeanArrayList.add(news_2);
        }
        for (int i = 0; i < mBeanArrayList.size(); i++) {
            Log.i("TAG", "图片个数:" + mBeanArrayList.get(i).getNewsIconList().size() + "initData: " + mBeanArrayList.get(i).toString());
        }
        //初始化gridView的数据
        for (int i = 0; i < titles.length; i++) {
            GvItemBean gvItemBean = new GvItemBean(R.mipmap.ic_launcher, titles[i]);
            mGvItemBeanArrayList.add(gvItemBean);
        }
    }
}
