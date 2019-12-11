package com.example.health_video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wd.mvp.presenter.BasePresenter;
import com.wd.mvp.view.fragment.BaseFragment;

public class VideoFragment extends BaseFragment {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter setFragment() {
        return new BasePresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.item_main;
    }
}
