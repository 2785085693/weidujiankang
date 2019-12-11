package com.wd.mvp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wd.mvp.presenter.BasePresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * date:2019/12/4
 * author:郑宏宇(Msi)
 * function:
 */
public abstract class BaseFragment <F extends BasePresenter> extends Fragment {

    public View inflate;
    public F f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(initLayout(), container, false);

        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        f = setFragment();
        f.attach(this);
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract F setFragment();

    protected abstract int initLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        f.detach();
    }
}
