package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Model.PathDetailModel;
import com.example.qinglv.MainPackage.Model.iModel.IModelPathDetail;
import com.example.qinglv.MainPackage.Presentor.iPresenter.BasePresenter;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterPathDetail;
import com.example.qinglv.MainPackage.View.activity.IViewPathDetail;

/**
 * 路线详情页的presenter
 */
public class PathDetailPresenter extends BasePresenter<IViewPathDetail> implements IPresenterPathDetail{
    private IModelPathDetail iModelPathDetail = new PathDetailModel();


    @Override
    public void init(final int id) {
        iModelPathDetail.getData(id, new IModelPathDetail.CallBack() {
            @Override
            public void onSucceed(Path path) {
                if (isAttached()) {
                    getView().setPath(path);
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
