package com.jensen.studymaterialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/28.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {
    private ArrayList<NBAStar> mNBAStarArrayList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RecyclerViewAdapter(ArrayList<NBAStar> starArrayList, Context context) {
        mNBAStarArrayList = starArrayList;
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recycler_view_item_view, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        NBAStar nbaStar = mNBAStarArrayList.get(position);
        holder.icon.setImageResource(nbaStar.getIcon());
        holder.name.setText(nbaStar.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemRecyclerListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNBAStarArrayList.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_icon)
        ImageView icon;
        @BindView(R.id.tv_item_name)
        TextView name;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemRecyclerListener{
        void onItemClick(int position);
    }

    private OnItemRecyclerListener mOnItemRecyclerListener;

    public void setOnItemRecyclerListener(OnItemRecyclerListener onItemRecyclerListener){
        mOnItemRecyclerListener = onItemRecyclerListener;
    }
}
