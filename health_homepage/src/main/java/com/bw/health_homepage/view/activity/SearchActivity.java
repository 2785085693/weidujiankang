package com.bw.health_homepage.view.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.health_homepage.R;
import com.bw.health_homepage.view.adapter.CircleAdapter;
import com.bw.health_homepage.view.adapter.SearchAdapter;
import com.wd.mvp.model.bean.SearchSickBean;
import com.wd.mvp.presenter.BasePresenter;
import com.wd.mvp.presenter.HomePresenter;
import com.wd.mvp.view.activity.BaseActivity;
import com.wd.mvp.view.interfaces.IContractView;

import java.util.ArrayList;
import java.util.List;


/**
 * date:2019/12/4
 * author:郑宏宇(Msi)
 * function:查询
 */
public class SearchActivity extends BaseActivity<HomePresenter> implements IContractView.iSearch {
    private ImageView circle_ImageView;
    private TextView search_TextView;
    private EditText search_EditText;
    private List<SearchSickBean.ResultBean> result;
    private RecyclerView searchRecyclerview;


    @Override
    protected void initData() {

    }

    @Override
    protected HomePresenter setPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        circle_ImageView = findViewById(R.id.circle_ImageView);
        search_TextView = findViewById(R.id.search_TextView);
        search_EditText = findViewById(R.id.search_EditText);
        searchRecyclerview = (RecyclerView) findViewById(R.id.search_recyclerview);
        

        circle_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });

        search_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyWord = search_EditText.getText().toString().trim();
                p.getSearch(keyWord);
            }
        });



    }

    @Override
    protected int initLayout() {
        return R.layout.search;
    }


    @Override
    public void iSearchSick(SearchSickBean searchSickBean) {
        if (searchSickBean.getMessage().equals("抱歉！没有找到相关病友圈")){
            Toast.makeText(this, searchSickBean.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }else{
            result = searchSickBean.getResult();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                    (this,LinearLayoutManager.VERTICAL,false);


            SearchAdapter searchAdapter = new SearchAdapter(this, searchSickBean.getResult());
            searchRecyclerview.setAdapter(searchAdapter);
            searchRecyclerview.setLayoutManager(linearLayoutManager);


        }
    }
}
