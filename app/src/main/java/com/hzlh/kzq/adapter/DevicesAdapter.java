package com.hzlh.kzq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hzlh.kzq.R;
import com.hzlh.kzq.data.model.DevicesData;

import java.util.List;

import butterknife.ButterKnife;

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

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setDatas(List<DevicesData> devicesDataList) {
        datas = devicesDataList;
//        notifyDataSetChanged();
    }

    public interface CallBack {
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {

        private DevicesData item;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(DevicesData device) {
            item = device;
        }


    }
}
