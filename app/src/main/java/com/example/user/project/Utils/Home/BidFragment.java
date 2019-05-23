package com.example.user.project.Utils.Home;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.project.R;
import com.example.user.project.Utils.Detail.DetailActivity;

public class BidFragment extends Fragment implements GoodAdapter.GoodAdapterOnClickHandler {
    private static final String TAG = "BidFragment";
    public String[] testDataA = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};
    private RecyclerView mRecyclerView;
    private GoodAdapter mGoodAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_bid, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_bid);
        GridLayoutManager layoutManager
                = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mGoodAdapter = new GoodAdapter(testDataA, this);
        mRecyclerView.setAdapter(mGoodAdapter);
        return  view;
    }
    public void onClick(String thisGood) {
        Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
        detailIntent.putExtra(Intent.EXTRA_TEXT, thisGood);
        startActivity(detailIntent);
    }
}
