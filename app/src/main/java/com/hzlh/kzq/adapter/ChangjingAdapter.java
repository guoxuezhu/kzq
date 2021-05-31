package com.hzlh.kzq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hzlh.kzq.R;
import com.hzlh.kzq.data.model.ChangjingDatas;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangjingAdapter extends RecyclerView.Adapter<ChangjingAdapter.ChangjignViewHolder> {

    private Context mContext;
    private List<ChangjingDatas> datas;
    private CallBack mCallBack;

    public ChangjingAdapter(Context context, List<ChangjingDatas> data, CallBack callBack) {
        this.datas = data;
        this.mContext = context;
        this.mCallBack = callBack;
    }

    @NonNull
    @Override
    public ChangjignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chang_jing_item, parent, false);
        return new ChangjignViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChangjignViewHolder holder, int position) {
        ChangjingDatas changjingData = datas.get(position);
        holder.tv_cj_serialNumber.setText((position + 1) + "");
        holder.tv_cj_id.setText(changjingData.cj_id + "");
        holder.setItem(changjingData);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setDatas(List<ChangjingDatas> changjingDatasList) {
        datas = changjingDatasList;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onClickChangjingItem(ChangjingDatas changjingData);
    }

    public class ChangjignViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_cj_serialNumber)
        TextView tv_cj_serialNumber;
        @BindView(R.id.tv_cj_id)
        TextView tv_cj_id;


        private ChangjingDatas item;

        public ChangjignViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(ChangjingDatas changjingDatas) {
            item = changjingDatas;
        }

        @OnClick(R.id.cj_dy_btn)
        public void cj_dy_btn() {
            mCallBack.onClickChangjingItem(item);
        }

    }
}
