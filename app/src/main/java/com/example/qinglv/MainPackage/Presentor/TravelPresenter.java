package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.Model.TravelModel;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelPager;
import com.example.qinglv.MainPackage.inter.iApiMvp.BasePresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterPager;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewPreview;
import java.util.List;

/**
 * 游记预览的presenter类
 */
public class TravelPresenter extends BasePresenter<IViewPreview<Travel>> implements IPresenterPager {
    private IModelPager<Travel> iModelPager;

    public TravelPresenter(){
        iModelPager = new TravelModel();
    }

    private boolean mIsRefresh = true;

    private IModelPager.CallBack<Travel> callBack = new IModelPager.CallBack<Travel>() {
        @Override
        public void onSucceed(List<Travel> list, boolean isMore) {
            if (isAttached()) {
                getView().setList(list, isMore, mIsRefresh);

            }
        }

        @Override
        public void onError(String errorType , int recyclerFootType) {
            if (isAttached()) {
                getView().setErrorToast(errorType , recyclerFootType);
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