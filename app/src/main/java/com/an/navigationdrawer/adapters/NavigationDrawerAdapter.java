package com.an.navigationdrawer.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.an.navigationdrawer.R;
import com.an.navigationdrawer.config.AppConst;
import com.an.navigationdrawer.dto.NavDrawerItem;
import com.an.navigationdrawer.listeners.OnItemClickListener;

import java.util.Collections;
import java.util.List;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {

    private Context mContext;
    private List<NavDrawerItem> mData = Collections.emptyList();
    private OnItemClickListener<NavDrawerItem> mListener;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data, @NonNull OnItemClickListener<NavDrawerItem> listener) {
        this.mContext = context;
        this.mData = data;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_navigation_drawer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem navigationItem = mData.get(position);
        holder.mTvDrawerItemTitle.setText(navigationItem.getTitle());
        if (navigationItem.getTitle().equals(AppConst.NAV_ITEM_1)) {
            holder.mIvItemIcon.setBackground(mContext.getResources().getDrawable(R.drawable.ic_about));
        } else if (navigationItem.getTitle().equals(AppConst.NAV_ITEM_2)) {
            holder.mIvItemIcon.setBackground(mContext.getResources().getDrawable(R.drawable.ic_about));
        } else if (navigationItem.getTitle().equals(AppConst.NAV_ITEM_3)) {
            holder.mIvItemIcon.setBackground(mContext.getResources().getDrawable(R.drawable.ic_about));
        } else if (navigationItem.getTitle().equals(AppConst.NAV_ITEM_4)) {
            holder.mIvItemIcon.setBackground(mContext.getResources().getDrawable(R.drawable.ic_about));
        } else if (navigationItem.getTitle().equals(AppConst.NAV_ITEM_5)) {
            holder.mIvItemIcon.setBackground(mContext.getResources().getDrawable(R.drawable.ic_about));
        } else if (navigationItem.getTitle().equals(AppConst.NAV_ITEM_6)) {
            holder.mIvItemIcon.setBackground(mContext.getResources().getDrawable(R.drawable.ic_about));
        }

        if (navigationItem.isSelectedItem()) {
            holder.mRlBase.setBackground(mContext.getResources().getDrawable(R.drawable.item_selected_background));
            holder.mTvDrawerItemTitle.setTypeface(null, Typeface.BOLD);
        } else {
            holder.mRlBase.setBackground(mContext.getResources().getDrawable(R.drawable.item_click_background));
            holder.mTvDrawerItemTitle.setTypeface(null, Typeface.NORMAL);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout mRlBase;
        ImageView mIvItemIcon;
        TextView mTvDrawerItemTitle;

        public MyViewHolder(View view) {
            super(view);

            mRlBase = view.findViewById(R.id.row_navigation_drawer_rl_base);
            mIvItemIcon = view.findViewById(R.id.row_navigation_drawer_iv_item_icon);
            mTvDrawerItemTitle = view.findViewById(R.id.row_navigation_drawer_tv_item_title);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = MyViewHolder.this.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position, mData.get(position));
                    }
                }
            });
        }
    }

}
