package com.jensen.studymaterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/28.
 */

public class TabOneFragment extends Fragment {
    @BindView(R.id.fragment_tab_one_recycle_view)
    RecyclerView mRecyclerView;
    private int[] icons = {R.drawable.ai, R.drawable.curry, R.drawable.james, R.drawable.james2, R.drawable.jordan, R.drawable.jordan2, R.drawable.kaili, R.drawable.kobe, R.drawable.paul, R.drawable.tmac};
    private String[] names = {"Ai", "Curry", "James", "James", "Jordan", "Jordan", "Irving", "Kobe", "Paul", "TMac"};
    private ArrayList<NBAStar> mNBAStarArrayList = new ArrayList<>();
    private static final int FLAG = 0;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_one, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initData();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mNBAStarArrayList, getActivity());
        mRecyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemRecyclerListener(new RecyclerViewAdapter.OnItemRecyclerListener() {
            @Override
            public void onItemClick(int position) {
                NBAStar nbaStar = mNBAStarArrayList.get(position);
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.setFlags(FLAG);
                intent.putExtra("icon",nbaStar.getIcon());
                intent.putExtra("name",nbaStar.getName());
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    private void initData() {
        for (int i = 0; i < icons.length; i++) {
            mNBAStarArrayList.add(new NBAStar(icons[i], names[i]));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
