package com.bw.health_homepage.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.health_homepage.R;
import com.bw.health_homepage.view.activity.FindSickCircleActivity;
import com.bw.health_homepage.view.activity.PublishActivity;
import com.bw.health_homepage.view.activity.SearchActivity;
import com.bw.health_homepage.view.adapter.CircleAdapter;
import com.bw.health_homepage.view.adapter.CircleRoomAdapter;
import com.bw.health_homepage.view.adapter.SearchAdapter;
import com.wd.mvp.model.bean.CircleBean;
import com.wd.mvp.model.bean.OfficeBean;
import com.wd.mvp.model.bean.SearchSickBean;
import com.wd.mvp.model.bean.SickCircleBean;
import com.wd.mvp.presenter.BasePresenter;
import com.wd.mvp.presenter.CirclePresenter;
import com.wd.mvp.presenter.HomePresenter;
import com.wd.mvp.view.fragment.BaseFragment;
import com.wd.mvp.view.interfaces.IContractView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CircleFragment extends BaseFragment<HomePresenter> implements IContractView.iCircleView,IContractView.iSearch {

    private RecyclerView circle_recycle1;
    private RecyclerView circle_recycle2;
    private RecyclerView circle_recycle3;
    private List<OfficeBean.ResultBean> result;
    private CirclePresenter circlePresenter;
    private int id;
    private int sickCircleId;
    private int page = 2;
    private int count =5;
    private CircleRoomAdapter circleRoomAdapter;
    private List<CircleBean.ResultBean> result1;
    private CircleAdapter circleAdapter;
    private ImageView circle_ImageView;
    private ImageView circle_sousuo;
    private EditText item_EditText;
    private LinearLayout ll_search;  //外层的搜索框控件
    private LinearLayout sousuo_LinearLayout;  //外层的搜索框控件

    private int mLlSearchHeight; // 搜索框的高度

    private int mScrollY;  //recyclerview 滑动的距离

    private boolean isShow = true;  //搜索框是否显示

    private boolean isAnimmating;//是否正在进行动画
    private List<CircleBean.ResultBean> result2;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        circle_recycle1 = inflate.findViewById(R.id.circle_recycle1);
        circle_recycle2 = inflate.findViewById(R.id.circle_recycle2);
        circle_recycle3 = inflate.findViewById(R.id.circle_recycle3);
        item_EditText = inflate.findViewById(R.id.item_EditText);
        circle_ImageView = inflate.findViewById(R.id.circle_ImageView);
        circle_sousuo = inflate.findViewById(R.id.circle_sousuo);
        ll_search = inflate.findViewById(R.id.ll_search);
        sousuo_LinearLayout = inflate.findViewById(R.id.sousuo_LinearLayout);
        circlePresenter = new CirclePresenter();
        circlePresenter.attach(this);
        circlePresenter.getRoom();
        circlePresenter.getCircle(2,2,5);
    }

    @Override
    protected HomePresenter setFragment() {
        return new HomePresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.item_my;
    }

    @Override
    public void iRoomSuccess(OfficeBean officeBean) {
        result = officeBean.getResult();
        circleRoomAdapter = new CircleRoomAdapter(getActivity(),result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        circle_recycle1.setLayoutManager(linearLayoutManager);
        circle_recycle1.setAdapter(circleRoomAdapter);

        circle_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        circleRoomAdapter.onItemClickListener(new CircleRoomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int id = result.get(position).getId();
                circlePresenter.getCircle(id,page,count);

            }
        });
        item_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyWord = item_EditText.getText().toString().trim();
                f.getSearch(keyWord);
            }
        });
    }

    @Override
    public void iCircleSuccess(CircleBean circleBean) {

        result1 = circleBean.getResult();
        circleAdapter = new CircleAdapter(getContext(),result1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        circle_recycle2.setLayoutManager(linearLayoutManager);
        circle_recycle2.setAdapter(circleAdapter);

        circleAdapter.setmOnItemClickListener(new CircleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                onItemClick(position);


                Intent intent = new Intent(getContext(), FindSickCircleActivity.class);
               // intent.putExtra("sickCircleId",position);
                startActivity(intent);

            }
        });


        circle_recycle2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dy是垂直滚动距离，手指上滑动的时候为正，手指下滑的时候为负

                //需要获取llSearch 的高度作为 判断条件   所以布局文件中  llSearch的 visiable属性  不能设置为 gone
                // 设置为  gone之后，llSearch 不进行渲染  获取不到高度

                if (mLlSearchHeight == 0) {
                    mLlSearchHeight = ll_search.getHeight();
                }
                //记录滑动的距离
                mScrollY += dy;

                if (mScrollY <= 0) {
                    sousuo_LinearLayout.setVerticalGravity(View.VISIBLE);
                    ll_search.setVisibility(View.GONE);
                } else {
                    ll_search.setVisibility(View.VISIBLE);
                    sousuo_LinearLayout.setVerticalGravity(View.GONE);
                }

                if (isAnimmating || (mScrollY <= mLlSearchHeight)) {
                    return;
                }

                if (dy < 0) {

                    if (isShow) {
                        return;
                    }

                    ObjectAnimator animator = ObjectAnimator.ofFloat(ll_search, "translationY", -mLlSearchHeight, 0);
                    animator.setDuration(300);
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            isShow = true;
                            isAnimmating = false;
                            animation.removeAllListeners();
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            isAnimmating = true;

                        }
                    });
                    animator.start();
                } else {
                    if (!isShow) {
                        return;
                    }
                    ObjectAnimator animator = ObjectAnimator.ofFloat(ll_search, "translationY", 0, -mLlSearchHeight);
                    animator.setDuration(300);
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            isShow = false;
                            isAnimmating = false;
                            animation.removeAllListeners();
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            isAnimmating = true;

                        }
                    });
                    animator.start();
                }

            }
        });
    }


    @Override
    public void iSearchSick(SearchSickBean searchSickBean) {
        if (searchSickBean.getMessage().equals("抱歉！没有找到相关病友圈")){
            Toast.makeText(getContext(), searchSickBean.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }else{


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                    (getContext(),LinearLayoutManager.VERTICAL,false);

            SearchAdapter searchAdapter = new SearchAdapter(getContext(), searchSickBean.getResult());
            circle_recycle2.setAdapter(searchAdapter);
            circle_recycle2.setLayoutManager(linearLayoutManager);

        }
    }
}
