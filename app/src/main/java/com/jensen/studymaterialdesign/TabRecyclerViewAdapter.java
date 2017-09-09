package com.jensen.studymaterialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/8.
 */

public class TabRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "TabRecyclerViewAdapter";
    private ArrayList<NewsBean> mBeanArrayList;
    private ArrayList<GvItemBean> mGvItemBeanArrayList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private static final int TYPE0 = 0;//头布局
    private static final int TYPE1 = 1;//单图标
    private static final int TYPE2 = 2;//多图

    public TabRecyclerViewAdapter(ArrayList<NewsBean> beanArrayList, Context context, ArrayList<GvItemBean> gvItemBeanArrayList) {
        mBeanArrayList = beanArrayList;
        mGvItemBeanArrayList = gvItemBeanArrayList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        RecyclerView.ViewHolder holder = getViewHolderByType(viewType);
        return holder;
    }

    private RecyclerView.ViewHolder getViewHolderByType(int viewType) {
        switch (viewType) {
            case TYPE1:
                View view_1 = mLayoutInflater.inflate(R.layout.fragment_recycler_view_type_1, null);
                Type1ViewHolder type1ViewHolder = new Type1ViewHolder(view_1);
                return type1ViewHolder;

            case TYPE2:
                View view_2 = mLayoutInflater.inflate(R.layout.fragment_recycler_view_type_2, null);
                Type2ViewHolder type2ViewHolder = new Type2ViewHolder(view_2);
                return type2ViewHolder;

            case TYPE0:
                View view_0 = mLayoutInflater.inflate(R.layout.fragment_recycler_view_type_0, null);
                Type0ViewHolder type0ViewHolder = new Type0ViewHolder(view_0);
                return type0ViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsBean newsBean = mBeanArrayList.get(position);
        if (holder instanceof Type1ViewHolder) {
            Log.i(TAG, "onBindViewHolder: type1" + newsBean.getNewsIconList().size());
            //type1 绑值
            bindDataView(holder, newsBean, TYPE1, position);

        } else if (holder instanceof Type2ViewHolder) {
            Log.i(TAG, "onBindViewHolder: type2" + newsBean.getNewsIconList().size());
            //type2 绑值
            bindDataView(holder, newsBean, TYPE2, position);
        } else {
            //type0 绑值
            bindDataView(holder, newsBean, TYPE0, position);
        }
    }

    private void bindDataView(RecyclerView.ViewHolder holder, NewsBean newsBean, int type, final int position) {
        switch (type) {
            case TYPE1:
                Type1ViewHolder holder1 = (Type1ViewHolder) holder;
                holder1.mImageView_1.setImageResource(newsBean.getNewsIconList().get(0));
                holder1.mTextViewTime_1.setText(newsBean.getNewsTime());
                holder1.mTextViewTitle_1.setText(newsBean.getNewstitle());
                holder1.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemListener.onItemClick(position);
                    }
                });
                break;

            case TYPE2:
                Type2ViewHolder holder2 = (Type2ViewHolder) holder;
                holder2.mTextViewTitle_2.setText(newsBean.getNewstitle());
                holder2.mImageView_2_1.setImageResource(newsBean.getNewsIconList().get(0));
                holder2.mImageView_2_2.setImageResource(newsBean.getNewsIconList().get(1));
                holder2.mImageView_2_3.setImageResource(newsBean.getNewsIconList().get(2));
                holder2.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemListener.onItemClick(position);
                    }
                });
                break;

            case TYPE0:
                Type0ViewHolder holder0 = (Type0ViewHolder) holder;
                GridViewAdapter gridViewAdapter = new GridViewAdapter(mContext, mGvItemBeanArrayList);
                holder0.mGridView.setAdapter(gridViewAdapter);
                holder0.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(mContext, mGvItemBeanArrayList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });

                //解决gridView只显示一行  每次主动测量出GridView的高度，并设置给布局
                ViewGroup.LayoutParams layoutParams = holder0.mGridView.getLayoutParams();
                View view = gridViewAdapter.getView(0, null, holder0.mGridView);
                view.measure(0, 0);
                int measuredHeight = view.getMeasuredHeight();
                int totalHeight = holder0.mGridView.getVerticalSpacing() * 2 + measuredHeight * 2;
                layoutParams.height = totalHeight;
                holder0.mGridView.setLayoutParams(layoutParams);
        }
    }


    @Override
    public int getItemViewType(int position) {
        Log.i(TAG, "getItemViewType:");
        //根据bean内容返回具体的布局
        NewsBean newsBean = mBeanArrayList.get(position);
        if (newsBean.getNewstitle().endsWith("头布局")) {
            return TYPE0;
        } else if (newsBean.getNewsIconList().size() > 1) {
            //多图的新闻
            return TYPE2;
        } else {
            //单图的新闻
            return TYPE1;
        }
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount:");
        return mBeanArrayList.size();
    }

    //TYPE0 viewHolder
    static class Type0ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_type_0_gv)
        GridView mGridView;

        public Type0ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //TYPE1 ViewHolder
    static class Type1ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_type_1_iv_icon)
        ImageView mImageView_1;
        @BindView(R.id.item_type_1_tv_time)
        TextView mTextViewTime_1;
        @BindView(R.id.item_type_1_tv_title)
        TextView mTextViewTitle_1;

        public Type1ViewHolder(View itemView) {
            super(itemView);
            Log.i(TAG, "Type1ViewHolder:");
            ButterKnife.bind(this, itemView);
        }
    }

    //Type2 viewHolder
    static class Type2ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_type_2_tv_time)
        TextView mTextViewTime_2;
        @BindView(R.id.item_type_2_tv_title)
        TextView mTextViewTitle_2;
        @BindView(R.id.item_type_2_iv_1)
        ImageView mImageView_2_1;
        @BindView(R.id.item_type_2_iv_2)
        ImageView mImageView_2_2;
        @BindView(R.id.item_type_2_iv_3)
        ImageView mImageView_2_3;

        public Type2ViewHolder(View itemView) {
            super(itemView);
            Log.i(TAG, "Type2ViewHolder:");
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }

    private OnItemListener mOnItemListener;

    public void setOnItemListener(OnItemListener onItemListener) {
        mOnItemListener = onItemListener;
    }
}
