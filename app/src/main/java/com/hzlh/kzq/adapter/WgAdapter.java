package com.hzlh.kzq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hzlh.kzq.R;
import com.hzlh.kzq.data.model.WgDatas;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WgAdapter extends RecyclerView.Adapter<WgAdapter.WgViewHolder> {

    private Context mContext;
    private List<WgDatas> datas;
    private CallBack mCallBack;

    public WgAdapter(Context context, List<WgDatas> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public WgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wg_item, parent, false);
        return new WgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WgViewHolder holder, int position) {
        WgDatas wgData = datas.get(position);
        holder.tv_wg_name.setText("名称:" + wgData.wg_name);
        holder.tv_wg_ip.setText("网关IP:" + wgData.wg_ip);
        holder.tv_wg_wl_zwym.setText("子网掩码:" + wgData.wg_wl_zwym);
        holder.tv_wg_wl_wgip.setText("网关:" + wgData.wg_wl_wgip);
        holder.tv_wg_mac.setText("MAC地址:" + wgData.wg_mac);
        holder.tv_wg_swj_ip.setText("上位机IP:" + wgData.wg_swj_ip);
        holder.tv_wg_swj_port.setText("上位机端口:" + wgData.wg_swj_port);
        if (wgData.wg_status == 1) {
            holder.tv_status.setText("在线");
            holder.tv_status.setTextColor(mContext.getResources().getColor(R.color.green));
        } else {
            holder.tv_status.setText("离线");
            holder.tv_status.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        holder.setItem(wgData);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setDatas(List<WgDatas> wgDatasList) {
        datas = wgDatasList;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onClickWgItem(WgDatas wgDatas);

        void onClickWgSetingItem(WgDatas wgDatas);
    }

    public class WgViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_wg_name)
        TextView tv_wg_name;
        @BindView(R.id.tv_wg_ip)
        TextView tv_wg_ip;
        @BindView(R.id.tv_wg_mac)
        TextView tv_wg_mac;
        @BindView(R.id.tv_wg_swj_ip)
        TextView tv_wg_swj_ip;
        @BindView(R.id.tv_wg_wl_zwym)
        TextView tv_wg_wl_zwym;
        @BindView(R.id.tv_wg_swj_port)
        TextView tv_wg_swj_port;
        @BindView(R.id.tv_wg_wl_wgip)
        TextView tv_wg_wl_wgip;
        @BindView(R.id.tv_status)
        TextView tv_status;

        private WgDatas item;

        public WgViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(WgDatas wgData) {
            item = wgData;
        }

        @OnClick({R.id.wg_layout_1, R.id.tv_wg_swj_ip, R.id.tv_wg_wl_zwym, R.id.tv_wg_swj_port, R.id.tv_wg_wl_wgip, R.id.tv_wg_mac, R.id.tv_status})
        public void wg_layout_1() {
            mCallBack.onClickWgItem(item);
        }

        @OnClick(R.id.wg_seting)
        public void wg_seting() {
            mCallBack.onClickWgSetingItem(item);
        }
    }
}
