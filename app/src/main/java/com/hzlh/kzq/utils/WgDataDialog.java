package com.hzlh.kzq.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.hzlh.kzq.R;
import com.hzlh.kzq.data.model.WgDatas;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WgDataDialog extends Dialog {

    @BindView(R.id.et_wg_name)
    EditText et_wg_name;
    @BindView(R.id.et_wg_ip)
    EditText et_wg_ip;
    @BindView(R.id.et_wg_zwym)
    EditText et_wg_zwym;
    @BindView(R.id.et_wg_wl_wg)
    EditText et_wg_wl_wg;
    @BindView(R.id.et_wg_mac)
    EditText et_wg_mac;
    @BindView(R.id.et_wg_swj_ip)
    EditText et_wg_swj_ip;
    @BindView(R.id.et_wg_swj_port)
    EditText et_wg_swj_port;

    private Context mContext;
    private WgDatas wgDatas;
    private DialogCallBack mDialogCallBack;

    public WgDataDialog(@NonNull Context context, WgDatas item, DialogCallBack dialogCallBack) {
        super(context, R.style.FullHeightDialog);
        mContext = context;
        wgDatas = item;
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
        if (wgDatas != null) {
            et_wg_name.setText(wgDatas.wg_name + "");
            et_wg_ip.setText(wgDatas.wg_ip + "");
            et_wg_zwym.setText(wgDatas.wg_wl_zwym + "");
            et_wg_wl_wg.setText(wgDatas.wg_wl_wgip + "");
            et_wg_mac.setText(wgDatas.wg_mac + "");
            et_wg_swj_ip.setText(wgDatas.wg_swj_ip + "");
            et_wg_swj_port.setText(wgDatas.wg_swj_port + "");
        }
    }

    public interface DialogCallBack {
        void dismissDialog();

        void fixWgInfoOkDialog(WgDatas wgDatas, String et_wg_name, String et_wg_ip, String et_wg_wl_zwym, String et_wg_wl_wgip, String et_wg_mac, String et_wg_swj_ip, String et_wg_swj_port);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancelDismissDialog();
    }

    private void cancelDismissDialog() {
        mDialogCallBack.dismissDialog();
    }

    @OnClick(R.id.wgfix_dialog_btn_no)
    public void wgfix_dialog_btn_no() {
        cancelDismissDialog();
    }

    @OnClick(R.id.wgfix_dialog_btn_ok)
    public void wgfix_dialog_btn_ok() {
        mDialogCallBack.fixWgInfoOkDialog(wgDatas,
                et_wg_name.getText().toString(),
                et_wg_ip.getText().toString(),
                et_wg_zwym.getText().toString(),
                et_wg_wl_wg.getText().toString(),
                et_wg_mac.getText().toString(),
                et_wg_swj_ip.getText().toString(),
                et_wg_swj_port.getText().toString());
    }
}
