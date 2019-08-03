package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Model.FoodModel;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.Presentor.iPresenter.BasePresenter;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterPager;
import com.example.qinglv.MainPackage.View.iView.IViewPreview;

import java.util.List;

import static com.example.qinglv.util.NewRecyclerScrollListener.IS_SCROLL;

/**
 * 美食预览的presenter类
 */
public class FoodPresenter extends BasePresenter<IViewPreview<Food>> implements IPresenterPager  {

    private IModelPager<Food> iModelPager;

    public FoodPresenter(){
        iModelPager = new FoodModel();
    }

    @Override
    public void refreshRecycler(int firstNum, int size , final boolean isRefresh) {
        IS_SCROLL = false;
        IModelPager.CallBack<Food> callBack = new IModelPager.CallBack<Food>() {
            @Override
            public void onSucceed(List<Food> list, boolean isMore) {
                if (isAttached()) {
                    getView().setList(list, isMore, isRefresh);
                }
            }

            @Override
            public void onError(String errorType) {
                if (isAttached()) {
                    getView().setErrorToast(errorType);
                }
            }
        };
        iModelPager.getData(firstNum, size, callBack);
    }

    @Override
    public void searchKry(String key, int firstNum, int size) {
        IS_SCROLL = false;
        IModelPager.CallBack<Food> callBack = new IModelPager.CallBack<Food>() {
            @Override
            public void onSucceed(List<Food> list, boolean isMore) {
                if (isAttached()) {
                    getView().setList(list, isMore, false);
                }
            }

            @Override
            public void onError(String errorType) {
                if (isAttached()) {
                    getView().setErrorToast(errorType);
                }
            }
        };
        iModelPager.getSearchData(key,firstNum, size, callBack);
    }
}
