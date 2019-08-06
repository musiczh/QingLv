package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Model.PathModel;
import com.example.qinglv.MainPackage.inter.iApiMvp.IModelPager;
import com.example.qinglv.MainPackage.inter.iApiMvp.BasePresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterPager;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewPreview;
import java.util.List;

/**
 * 路线预览的presenter类
 */
public class PathPresenter extends BasePresenter<IViewPreview<Path>> implements IPresenterPager {
    private IModelPager<Path> iModelPager;

    public PathPresenter(){
        iModelPager = new PathModel();
    }

    private boolean mIsRefresh = true;

    private IModelPager.CallBack<Path> callBack = new IModelPager.CallBack<Path>() {
        @Override
        public void onSucceed(List<Path> list, boolean isMore) {
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
    public void searchKry(String key, int firstNum, int size ,boolean isRefresh) {
        mIsRefresh = isRefresh;
        iModelPager.getSearchData(key,firstNum, size, callBack);
    }
}