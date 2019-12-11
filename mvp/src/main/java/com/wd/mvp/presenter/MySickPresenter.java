package com.wd.mvp.presenter;

import com.wd.mvp.model.bean.MySickBean;
import com.wd.mvp.model.bean.SickCircleBean;
import com.wd.mvp.model.http.OkHttpUtils;
import com.wd.mvp.view.interfaces.IContractView;

public class MySickPresenter extends BasePresenter< IContractView.iSickCircless> {
    public void getMysick(int userId,String sessionId,int sickCircleId,int page,int count){
        OkHttpUtils.getInstance().getMySick(new OkHttpUtils.IOkCallBack<MySickBean>() {
            @Override
            public void callSuccess(MySickBean bean) {
                getV().mysick(bean);
            }

            @Override
            public void callError(String msg) {

            }
        },MySickBean.class,userId,sessionId,sickCircleId,page,count);
    }
}
