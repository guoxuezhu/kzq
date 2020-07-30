package com.hzlh.kzq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hzlh.kzq.R;
import com.hzlh.kzq.data.DeviceInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    private Context mContext;
    private List<DeviceInfo> datas;
    private CallBack mCallBack;

    public DeviceAdapter(Context context, List<DeviceInfo> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_item, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        DeviceInfo deviceInfo = datas.get(position);
        holder.tv_number.setText(position + 1 + "");
        holder.tv_mac.setText("MAC地址:" + deviceInfo.getMAC());
        holder.tv_ip.setText("IP地址:" + deviceInfo.getAddressIP());
        holder.setItem(deviceInfo);
    }





    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setData(List<DeviceInfo> deviceInfos) {
        datas = deviceInfos;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onClickItemWlan(DeviceInfo item);
        void onClickItemTcp(DeviceInfo item);
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_number)
        TextView tv_number;
        @BindView(R.id.tv_mac)
        TextView tv_mac;
        @BindView(R.id.tv_ip)
        TextView tv_ip;


        private DeviceInfo item;


        public DeviceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setItem(DeviceInfo deviceInfo) {
            item = deviceInfo;
        }

        @OnClick(R.id.btn_wlan)
        public void btn_wlan() {
            mCallBack.onClickItemWlan(item);
        }

        @OnClick(R.id.btn_tcp)
        public void btn_tcp() {
            mCallBack.onClickItemTcp(item);
        }

    }
}
