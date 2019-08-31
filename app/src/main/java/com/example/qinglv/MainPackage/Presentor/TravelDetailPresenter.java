package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.Entity.TravelDetail;
import com.example.qinglv.MainPackage.Model.TravelDetailModel;
import com.example.qinglv.MainPackage.inter.iApiMvp.BasePresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelDetail;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterDetail;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewDetail;

public class TravelDetailPresenter extends BasePresenter<IViewDetail<TravelDetail>> implements IPresenterDetail {

    private IModelDetail<TravelDetail> iModelDetail = new TravelDetailModel();

    //初始化详情页界面数据的逻辑
    @Override
    public void init(final int id) {

        iModelDetail.getData(id, new IModelDetail.CallBack<TravelDetail>() {
            @Override
            public void onSucceed(TravelDetail detail) {
                if (isAttached())
                getView().setDetail(detail);
            }

            @Override
            public void onError(String errorType) {
                if (isAttached())
                    getView().onError(errorType);
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

    //点赞的逻辑
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

    //收藏的逻辑
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
