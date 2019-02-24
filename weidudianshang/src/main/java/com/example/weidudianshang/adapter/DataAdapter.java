package com.example.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weidudianshang.R;
import com.example.weidudianshang.entity.BannerBean;
import com.example.weidudianshang.entity.DataBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context context;
    private DataBean.ResultBean data;
    private  List<BannerBean.ResultBean> bannerdata;

    public DataAdapter(Context context) {
        this.context = context;
    }

    public void setData(DataBean.ResultBean result) {
        this.data = result;
        notifyDataSetChanged();
    }
    public void setbanner(List<BannerBean.ResultBean> result) {
        this.bannerdata = result;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==0){
            View view=LayoutInflater.from(context).inflate(R.layout.banner_item_layout,null);
            return new ViewHolder(view);
        }else if(i==1){
            View view=LayoutInflater.from(context).inflate(R.layout.rxxp_item_layout,null);
            return new ViewHolder(view);
        }else if(i==2){
            View view=LayoutInflater.from(context).inflate(R.layout.miss_item_layout,null);
            return new ViewHolder(view);
        }else if (i==3){
            View view=LayoutInflater.from(context).inflate(R.layout.pzsh_item_layout,null);
            return new ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder viewHolder, int i) {
        int i1 = getItemViewType(i);
        if (i1==0){
            ArrayList<String> bannerList = new ArrayList<>();
            for (int j = 0; j < bannerdata.size(); j++) {
                bannerList.add(bannerdata.get(j).getImageUrl());
            }
            viewHolder.bannert.setPages(bannerList, new MZHolderCreator<BannerViewHolder>() {
                @Override
                public BannerViewHolder createViewHolder() {
                    return new BannerViewHolder();
                }
            });
        }else if (i1==1){
            List<DataBean.ResultBean.RxxpBean.CommodityListBean> rxxpList = data.getRxxp().get(0).getCommodityList();
            RxxpAdapter rxxpAdapter = new RxxpAdapter(context, rxxpList);
            viewHolder.rxxprv.setAdapter(rxxpAdapter);
            viewHolder.rxxprv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        }else if(i1==2){
            List<DataBean.ResultBean.MlssBean.CommodityListBeanXX> missList = data.getMlss().get(0).getCommodityList();
            MissAdapter missAdapter = new MissAdapter(context, missList);
            viewHolder.missrv.setAdapter(missAdapter);
            viewHolder.missrv.setLayoutManager(new LinearLayoutManager(context));
        }else if (i1==3){
            List<DataBean.ResultBean.PzshBean.CommodityListBeanX> pzshList = data.getPzsh().get(0).getCommodityList();
            PzshAdapter pzshAdapter = new PzshAdapter(context, pzshList);
            viewHolder.pzshrv.setAdapter(pzshAdapter);
            viewHolder.pzshrv.setLayoutManager(new GridLayoutManager(context,2));
        }
    }

    @Override
    public int getItemViewType(int position) {
        int pos=0;
        if (position%4==1){
            pos=1;
        }else if(position%4==2){
            pos=2;
        }else if(position%4==3){
            pos=3;
        }
        return pos;
    }

    public class BannerViewHolder implements MZViewHolder<String> {
        private SimpleDraweeView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item_zi_layout,null);
            mImageView = view.findViewById(R.id.simp_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, String s) {
            mImageView.setImageURI(s);
        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView missrv;
        private RecyclerView pzshrv;
        private RecyclerView rxxprv;
        private MZBannerView bannert;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            missrv = itemView.findViewById(R.id.missrv);
            pzshrv = itemView.findViewById(R.id.pzshrv);
            rxxprv = itemView.findViewById(R.id.rxxprv);
            bannert = itemView.findViewById(R.id.bannert);
        }
    }
}
