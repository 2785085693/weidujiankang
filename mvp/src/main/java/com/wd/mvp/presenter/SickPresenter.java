package com.wd.mvp.presenter;

import com.wd.mvp.model.bean.SickCircleBean;
import com.wd.mvp.model.http.OkHttpUtils;
import com.wd.mvp.view.interfaces.IContractView;

public class SickPresenter extends BasePresenter<IContractView.iSickCircless>{

    public void getSickcir(int sickCircleId){
        OkHttpUtils.getInstance().getSickcir(new OkHttpUtils.IOkCallBack<SickCircleBean>() {

            @Override
            public void callSuccess(SickCircleBean bean) {
                getV().sickCild(bean);
            }

            @Override
            public void callError(String msg) {

            }
        },SickCircleBean.class,sickCircleId);
    }
}
