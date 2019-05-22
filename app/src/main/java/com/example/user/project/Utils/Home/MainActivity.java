package com.example.user.project.Utils.Home;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.project.R;
import com.example.user.project.Utils.Detail.DetailActivity;
import com.example.user.project.Utils.Utils.BottomNavigationViewHelper;
import com.example.user.project.Utils.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.example.user.project.Utils.Home.GoodAdapter.GoodAdapterOnClickHandler;

public class MainActivity extends AppCompatActivity implements GoodAdapter.GoodAdapterOnClickHandler {
    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = MainActivity.this;
    private RecyclerView mRecyclerView;
    private GoodAdapter mGoodAdapter;
    public String[] testData = {"a","b","c","d","e","f","g","h","i","j","k","l","m"};

    //    private TextView mErrorMessageDisplay;
//    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: starting.");

        initImageLoader();
        setupBottomNavigationView();
        setupViewPager();
        //  made by austin
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_good);

        GridLayoutManager layoutManager
                = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mGoodAdapter = new GoodAdapter(testData, this);
        mRecyclerView.setAdapter(mGoodAdapter);
//        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
    }
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    private void setupViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new MessagesFragment());
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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
        Context context = this;
//        Toast.makeText(context, thisGood, Toast.LENGTH_SHORT)
//                .show();
        Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
        detailIntent.putExtra(Intent.EXTRA_TEXT, thisGood);
        startActivity(detailIntent);
    }
    private Item item;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processViews();
        processControllers();

        // 建立資料庫物件
        itemDAO = new ItemDAO(getApplicationContext());

        // 如果資料庫是空的，就建立一些範例資料
        // 這是為了方便測試用的，完成應用程式以後可以拿掉
        if (item.getCount() == 0) {
            item.sample();
        }

        // 取得所有記事資料
        items = itemDAO.getAll();

        itemAdapter = new ItemAdapter(this, R.layout.single_item, items);
        item_list.setAdapter(itemAdapter);
    }

//    private void loadGoodData() {
//        showGoodDataView();
//
//        String location = SunshinePreferences.getPreferredWeatherLocation(this);
//        new FetchWeatherTask().execute(location);
//    }

//    private void showGoodDataView() {
//        mRecyclerView.setVisibility(View.VISIBLE);
//    }
}
