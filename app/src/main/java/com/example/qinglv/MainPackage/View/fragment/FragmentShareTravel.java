package com.example.qinglv.MainPackage.View.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qinglv.MainActivity;
import com.example.qinglv.MainPackage.View.activity.SearchActivity;
import com.example.qinglv.util.RecyclerViewAdapterWrapper;
import com.example.qinglv.MainPackage.Adapter.TravelAdapter;
import com.example.qinglv.MainPackage.Entity.Travel;
import com.example.qinglv.MainPackage.Presentor.TravelPresenter;
import com.example.qinglv.MainPackage.inter.iApiMvp.IPresenterPager;
import com.example.qinglv.MainPackage.View.activity.TravelDetailActivity;
import com.example.qinglv.MainPackage.inter.iApiMvp.IViewPreview;
import com.example.qinglv.MainPackage.inter.iApiUtil.RecyclerClickCallback;
import com.example.qinglv.util.NewRecyclerScrollListener;
import com.example.qinglv.R;
import java.util.ArrayList;
import java.util.List;

import static com.example.qinglv.util.StaticQuality.PREFIX_IMAGE;

/**
 * 游记预览展示那一页的碎片
 */
public class FragmentShareTravel extends Fragment implements IViewPreview<Travel> {
    private RecyclerViewAdapterWrapper adapterWrapper;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Travel> mList = new ArrayList<>();
    private IPresenterPager iPresenterPager;
    private String query;
    private NewRecyclerScrollListener newRecyclerScrollListener;




    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager,container,false);
        iPresenterPager = new TravelPresenter();//建立presenter的实例
        ((TravelPresenter) iPresenterPager).attachView(this);//建立与presenter的关联

        //RecyclerView的初始化以及设置适配器，设置子项点击监听
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_pager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        TravelAdapter travelAdapter = new TravelAdapter(mList, new RecyclerClickCallback() {
            //recyclerView子项点击监听
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), TravelDetailActivity.class);
                Travel travel = mList.get(position);

                String url;
                if (travel.getHeadPortrait().length()<10) {
                    url = PREFIX_IMAGE+travel.getHeadPortrait();
                }else{
                    url = travel.getHeadPortrait();
                }

                intent.putExtra("nickName",travel.getNickName());
                intent.putExtra("headPortrait",url);
                intent.putExtra("tittle",travel.getTitle());
                intent.putExtra("id",travel.getId());
                startActivity(intent);
            }

            @Override
            public void onLongClick() {
            }
        });
        adapterWrapper = new RecyclerViewAdapterWrapper(travelAdapter);
        recyclerView.setAdapter(adapterWrapper);

        //给recyclerView设置下拉监听,方法具体在下面
        newRecyclerScrollListener = new NewRecyclerScrollListener() {
            @Override
            public void onLoadMore(int itemCount) {
                //加载的过程中不能继续上拉
                newRecyclerScrollListener.IS_SCROLL = false;

                adapterWrapper.setItemState(RecyclerViewAdapterWrapper.LOADING,true);
                //判断是在搜索列表还是预览展示列表
                if (getActivity()instanceof MainActivity)
                    iPresenterPager.refreshRecycler(itemCount , 10,false);
                else iPresenterPager.searchKry(query , itemCount,10,false);
            }
        };
        recyclerView.addOnScrollListener(newRecyclerScrollListener);


        //下拉刷新控件的初始化以及设置监听
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout_pager);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iPresenterPager.refreshRecycler(0,10,true);
            }
        });


        //第一次进入直接刷新并展示小圆圈提示一下
        swipeRefreshLayout.setRefreshing(true);
        if (!(getActivity() instanceof SearchActivity)) {
            iPresenterPager.refreshRecycler(0, 10, false);
        }else {
            Bundle bundle =  getArguments();
            if (bundle != null){
                query = bundle.getString("query");
            }
            setQuery(query);
        }

        return view;
    }





    //接口方法，用于访问数据后更改列表。第二个参数是判断还有没有更多，没有的话最后一项显示到底.IS_SCROLL是不然继续下滑的参数
    @Override
    public void setList(List<Travel> list , boolean isMore ,boolean isRefresh) {
        if (isRefresh) mList.clear();
        mList.addAll(list);
        if (isMore) {
            newRecyclerScrollListener.IS_SCROLL = true;
            adapterWrapper.setItemState(RecyclerViewAdapterWrapper.CONTINUE_DRAG, false);
        }else{
            newRecyclerScrollListener.IS_SCROLL = false;
            adapterWrapper.setItemState(RecyclerViewAdapterWrapper.NO_MORE,false);
        }
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    //出现异常的处理，显示一个Toast
    @Override
    public void setErrorToast(String string , int footType) {
        newRecyclerScrollListener.IS_SCROLL = true;
        Toast.makeText(getContext(),string,Toast.LENGTH_SHORT).show();
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        switch (footType){
            case RecyclerViewAdapterWrapper.NO_MORE :
                adapterWrapper.setItemState(RecyclerViewAdapterWrapper.NO_MORE,true);
                newRecyclerScrollListener.IS_SCROLL = false;
                break;
            case RecyclerViewAdapterWrapper.NO_INTERNET:
                adapterWrapper.setItemState(RecyclerViewAdapterWrapper.NO_INTERNET,true);
                break;
            case RecyclerViewAdapterWrapper.NO_ARTICLE:
                adapterWrapper.setItemState(RecyclerViewAdapterWrapper.NO_ARTICLE,true);
                break;
        }

    }

    @Override
    public void setQuery(String string) {
        query = string;
        iPresenterPager.searchKry(query , 0,10,true);
    }

    //销毁view时同时解除引用
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((TravelPresenter) iPresenterPager).detachView();
    }
}


