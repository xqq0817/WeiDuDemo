package com.example.weidudianshang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weidudianshang.R;
import com.example.weidudianshang.activity.PzshActivity;
import com.example.weidudianshang.entity.DataBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class PzshAdapter extends RecyclerView.Adapter<PzshAdapter.ViewHolder> {
    private Context context;
    private List<DataBean.ResultBean.PzshBean.CommodityListBeanX> pzshdata;

    public PzshAdapter(Context context, List<DataBean.ResultBean.PzshBean.CommodityListBeanX> pzshdata) {
        this.context = context;
        this.pzshdata = pzshdata;
    }

    @NonNull
    @Override
    public PzshAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.datathree_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PzshAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.simp.setImageURI(pzshdata.get(i).getMasterPic());
        viewHolder.tv_name.setText(pzshdata.get(i).getCommodityName());
        viewHolder.tv_price.setText("￥"+pzshdata.get(i).getPrice()+".00");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(pzshdata.get(i).getCommodityId());
                context.startActivity(new Intent(context,PzshActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pzshdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView simp;
        private TextView tv_name;
        private TextView tv_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.simp);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
