package com.example.user.project.Utils.Home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.project.R;

public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.GoodAdapterViewHolder> {
    private String[] mGoodData;
//    private int mTestNumbers;

    public GoodAdapter(String[] mData) {
        mGoodData = mData;
    }

    public class GoodAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView mGoodTextView;
        public LinearLayout mLinearLayout;

        public GoodAdapterViewHolder(View view) {
            super(view);
//            view.setOnClickListener(this);
            mGoodTextView = (TextView) view.findViewById(R.id.tv_good_data);
            mLinearLayout = (LinearLayout) view.findViewById(R.id.ll);
        }

//        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
        }
    }

    @Override
    public GoodAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.good_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new GoodAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodAdapterViewHolder goodAdapterViewHolder, int position) {
        String GoodForThisOne = mGoodData[position];
        goodAdapterViewHolder.mGoodTextView.setText(GoodForThisOne);
    }

    @Override
    public int getItemCount() {
        if (mGoodData == null) return 0;
        return mGoodData.length;
    }

    public void setGoodData(String[] goodData) {
        mGoodData = goodData;
        notifyDataSetChanged();
    }
}
