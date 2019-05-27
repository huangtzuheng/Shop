package com.example.user.project.Utils.Cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.project.R;
import com.example.user.project.Utils.Detail.CartDetailActivity;
import com.example.user.project.Utils.Detail.DetailActivity;
import com.example.user.project.Utils.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartAdapterOnClickHandler {
    private static final String TAG = "CartActivity";
    private static final int ACTIVITY_NUM = 3;
    private Context mContext = CartActivity.this;
    private RecyclerView mRecyclerView;
    private CartAdapter mCartAdapter;
//    private Button mButton;
    public String[] testDataC = {"aaaaa", "bbbbb", "ccccc"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Log.d(TAG, "onCreate: starting.");

        setupBottomNavigationView();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_cart);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mCartAdapter = new CartAdapter(testDataC,this, ACTIVITY_NUM);
        mRecyclerView.setAdapter(mCartAdapter);
//        mButton = (Button) findViewById(R.id.bt_detail);
    }

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    public void onClick(String thisGood) {
        Intent detailIntent = new Intent(this, CartDetailActivity.class);
        detailIntent.putExtra(Intent.EXTRA_TEXT, thisGood);
//        mButton.setText("Remove from Cart");
        startActivity(detailIntent);
    }
}


