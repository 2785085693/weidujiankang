package com.bw.health_homepage.view.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.util.Log;
import android.view.FrameStats;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.TimePickerView;
import com.bw.health_homepage.R;
import com.bw.health_homepage.view.adapter.ImageAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.wd.mvp.presenter.BasePresenter;
import com.wd.mvp.view.activity.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class PublishActivity extends BaseActivity {
    private ImageView keshi;
    private ImageView zhuyao;
    private ImageView xia_ImageView;
    private ImageView time_ImageView;
    private RecyclerView add_ImageView;
    private TextView add_TextView;
    private EditText et_apply_department;
    private EditText bingzheng;
    private TextView time_TextView;
    private TimePickerView pvTime;
    private TextView over_time;
    private List<LocalMedia> images;
    private ImageAdapter imageAdapter;
    private Button but_Publish;

    @Override
    protected void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        add_ImageView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        p.attach(this);
    }

    @Override
    protected BasePresenter setPresenter() {
        return new BasePresenter();
    }

    @Override
    protected void initView() {
        keshi = findViewById(R.id.keshi);
        et_apply_department = findViewById(R.id.et_apply_department);
        bingzheng = findViewById(R.id.bingzheng);
        zhuyao = findViewById(R.id.zhuyao);
        add_ImageView = findViewById(R.id.add_ImageView);
        add_TextView = findViewById(R.id.add_TextView);
        time_TextView = findViewById(R.id.time_TextView);
        time_ImageView = findViewById(R.id.time_ImageView);
        xia_ImageView = findViewById(R.id.xia_ImageView);
        over_time = findViewById(R.id.over_time);
        but_Publish = findViewById(R.id.but_Publish);

        but_Publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PublishActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                    PublishActivity.this.finish();
                }
        });

        ArrayList<String> strings = new ArrayList<>();

        imageAdapter = new ImageAdapter(strings,PublishActivity.this);

        //  imageAdapter.addItem("res://com.dingtao.rrmmp/R.drawable.add");
//        recyclerView.setAdapter(iamgeAdpater);
        add_ImageView.setAdapter(imageAdapter);


        time_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTimex();
            }
        });

        xia_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTimeo();
            }
        });


        add_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(PublishActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(6)
                        .minSelectNum(1)
                        .imageSpanCount(4)
                        .selectionMedia(images)// 是否传入已选图片 List<LocalMedia> list
                        .selectionMode(PictureConfig.MULTIPLE)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }

        });
        imageAdapter.onItemClickListenerx(new ImageAdapter.OnItemClickListenerx() {
            @Override
            public void onItemClick(int position) {
                onItemClick(position);
                notify();
            }
        });


        clearCache();


        keshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String [] list={"内科", "眼科", "骨科", "儿科", "传染病科", "皮肤病科", "耳鼻喉科", "精神病科"};
                final ListPopupWindow listPopupWindow;
                listPopupWindow = new ListPopupWindow(PublishActivity.this);
                listPopupWindow.setAdapter(new ArrayAdapter<String>(PublishActivity.this,android.R.layout.simple_list_item_1,list));
                listPopupWindow.setAnchorView(et_apply_department);
                listPopupWindow.setModal(true);
                et_apply_department.setSelection(et_apply_department.getText().length());
                listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        et_apply_department.setText(list[position]);
                        listPopupWindow.dismiss();
                    }
                });
                listPopupWindow.show();
                et_apply_department.setCursorVisible(false);

            }
        });
        zhuyao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String [] list={"猩红热", "百日咳", "水痘", "风疹", "麻疹",

                        "儿童骨髓炎", "儿童风湿热", "小儿肾炎","小儿遗尿症", "小儿夜哭",
                        "儿童骨髓炎", "儿童风湿热", "小儿肾炎","小儿遗尿症", "小儿夜哭"};
                final ListPopupWindow listPopupWindow;
                listPopupWindow = new ListPopupWindow(PublishActivity.this);
                listPopupWindow.setAdapter(new ArrayAdapter<String>(PublishActivity.this,android.R.layout.simple_list_item_1,list));
                listPopupWindow.setAnchorView(bingzheng);
                listPopupWindow.setModal(true);
                bingzheng.setSelection(bingzheng.getText().length());
                listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        bingzheng.setText(list[position]);
                        listPopupWindow.dismiss();
                    }
                });
                listPopupWindow.show();
                et_apply_department.setCursorVisible(false);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    images = PictureSelector.obtainMultipleResult(data);
                    for (int i = 0; i <images.size() ; i++) {
                        imageAdapter.addItem("file://"+images.get(i).getPath());

                    }
                    imageAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    /**
     * 清空图片缓存，包括裁剪、压缩后的图片
     */
    private void clearCache() {
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    //清除缓存
                    PictureFileUtils.deleteCacheDirFile(PublishActivity.this);
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private void getTimex() {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String time = getTime(date);
                time_TextView.setText(time);

            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(12)//滚轮文字大小
                .setTitleSize(14)//标题文字大小
                .setTitleText("选择日期")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                //.setSubmitColor(Color.BLUE)//确定按钮文字颜色
                //.setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(Color.BLUE)//标题背景颜色 Night mode
                //.setBgColor(Color.blue(1))//滚轮背景颜色 Night mode
                //.setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                //.setRangDate(startDate,endDate)//起始终止年月日设定
                //.setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();
                 pvTime.show();

    }

    private void getTimeo() {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String time = getTime(date);
                over_time.setText(time);
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(12)//滚轮文字大小
                .setTitleSize(14)//标题文字大小
                .setTitleText("选择日期")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                //.setSubmitColor(Color.BLUE)//确定按钮文字颜色
                //.setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(Color.BLUE)//标题背景颜色 Night mode
                //.setBgColor(Color.blue(1))//滚轮背景颜色 Night mode
                //.setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                //.setRangDate(startDate,endDate)//起始终止年月日设定
                //.setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();
        pvTime.show();

    }

    @Override
    protected int initLayout() {
        return R.layout.publish;
    }


    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
