package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.Model.ScenicModel;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.Presentor.iPresenter.BasePresenter;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterPager;
import com.example.qinglv.MainPackage.View.iView.IViewPreview;

import java.util.List;

import static com.example.qinglv.util.NewRecyclerScrollListener.IS_SCROLL;

/**
 * 风景预览的presenter类
 */
public class ScenicPresenter extends BasePresenter<IViewPreview<Scenic>> implements IPresenterPager {

    private IModelPager<Scenic> iModelPager;

    public ScenicPresenter(){
        iModelPager = new ScenicModel();
    }

    @Override
    public void refreshRecycler(int firstNum, int size , final boolean isRefresh) {
        IS_SCROLL = false;
        IModelPager.CallBack<Scenic> callBack = new IModelPager.CallBack<Scenic>() {
            @Override
            public void onSucceed(List<Scenic> list, boolean isMore) {
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
        IModelPager.CallBack<Scenic> callBack = new IModelPager.CallBack<Scenic>() {
            @Override
            public void onSucceed(List<Scenic> list, boolean isMore) {
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