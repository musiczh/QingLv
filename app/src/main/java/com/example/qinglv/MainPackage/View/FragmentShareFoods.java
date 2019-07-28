package com.example.qinglv.MainPackage.View;

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

import com.example.qinglv.MainPackage.Adapter.FoodAdapter;
import com.example.qinglv.MainPackage.Adapter.RecyclerViewAdapterWrapper;
import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Presentor.FoodPresenter;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterFood;
import com.example.qinglv.MainPackage.View.iView.IViewFood;
import com.example.qinglv.MainPackage.util.NewRecyclerScrollListener;
import com.example.qinglv.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.qinglv.MainPackage.util.NewRecyclerScrollListener.IS_SCROLL;

public class FragmentShareFoods extends Fragment implements IViewFood {
    private RecyclerView recyclerView;
    private RecyclerView.OnScrollListener scrollListener;
    private RecyclerViewAdapterWrapper adapterWrapper;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Food> mList = new ArrayList<>();
    private IPresenterFood iPresenterFood;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager,container,false);
        iPresenterFood = new FoodPresenter(this);//建立presenter的实例

        //RecyclerView的初始化以及设置适配器
        recyclerView = view.findViewById(R.id.recyclerView_pager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        FoodAdapter foodAdapter = new FoodAdapter(mList);
        adapterWrapper = new RecyclerViewAdapterWrapper(foodAdapter);
        recyclerView.setAdapter(adapterWrapper);

        //给recyclerView设置下拉监听,方法具体在下面
        setRecyclerListener(recyclerView,adapterWrapper);


        //下拉刷新控件的初始化以及设置监听
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout_pager);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iPresenterFood.refreshRecycler(1,10);
            }
        });


        //第一次进入直接刷新并展示小圆圈提示一下
        swipeRefreshLayout.setRefreshing(true);
        iPresenterFood.refreshRecycler(1,10);


        return view;
    }

    private void setRecyclerListener(RecyclerView recyclerView , final RecyclerViewAdapterWrapper adapterWrapper){
        recyclerView.addOnScrollListener(new NewRecyclerScrollListener() {
            @Override
            public void onLoadMore(int itemCount) {
                adapterWrapper.setItemState(RecyclerViewAdapterWrapper.LOADING,true);
                iPresenterFood.refreshRecycler(itemCount , 10);
            }
        });
    }


    //接口方法，用于访问数据后更改列表。第二个参数是判断还有没有更多，没有的话最后一项显示到底.IS_SCROLL是不然继续下滑的参数
    @Override
    public void setList(List<Food> list , boolean isMore) {
        mList.addAll(list);
        if (isMore) {
            IS_SCROLL = true;
            adapterWrapper.setItemState(RecyclerViewAdapterWrapper.CONTINUE_DRAG, false);
        }else{
            IS_SCROLL = false;
            adapterWrapper.setItemState(RecyclerViewAdapterWrapper.NO_MORE,false);
        }
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    //出现异常的处理，显示一个Toast
    @Override
    public void setErrorToast(String string) {
        IS_SCROLL = true;
        Toast.makeText(getContext(),string,Toast.LENGTH_SHORT).show();
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        adapterWrapper.setItemState(RecyclerViewAdapterWrapper.CONTINUE_DRAG,true);
    }
}
