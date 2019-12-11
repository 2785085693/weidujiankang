package com.wd.mvp;

import com.wd.mvp.model.bean.BannerBean;
import com.wd.mvp.model.bean.CircleBean;
import com.wd.mvp.model.bean.ConsultBean;
import com.wd.mvp.model.bean.ConsultListBean;
import com.wd.mvp.model.bean.DiseaseDetailsBean;
import com.wd.mvp.model.bean.DrugBean;
import com.wd.mvp.model.bean.DrugDetailsBean;
import com.wd.mvp.model.bean.FindDiseaseBean;
import com.wd.mvp.model.bean.FindDrugBean;
import com.wd.mvp.model.bean.HomeSearchBean;
import com.wd.mvp.model.bean.MySickBean;
import com.wd.mvp.model.bean.OfficeBean;
import com.wd.mvp.model.bean.SearchSickBean;
import com.wd.mvp.model.bean.SickCircleBean;
import com.wd.mvp.model.url.CantantUrl;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * date:2019/12/4
 * author:郑宏宇(Msi)
 * function:
 */
public interface API {
    //首页轮播
    @GET(CantantUrl.BANNER_URL)
    Observable<BannerBean>getBanner();
    //首页问诊咨询
    @GET(CantantUrl.OFFICE_URL)
    Observable<OfficeBean>getOffice();
    //健康咨询
    @GET(CantantUrl.CONSULT_URL)
    Observable<ConsultBean>getConsult();
    //健康咨询列表
    @GET(CantantUrl.CONSULTLIST_URL)
    Observable<ConsultListBean>getConsultList(@Query("plateId")int plateId,@Query("page")int page,@Query("count") int count);
    //首页搜索
    @GET(CantantUrl.HOMESEARCH_URL)
    Observable<HomeSearchBean>getHomeSearch(@Query("keyWord")String keyWord);
    //根据科室查看对应病症
    @GET(CantantUrl.DISEASE_URL)
    Observable<FindDiseaseBean>getDisease(@Query("departmentId")int departmentId);
    //药品分类
    @GET(CantantUrl.IDRUG_URL)
    Observable<DrugBean>getiDrug();
    //根据药品类目查询常见药品
    @GET(CantantUrl.DRUG_URL)
    Observable<FindDrugBean>getDrug(@Query("drugsCategoryId")int drugsCategoryId,@Query("page")int page,@Query("count")int count);
    //点击病症跳转病症详情
    @GET(CantantUrl.DISEASE_DETAILS_URL)
    Observable<DiseaseDetailsBean>getDiseaseDetails(@Query("id")int id);
    //点击药品跳转药品详情
    @GET(CantantUrl.DRUG_DETAILS_URL)
    Observable<DrugDetailsBean>getDrugDetails(@Query("id")int id);
    //病友圈科室
    @GET(CantantUrl.OFFICE_URL)
    Observable<OfficeBean>getRoom();
    //根据关键字查询病友圈
    @GET(CantantUrl.FINDSICK_URL)
    Observable<SearchSickBean>getSearch(@Query("keyWord") String keyWord);
    //查询病友圈详情
    @GET(CantantUrl.SICKCIRCLE_URL)
    Observable<SickCircleBean>getSickcir(@Query("sickCircleId") int sickCircleId);

    //病友圈列表
    @GET(CantantUrl.SEARCH_URL)
    Observable<CircleBean>getCircle(@Query("departmentId")int departmentId,@Query("page")int page,@Query("count")int count);

    //查询我的病友圈帖子的评论列表
    @GET(CantantUrl.MYSICK_URL)
    Observable<MySickBean> getMySick(@Header("userId") int userId,
                                     @Header("sessionId") String sessionId,
                                     @Query("sickCircleId") int sickCircleId,
                                     @Query("page") int page,
                                     @Query("count") int count
                                     );

}
