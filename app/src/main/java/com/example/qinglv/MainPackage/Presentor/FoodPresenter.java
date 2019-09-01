package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Model.FoodModel;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelPager;
import com.example.qinglv.MainPackage.inter.iApiMvp.BasePresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterPager;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewPreview;

import java.util.List;


/**
 * 美食预览的presenter类
 */
public class FoodPresenter extends BasePresenter<IViewPreview<Food>> implements IPresenterPager  {

    private IModelPager<Food> iModelPager;

    private boolean mIsRefresh = true;

    private IModelPager.CallBack<Food> callBack = new IModelPager.CallBack<Food>() {
        @Override
        public void onSucceed(List<Food> list, boolean isMore) {
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

    public FoodPresenter(){
        iModelPager = new FoodModel();
    }

    @Override
    public void refreshRecycler(int firstNum, int size , final boolean isRefresh) {
        mIsRefresh = isRefresh;
        iModelPager.getData(firstNum, size, callBack);
    }

    @Override
    public void searchKry(String key, int firstNum, int size ,boolean isRefresh) {
        mIsRefresh = isRefresh;
        iModelPager.getSearchData(key,firstNum, size, callBack);
    }
}
