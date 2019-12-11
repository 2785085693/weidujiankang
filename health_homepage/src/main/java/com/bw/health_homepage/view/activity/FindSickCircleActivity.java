package com.bw.health_homepage.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.health_homepage.R;
import com.bw.health_homepage.view.adapter.FindSickCircleAdapter;
import com.wd.mvp.model.bean.CircleBean;
import com.wd.mvp.model.bean.MySickBean;
import com.wd.mvp.model.bean.OfficeBean;
import com.wd.mvp.model.bean.SickCircleBean;
import com.wd.mvp.presenter.HomePresenter;
import com.wd.mvp.presenter.SickPresenter;
import com.wd.mvp.view.activity.BaseActivity;
import com.wd.mvp.view.interfaces.IContractView;
import com.wd.mvp.view.interfaces.IHomeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * date:2019/12/4
 * author:郑宏宇(Msi)
 * function:
 */
public class FindSickCircleActivity extends BaseActivity<SickPresenter> implements IContractView.iSickCircless {
   private RecyclerView findsick_recyclerview;
   private ImageView circle_ImageView;
   private TextView text_name;
   private int sickCircleId = 12;
    private ArrayList<SickCircleBean.ResultBean> list;
    private SickCircleBean.ResultBean result;
    private FindSickCircleAdapter findSickCircleAdapter;
    private RecyclerView poprv;
    private ImageView imageView;
    private ImageView tv;
    private TextView textView;
    private RelativeLayout relativeLayout;
    private EditText editText;
    private ImageView popSend;

    @Override
    protected void initData() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        findsick_recyclerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        p.getSickcir(sickCircleId);

    }

    @Override
    protected SickPresenter setPresenter() {
        return new SickPresenter();
    }

    @Override
    protected void initView() {
        findsick_recyclerview = findViewById(R.id.findsick_recyclerview);
        circle_ImageView = findViewById(R.id.circle_ImageView);
        text_name = findViewById(R.id.text_name);


    }

    @Override
    protected int initLayout() {
        return R.layout.findsick_circle;
    }


    @Override
    public void sickCild(SickCircleBean sickCircleBean) {
        result = sickCircleBean.getResult();
        list = new ArrayList<>();
        list.add(result);
        text_name.setText(sickCircleBean.getResult().getTitle());
        findSickCircleAdapter = new FindSickCircleAdapter(list, this);
        findsick_recyclerview.setAdapter(findSickCircleAdapter);
    }

    @Override
    public void mysick(MySickBean mySickBean) {
        findSickCircleAdapter.onItemClickListener(new FindSickCircleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                onItemClick(position);
                showPopupWindow();
            }
        });
    }

    private void showPopupWindow() {
        //找到pop弹窗布局
        View view = LayoutInflater.from(FindSickCircleActivity.this).inflate(R.layout.window_pinglun, null);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //返回键可退
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable()); // 响应返回键必须的语句
        popupWindow.setContentView(view);



        imageView = view.findViewById(R.id.image_pop);
        poprv = view.findViewById(R.id.rv_pop);
        tv = view.findViewById(R.id.pop_tv);
        textView = view.findViewById(R.id.pop_liuxia);
        relativeLayout = view.findViewById(R.id.pop_rel);
        editText = view.findViewById(R.id.pop_et);
        ImageView send = view.findViewById(R.id.pop_send);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //点击显示隐藏
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        InputMethodManager m = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }, 300);
                imageView.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });
        //点击发送评论
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString().trim();
                //发送评论
               // publishCommentPresenter.reqeust(String.valueOf(id),sessionId,sickCircleId1,content);
            }
        });


        //activity的布局
        View rootView = LayoutInflater.from(FindSickCircleActivity.this).inflate(R.layout.findsick_circle, null);
        //位置
        //设置高度
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.update();
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);

    }

}
