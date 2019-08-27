package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.Model.ScenicDetailModel;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelDetail;
import com.example.qinglv.MainPackage.inter.iApiMvp.BasePresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterDetail;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewDetail;

/**
 * 风景详情页的presenter
 */
public class ScenicDetailPresenter extends BasePresenter<IViewDetail<Scenic>> implements IPresenterDetail {

    @Override
    public void init(int id) {
        IModelDetail<Scenic> iModelDetail = new ScenicDetailModel();
        iModelDetail.getData(id, new IModelDetail.CallBack<Scenic>() {
            @Override
            public void onSucceed(Scenic detail) {
                if (isAttached())
                getView().setDetail(detail);
            }

            @Override
            public void onError(String errorType) {
                if (isAttached())
                getView().onError(errorType);
            }
        });


    }
}
