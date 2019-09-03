package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Model.PathDetailModel;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelDetail;
import com.example.qinglv.MainPackage.inter.iApiMvp.BasePresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterDetail;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewDetail;

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

        iModelDetail.isStar(id, new IModelDetail.CallBackStar() {
            @Override
            public void onSucceed(boolean isStar) {
                if (isAttached()) getView().setHeart(isStar);
            }

            @Override
            public void onError(String errorType) {
                if (isAttached()) getView().onError(errorType);
            }
        });
        iModelDetail.isCollection(id, new IModelDetail.CallBackStar() {
            @Override
            public void onSucceed(boolean isStar) {
                if (isAttached()) getView().setCollection(isStar);
            }

            @Override
            public void onError(String errorType) {
                if (isAttached()) getView().onError(errorType);
            }
        });
    }


    @Override
    public void setStar(int articleId) {
        iModelDetail.setStar(articleId, new IModelDetail.CallBackStar() {
            @Override
            public void onSucceed(boolean isStar) {
                if (isAttached()) getView().setHeart(isStar);
            }

            @Override
            public void onError(String errorType) {
                if (isAttached()) getView().onError(errorType);
            }
        });
    }

    @Override
    public void setCollection(int articleId) {
        iModelDetail.setCollection(articleId, new IModelDetail.CallBackStar() {
            @Override
            public void onSucceed(boolean isStar) {
                if (isAttached()) getView().setCollection(isStar);
            }

            @Override
            public void onError(String errorType) {
                if (isAttached()) getView().onError(errorType);
            }
        });
    }
}
