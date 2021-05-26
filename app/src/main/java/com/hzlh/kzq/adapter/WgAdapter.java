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
        holder.tv_wg_serialNumber.setText((position + 1) + "");
        holder.tv_wg_ip.setText(wgData.wg_ip);
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
    }

    public class WgViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_wg_serialNumber)
        TextView tv_wg_serialNumber;
        @BindView(R.id.tv_wg_ip)
        TextView tv_wg_ip;
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

        @OnClick(R.id.tv_wg_ip)
        public void tv_wg_ip() {
            mCallBack.onClickWgItem(item);
        }

    }
}
