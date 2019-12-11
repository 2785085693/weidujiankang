package com.wd.mvp.presenter;

import com.wd.mvp.model.bean.SearchSickBean;
import com.wd.mvp.model.http.OkHttpUtils;
import com.wd.mvp.view.interfaces.IContractView;

/**
 * date:2019/12/3
 * author:郑宏宇(Msi)
 * function:
 */
public class HomePresenter extends BasePresenter<IContractView.iSearch> {
    public void getSearch(String keyWord){
        OkHttpUtils.getInstance().getSearch(new OkHttpUtils.IOkCallBack<SearchSickBean>() {
            @Override
            public void callSuccess(SearchSickBean bean) {
                getV().iSearchSick(bean);
            }

            @Override
            public void callError(String msg) {

            }
        },SearchSickBean.class,keyWord);
    }

}
