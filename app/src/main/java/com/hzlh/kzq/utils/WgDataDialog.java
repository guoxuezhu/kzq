package com.hzlh.kzq.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hzlh.kzq.R;
import com.hzlh.kzq.data.model.DevicesData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WgDataDialog extends Dialog {

    @BindView(R.id.tv_data)
    TextView tv_data;

    private Context mContext;
    private DevicesData devicesData;
    private DialogCallBack mDialogCallBack;

    public WgDataDialog(@NonNull Context context, DevicesData item, DialogCallBack dialogCallBack) {
        super(context, R.style.FullHeightDialog);
        mContext = context;
        devicesData = item;
        mDialogCallBack = dialogCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_dialog_view);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    private void initView() {
        if (devicesData != null) {
            tv_data.setText("电压：" + devicesData.value_2 + "\n" +
                    "电压：" + devicesData.value_3 + "\n" +
                    "电压：" + devicesData.value_4 + "\n" +
                    "电压：" + devicesData.value_5 + "\n" +
                    "电压：" + devicesData.value_6 + "\n" +
                    "电压：" + devicesData.value_7 + "\n" +
                    "电压：" + devicesData.value_8);
        }
    }

    public interface DialogCallBack {
        void dismissDialog();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancelDismissDialog();
    }

    private void cancelDismissDialog() {
        mDialogCallBack.dismissDialog();
    }


}
