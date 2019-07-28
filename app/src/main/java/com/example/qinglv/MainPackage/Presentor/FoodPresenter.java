package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Adapter.FoodAdapter;
import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Model.FoodModel;
import com.example.qinglv.MainPackage.Model.iModel.IModelFood;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterFood;
import com.example.qinglv.MainPackage.View.iView.IViewFood;

import java.util.List;

import static com.example.qinglv.MainPackage.util.NewRecyclerScrollListener.IS_SCROLL;

/**
 * 美食预览的presenter类
 */
public class FoodPresenter implements IPresenterFood {

    private IViewFood mIViewFood;
    private IModelFood iModelFood;

    public FoodPresenter(IViewFood iViewFood){
        mIViewFood = iViewFood;
        iModelFood = new FoodModel();
    }

    @Override
    public void refreshRecycler(int firstNum, int size) {
        IS_SCROLL = false;
        iModelFood.getData(firstNum, size, new IModelFood.CallBack() {
            @Override
            public void onSucceed(List<Food> list , boolean isMore) {
                mIViewFood.setList(list,isMore);
            }

            @Override
            public void onError(String errorType) {
                mIViewFood.setErrorToast(errorType);
            }
        });
    }
}
