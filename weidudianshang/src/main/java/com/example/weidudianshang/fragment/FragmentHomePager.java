package com.example.weidudianshang.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.weidudianshang.R;
import com.example.weidudianshang.adapter.DataAdapter;
import com.example.weidudianshang.entity.BannerBean;
import com.example.weidudianshang.entity.DataBean;
import com.example.weidudianshang.network.API;
import com.example.weidudianshang.presenter.LoginPresenter;
import com.example.weidudianshang.view.DataView;

public class FragmentHomePager<T> extends Fragment implements DataView<T> {
    private LinearLayout line1;
    private ImageView homeHeadCai;
    private EditText edSs;
    private ImageView btnSearch;
    private RecyclerView rv;
    private LoginPresenter loginPresenter;
    private DataAdapter dataAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenthome, container, false);
        //资源ID
        initView(view);
        loginPresenter = new LoginPresenter(this);
        loginPresenter.setData(API.BANNER_URL,null,4,null,null);
        dataAdapter = new DataAdapter(getActivity());
        return view;
    }

    @Override
    public void onsuccess(T data) {
         if (data instanceof BannerBean){
            BannerBean bannerBean = (BannerBean) data;
            dataAdapter.setbanner(bannerBean.getResult());
            loginPresenter.setData(API.SHANGP_URL,null,3,null,null);

        }else if (data instanceof DataBean){
            DataBean dataBean=(DataBean) data;
            dataAdapter.setData(dataBean.getResult());
            rv.setAdapter(dataAdapter);
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    public void ondefeated(T error) {

    }
    //资源ID
    private void initView(View view) {
        line1 = (LinearLayout) view.findViewById(R.id.line1);
        homeHeadCai = (ImageView) view.findViewById(R.id.home_head_cai);
        edSs = (EditText) view.findViewById(R.id.ed_ss);
        btnSearch = (ImageView) view.findViewById(R.id.btn_search);
        rv = (RecyclerView) view.findViewById(R.id.rv);
    }
}
