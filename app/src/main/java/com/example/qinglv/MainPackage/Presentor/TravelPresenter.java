package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.Model.TravelModel;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterPager;
import com.example.qinglv.MainPackage.View.iView.IViewPreview;
import java.util.List;
import static com.example.qinglv.MainPackage.util.NewRecyclerScrollListener.IS_SCROLL;

/**
 * 游记预览的presenter类
 */
public class TravelPresenter implements IPresenterPager {

    private IViewPreview<Travel> mIViewPreview;
    private IModelPager<Travel> iModelPager;

    public TravelPresenter(IViewPreview<Travel> iViewPreview){
        mIViewPreview = iViewPreview;
        iModelPager = new TravelModel();
    }

    @Override
    public void refreshRecycler(int firstNum, int size) {
        IS_SCROLL = false;
        IModelPager.CallBack<Travel> callBack = new IModelPager.CallBack<Travel>() {
            @Override
            public void onSucceed(List<Travel> list, boolean isMore) {
                mIViewPreview.setList(list,isMore);
            }

            @Override
            public void onError(String errorType) {
                mIViewPreview.setErrorToast(errorType);
            }
        };

        iModelPager.getData(firstNum, size, callBack);

    }

}