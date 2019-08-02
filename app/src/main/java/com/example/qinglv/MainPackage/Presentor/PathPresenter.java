package com.example.qinglv.MainPackage.Presentor;

import com.example.qinglv.MainPackage.Entity.Path;
import com.example.qinglv.MainPackage.Model.PathModel;
import com.example.qinglv.MainPackage.Model.iModel.IModelPager;
import com.example.qinglv.MainPackage.Presentor.iPresenter.BasePresenter;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterPager;
import com.example.qinglv.MainPackage.View.iView.IViewPreview;
import java.util.List;
import static com.example.qinglv.util.NewRecyclerScrollListener.IS_SCROLL;

/**
 * 路线预览的presenter类
 */
public class PathPresenter extends BasePresenter<IViewPreview<Path>> implements IPresenterPager {
    private IModelPager<Path> iModelPager;

    public PathPresenter(){
        iModelPager = new PathModel();
    }

    @Override
    public void refreshRecycler(int firstNum, int size , final boolean isRefresh) {
        IS_SCROLL = false;
        IModelPager.CallBack<Path> callBack = new IModelPager.CallBack<Path>() {
            @Override
            public void onSucceed(List<Path> list, boolean isMore) {
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