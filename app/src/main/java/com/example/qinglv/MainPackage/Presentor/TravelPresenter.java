package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.Model.TravelModel;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.Presentor.iPresenter.BasePresenter;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterPager;
import com.example.qinglv.MainPackage.View.iView.IViewPreview;
import java.util.List;
import static com.example.qinglv.util.NewRecyclerScrollListener.IS_SCROLL;

/**
 * 游记预览的presenter类
 */
public class TravelPresenter extends BasePresenter<IViewPreview<Travel>> implements IPresenterPager {
    private IModelPager<Travel> iModelPager;

    public TravelPresenter(){
        iModelPager = new TravelModel();
    }

    @Override
    public void refreshRecycler(int firstNum, int size , final boolean isRefresh) {
        IS_SCROLL = false;
        IModelPager.CallBack<Travel> callBack = new IModelPager.CallBack<Travel>() {
            @Override
            public void onSucceed(List<Travel> list, boolean isMore) {
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

}