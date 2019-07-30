package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Model.FoodModel;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterPager;
import com.example.qinglv.MainPackage.View.iView.IViewPreview;

import java.util.List;

import static com.example.qinglv.MainPackage.util.NewRecyclerScrollListener.IS_SCROLL;

/**
 * 美食预览的presenter类
 */
public class FoodPresenter implements IPresenterPager {

    private IViewPreview<Food> mIViewPreview;
    private IModelPager<Food> iModelPager;

    public FoodPresenter(IViewPreview<Food> iViewPreview){
        mIViewPreview = iViewPreview;
        iModelPager = new FoodModel();
    }

    @Override
    public void refreshRecycler(int firstNum, int size , final boolean isRefresh) {
        IS_SCROLL = false;
        IModelPager.CallBack<Food> callBack = new IModelPager.CallBack<Food>() {
            @Override
            public void onSucceed(List<Food> list, boolean isMore) {
                mIViewPreview.setList(list,isMore,isRefresh);
            }

            @Override
            public void onError(String errorType) {
                mIViewPreview.setErrorToast(errorType);
            }
        };

        iModelPager.getData(firstNum, size, callBack);

    }

}
