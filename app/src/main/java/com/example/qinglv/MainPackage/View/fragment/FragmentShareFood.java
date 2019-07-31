package com.example.qinglv.MainPackage.View.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qinglv.MainPackage.Adapter.FoodAdapter;
import com.example.qinglv.MainPackage.View.activity.SearchActivity;
import com.example.qinglv.util.RecyclerViewAdapterWrapper;
import com.example.qinglv.MainPackage.Entity.Food;
import com.example.qinglv.MainPackage.Presentor.FoodPresenter;
import com.example.qinglv.MainPackage.Presentor.iPresenter.IPresenterPager;
import com.example.qinglv.MainPackage.View.activity.FoodDetailActivity;
import com.example.qinglv.MainPackage.View.iView.IViewPreview;
import com.example.qinglv.MainPackage.View.iView.RecyclerClickCallback;
import com.example.qinglv.util.NewRecyclerScrollListener;
import com.example.qinglv.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.qinglv.util.NewRecyclerScrollListener.IS_SCROLL;

public class FragmentShareFood extends Fragment implements IViewPreview<Food> {
    private RecyclerViewAdapterWrapper adapterWrapper;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Food> mList = new ArrayList<>();
    private IPresenterPager iPresenterPager;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager,container,false);
        iPresenterPager = new FoodPresenter(this);//建立presenter的实例


        //RecyclerView的初始化以及相关配置
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_pager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置回调方法，点击子项直接跳转活动查看详情
        FoodAdapter foodAdapter = new FoodAdapter(mList, new RecyclerClickCallback() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), FoodDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("food",mList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick() {

            }
        });
        adapterWrapper = new RecyclerViewAdapterWrapper(foodAdapter);
        recyclerView.setAdapter(adapterWrapper);
        //给recyclerView设置下拉监听,方法具体在下面
        setRecyclerPullUpListener(recyclerView,adapterWrapper);

        //搜索框的软键盘回车键设置监听。打开另一个活动展示搜索结果
        EditText editText = view.findViewById(R.id.editText_pager);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("text",v.getText());
                startActivity(intent);
                return false;
            }
        });

        //下拉刷新控件的初始化以及设置监听
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout_pager);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iPresenterPager.refreshRecycler(1,10,true);
            }
        });


        //第一次进入直接刷新并展示小圆圈提示一下
        swipeRefreshLayout.setRefreshing(true);
        iPresenterPager.refreshRecycler(1,10,false);


        return view;
    }

    private void setRecyclerPullUpListener(RecyclerView recyclerView , final RecyclerViewAdapterWrapper adapterWrapper){
        recyclerView.addOnScrollListener(new NewRecyclerScrollListener() {
            @Override
            public void onLoadMore(int itemCount) {
                adapterWrapper.setItemState(RecyclerViewAdapterWrapper.LOADING,true);
                iPresenterPager.refreshRecycler(itemCount , 10,false);
            }
        });

    }


    //接口方法，用于访问数据后更改列表。第二个参数是判断还有没有更多，没有的话最后一项显示到底.IS_SCROLL是不然继续下滑的参数
    @Override
    public void setList(List<Food> list , boolean isMore,boolean isRefresh) {
        if (isRefresh) mList.clear();
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
