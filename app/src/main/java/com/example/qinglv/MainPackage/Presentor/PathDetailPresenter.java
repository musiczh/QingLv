package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Model.PathDetailModel;
import com.example.qinglv.MainPackage.Model.iModel.IModelPathDetail;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterPathDetail;
import com.example.qinglv.MainPackage.View.activity.IViewPathDetail;

public class PathDetailPresenter implements IPresenterPathDetail{
    private IViewPathDetail iViewPathDetail;
    private IModelPathDetail iModelPathDetail = new PathDetailModel();

    public PathDetailPresenter(IViewPathDetail iViewPathDetail){
        this.iViewPathDetail = iViewPathDetail;
    }
    @Override
    public void init(final int id) {
        iModelPathDetail.getData(id, new IModelPathDetail.CallBack() {
            @Override
            public void onSucceed(Path path) {
                iViewPathDetail.setPath(path);
            }

            @Override
            public void onError(String errorType) {
                iViewPathDetail.onError(errorType);
            }
        });
    }
}
