package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Model.PathDetailModel;
import com.example.qinglv.MainPackage.Model.iModel.IModelDetail;
import com.example.qinglv.MainPackage.Presentor.iPresenter.BasePresenter;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterDetail;
import com.example.qinglv.MainPackage.View.activity.IViewDetail;

/**
 * 路线详情页的presenter
 */
public class PathDetailPresenter extends BasePresenter<IViewDetail<Path>> implements IPresenterDetail {
    private IModelDetail<Path> iModelDetail = new PathDetailModel();


    @Override
    public void init( int id) {
        iModelDetail.getData(id, new IModelDetail.CallBack<Path>() {
            @Override
            public void onSucceed(Path detail) {
                if (isAttached()) {
                    getView().setDetail(detail);
                }
            }

            @Override
            public void onError(String errorType) {
                if (isAttached()) {
                    getView().onError(errorType);
                }
            }
        });
    }
}
