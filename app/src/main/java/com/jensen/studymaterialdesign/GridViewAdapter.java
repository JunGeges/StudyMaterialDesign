package com.jensen.studymaterialdesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/9.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<GvItemBean> mGvItemBeanArrayList;
    private LayoutInflater mLayoutInflater;

    public GridViewAdapter(Context context, ArrayList<GvItemBean> gvItemBeanArrayList) {
        mContext = context;
        mGvItemBeanArrayList = gvItemBeanArrayList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mGvItemBeanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGvItemBeanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_grid_view, parent, false);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.gv_item_iv);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.gv_item_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        //绑值
        GvItemBean gvItemBean = mGvItemBeanArrayList.get(position);
        viewHolder.mImageView.setImageResource(gvItemBean.getIcon());
        viewHolder.mTextView.setText(gvItemBean.getTitle());
        return convertView;
    }

    static class ViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }
}
