package com.bw.health_homepage.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.health_homepage.R;
import com.bw.health_homepage.view.fragment.CircleFragment;
import com.bw.health_homepage.view.fragment.HomePageFragment;
import com.example.health_video.VideoFragment;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MyActivity extends AppCompatActivity implements View.OnClickListener {


    private List<Fragment> list = new ArrayList<>();
    private ImageView sick_circle_img;
    private ImageView comments_img;
    private HomePageFragment homePageFragment;
    private CircleFragment circleFragment;
    private VideoFragment videoFragment;
    private long mExitTime;
    private ImageView rb_shou_wei;
    private ImageView rb_shou_xuan;
    private ImageView rb_movie_wei;
    private ImageView rb_movie_xuan;
    private LinearLayout home_lin;
    private LinearLayout movie_lin;
    private LinearLayout sick_lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_activity);
        initView();


        homePageFragment = new HomePageFragment();
        circleFragment = new CircleFragment();
        videoFragment = new VideoFragment();
        list.add(homePageFragment);
        list.add(circleFragment);
        list.add(videoFragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.vp, homePageFragment)
                .add(R.id.vp, circleFragment)
                .add(R.id.vp, videoFragment)
                .show(homePageFragment)
                .hide(circleFragment)
                .hide(videoFragment)
                .commit();
    }


    private void initView() {
        sick_circle_img = (ImageView) findViewById(R.id.sick_circle_img);
        comments_img = (ImageView) findViewById(R.id.comments_img);
        rb_shou_wei = (ImageView) findViewById(R.id.rb_shou_wei);
        rb_shou_xuan = (ImageView) findViewById(R.id.rb_shou_xuan);
        rb_movie_wei = (ImageView) findViewById(R.id.rb_movie_wei);
        rb_movie_xuan = (ImageView) findViewById(R.id.rb_movie_xuan);
        home_lin = (LinearLayout) findViewById(R.id.home_lin);
        home_lin.setOnClickListener(this);
        movie_lin = (LinearLayout) findViewById(R.id.movie_lin);
        movie_lin.setOnClickListener(this);
        sick_lin = (LinearLayout) findViewById(R.id.sick_lin);
        sick_lin.setOnClickListener(this);
        comments_img.setOnClickListener(this);
    }

    public void onClick(View v) {

        comments_img.setVisibility(View.GONE);
        rb_movie_xuan.setVisibility(View.GONE);
        rb_shou_xuan.setVisibility(View.GONE);

        sick_circle_img.setVisibility(View.VISIBLE);
        rb_movie_wei.setVisibility(View.VISIBLE);
        rb_shou_wei.setVisibility(View.VISIBLE);

        if (v.getId() == R.id.home_lin) {
            rb_shou_xuan.setVisibility(View.VISIBLE);
            rb_shou_wei.setVisibility(View.GONE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(homePageFragment)
                    .hide(circleFragment)
                    .hide(videoFragment)
                    .commit();
        } else if (v.getId() == R.id.sick_lin) {
            comments_img.setVisibility(View.VISIBLE);
            sick_circle_img.setVisibility(View.GONE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(circleFragment)
                    .hide(videoFragment)
                    .hide(homePageFragment)
                    .commit();

        } else if (v.getId() == R.id.movie_lin) {
            rb_movie_xuan.setVisibility(View.VISIBLE);
            rb_movie_wei.setVisibility(View.GONE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(videoFragment)
                    .hide(homePageFragment)
                    .hide(circleFragment)
                    .commit();

        } else if (v.getId() == R.id.comments_img) {
            startActivity(new Intent(MyActivity.this, PublishActivity.class));
        }
    }


    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
