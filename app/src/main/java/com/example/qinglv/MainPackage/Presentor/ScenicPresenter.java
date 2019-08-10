package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.Model.ScenicModel;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelPager;
import com.example.qinglv.MainPackage.inter.iApiMvp.BasePresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterPager;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewPreview;

import java.util.List;


/**
 * 风景预览的presenter类
 */
public class ScenicPresenter extends BasePresenter<IViewPreview<Scenic>> implements IPresenterPager {

    private IModelPager<Scenic> iModelPager;

    public ScenicPresenter(){
        iModelPager = new ScenicModel();
    }

    private boolean mIsRefresh = true;

    private IModelPager.CallBack<Scenic> callBack = new IModelPager.CallBack<Scenic>() {
        @Override
        public void onSucceed(List<Scenic> list, boolean isMore) {
            if (isAttached()) {
                getView().setList(list, isMore, mIsRefresh);
            }
        }

        @Override
        public void onError(String errorType) {
            if (isAttached()) {
                getView().setErrorToast(errorType);
            }
        }
    };


    @Override
    public void refreshRecycler(int firstNum, int size , final boolean isRefresh) {
        mIsRefresh = isRefresh;
        iModelPager.getData(firstNum, size, callBack);

    }

    @Override
    public void searchKry(String key, int firstNum, int size , boolean isRefresh) {
        mIsRefresh = isRefresh;
        iModelPager.getSearchData(key,firstNum, size, callBack);
    }
}