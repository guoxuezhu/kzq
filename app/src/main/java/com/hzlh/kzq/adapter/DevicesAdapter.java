package com.hzlh.kzq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hzlh.kzq.R;
import com.hzlh.kzq.data.model.DevicesData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder> {

    private Context mContext;
    private List<DevicesData> datas;
    private CallBack mCallBack;

    public DevicesAdapter(Context context, List<DevicesData> data, CallBack callBack) {
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
        DevicesData devicesData = datas.get(position);
        holder.tv_device_serialNumber.setText((position + 1) + "");
        holder.tv_device_id.setText(devicesData.device_id + "");
        if (devicesData.device_type.equals("1")) {
            holder.tv_device_type.setText("控制面板");
        } else if (devicesData.device_type.equals("2")) {
            holder.tv_device_type.setText("控制器");
        } else if (devicesData.device_type.equals("3")) {
            holder.tv_device_type.setText("串口透传");
        } else {
            holder.tv_device_type.setText("离线设备");
        }
//        if (devicesData.device_status.equals("1")) {
//            holder.tv_device_status.setText("在线");
//            holder.tv_device_status.setTextColor(mContext.getResources().getColor(R.color.green));
//        } else {
//            holder.tv_device_status.setText("离线");
//            holder.tv_device_status.setTextColor(mContext.getResources().getColor(R.color.red));
//        }
        holder.setItem(devicesData);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setDatas(List<DevicesData> devicesDataList) {
        datas = devicesDataList;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onClickDeviceItem(DevicesData devicesData);
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_device_serialNumber)
        TextView tv_device_serialNumber;
        @BindView(R.id.tv_device_id)
        TextView tv_device_id;
        @BindView(R.id.tv_device_type)
        TextView tv_device_type;

        private DevicesData item;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(DevicesData device) {
            item = device;
        }

        @OnClick(R.id.tv_device_type)
        public void tv_device_type() {
            mCallBack.onClickDeviceItem(item);
        }

    }
}
