package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Entity.Scenic;
import com.example.qinglv.MainPackage.Model.PathModel;
import com.example.qinglv.MainPackage.Model.ScenicModel;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterPager;
import com.example.qinglv.MainPackage.View.iView.IViewPreview;

import java.util.List;

import static com.example.qinglv.MainPackage.util.NewRecyclerScrollListener.IS_SCROLL;

/**
 * 风景预览的presenter类
 */
public class ScenicPresenter implements IPresenterPager {

    private IViewPreview<Scenic> mIViewPreview;
    private IModelPager<Scenic> iModelPager;

    public ScenicPresenter(IViewPreview<Scenic> iViewPreview){
        mIViewPreview = iViewPreview;
        iModelPager = new ScenicModel();
    }

    @Override
    public void refreshRecycler(int firstNum, int size) {
        IS_SCROLL = false;
        IModelPager.CallBack<Scenic> callBack = new IModelPager.CallBack<Scenic>() {
            @Override
            public void onSucceed(List<Scenic> list, boolean isMore) {
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