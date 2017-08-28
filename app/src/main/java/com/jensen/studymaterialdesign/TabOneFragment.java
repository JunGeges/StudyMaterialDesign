package com.jensen.studymaterialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class TabOneFragment extends Fragment {
    @BindView(R.id.fragment_tab_one_recycle_view)
    RecyclerView mRecyclerView;
    private int[] icons = {R.drawable.ai, R.drawable.curry, R.drawable.james, R.drawable.james2, R.drawable.jordan, R.drawable.jordan2, R.drawable.kaili, R.drawable.kobe, R.drawable.paul, R.drawable.tmac};
    private String[] names = {"Ai", "Curry", "James", "James", "Jordan", "Jordan", "Irving", "Kobe", "Paul", "TMac"};
    private ArrayList<NBAStar> mNBAStarArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab_one,container,false);
        ButterKnife.bind(this,view);
        initData();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mNBAStarArrayList,getActivity());
        mRecyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemRecyclerListener(new RecyclerViewAdapter.OnItemRecyclerListener() {
            @Override
            public void onItemClick(int position) {
                NBAStar nbaStar = mNBAStarArrayList.get(position);
                Toast.makeText(getActivity(), nbaStar.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void initData() {
        for (int i = 0; i < icons.length; i++) {
            mNBAStarArrayList.add(new NBAStar(icons[i], names[i]));
        }
    }
}
