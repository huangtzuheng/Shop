package com.example.user.project.Utils.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.user.project.R;

public class CartDetailActivity extends AppCompatActivity {
    private TextView mGoodDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detail);
        mGoodDetail = (TextView) findViewById(R.id.tv_cartGoodTitle);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String gDetail = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mGoodDetail.setText(gDetail);
        }
    }
}
