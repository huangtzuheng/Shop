package com.example.user.project.Utils.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.project.R;

public class NotificationDetailActivity extends AppCompatActivity {
    private TextView mOrderDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
        mOrderDetail = (TextView) findViewById(R.id.tv_order);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String gDetail = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mOrderDetail.setText(gDetail);
        }
    }
}
