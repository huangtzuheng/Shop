package com.example.user.project.Utils.Notification;

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
import com.example.user.project.Utils.Cart.CartAdapter;
import com.example.user.project.Utils.Detail.CartDetailActivity;
import com.example.user.project.Utils.Detail.NotificationDetailActivity;
import com.example.user.project.Utils.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class NotificationActivity extends AppCompatActivity implements CartAdapter.CartAdapterOnClickHandler {
    private static final String TAG = "NotificationActivity";
    private static final int ACTIVITY_NUM = 2;
    private Context mContext = NotificationActivity.this;
    private RecyclerView mRecyclerView;
    private CartAdapter mCartAdapter;
    public String[] testDataD = {"11111", "22222", "33333"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Log.d(TAG, "onCreate: starting.");

        setupBottomNavigationView();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_notif);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mCartAdapter = new CartAdapter(testDataD,this, ACTIVITY_NUM);
        mRecyclerView.setAdapter(mCartAdapter);
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
        Intent detailIntent = new Intent(this, NotificationDetailActivity.class);
        detailIntent.putExtra(Intent.EXTRA_TEXT, thisGood);
        startActivity(detailIntent);
    }
}


