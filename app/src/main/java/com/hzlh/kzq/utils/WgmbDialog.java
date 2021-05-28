package com.hzlh.kzq.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.hzlh.kzq.R;
import com.hzlh.kzq.data.model.DevicesData;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WgmbDialog extends Dialog {


    private Context mContext;
    private DevicesData devicesData;
    private DialogCallBack mDialogCallBack;

    public WgmbDialog(@NonNull Context context, DevicesData item, DialogCallBack dialogCallBack) {
        super(context, R.style.FullHeightDialog);
        mContext = context;
        devicesData = item;
        mDialogCallBack = dialogCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wgmb_dialog_view);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    private void initView() {
        if (devicesData != null) {

        }
    }

    public interface DialogCallBack {
        void wgmbDismissDialog();

        void wgmbOnClick(int position, int status);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancelDismissDialog();
    }

    private void cancelDismissDialog() {
        mDialogCallBack.wgmbDismissDialog();
    }

    @OnClick(R.id.wg_btn_cl)
    public void wg_btn_cl() {
        mDialogCallBack.wgmbOnClick(1, 1);
    }

}
