package com.jensen.studymaterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.iv_detail_icon)
    ImageView mImageView;
    @OnClick(R.id.iv_detail_icon)
    public void click(View view){
        finish();
    }
    @BindView(R.id.detail_tool_bar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        switch (intent.getFlags()){
            case 0:
                int icon = intent.getIntExtra("icon", R.mipmap.ic_launcher_round);
                String name = intent.getStringExtra("name");
                mToolbar.setTitle(name);
                mImageView.setImageResource(icon);
                break;
        }
        mToolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //back
        finish();
    }
}
