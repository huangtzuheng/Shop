package com.example.user.project.Utils.Detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.project.Item;
import com.example.user.project.ItemDAO;
import com.example.user.project.R;
import com.example.user.project.User;
import com.example.user.project.UserDAO;
import com.example.user.project.Utils.Home.MainActivity;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private TextView mGoodTitle, mGoodSize, mGoodPrice, mGoodDescription;
    private TextView mUserName, mUserScore, mUserPhone;
    private ImageView mGoodimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        LinkElements();


        Bundle bundle =this.getIntent().getExtras();
        long GoodID =  bundle.getLong("GoodID",0);

        if(GoodID == 0){
//            finish();
        }else{
            Item thisGood = new ItemDAO(this).get(GoodID);
            mGoodTitle.setText(thisGood.getTITLE());
            mGoodPrice.setText(String.valueOf(thisGood.getPRICE()));
            mGoodSize.setText(String.valueOf(thisGood.getSIZE()));
            mGoodDescription.setText(thisGood.getDESCRIPTION());

            UserDAO userDAO = new UserDAO(this);
            User thisUser = userDAO.get(thisGood.getUId());
            mUserName.setText(thisUser.getName());
            mUserPhone.setText(thisUser.getPhone());
            mUserScore.setText(String.valueOf(thisUser.getScore()));

            Picasso.get().load(thisGood.getPICTURE()).into(mGoodimg);

        }

//        Intent intentThatStartedThisActivity = getIntent();
//        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
//            String gDetail = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
//            mGoodDetail.setText(gDetail);
//        }
    }

    public void LinkElements(){
        mGoodTitle = (TextView) findViewById(R.id.goodInfo_title);
        mGoodSize = (TextView) findViewById(R.id.goodInfo_size);
        mGoodPrice = (TextView) findViewById(R.id.goodInfo_price);
        mGoodDescription = (TextView) findViewById(R.id.goodInfo_seller_description);

        mUserName= (TextView) findViewById(R.id.goodInfo_seller_name);
        mUserScore= (TextView) findViewById(R.id.goodInfo_seller_score);
        mUserPhone= (TextView) findViewById(R.id.goodInfo_seller_phone);

        mGoodimg = (ImageView) findViewById(R.id.goodInfo_pic);
    }
}
