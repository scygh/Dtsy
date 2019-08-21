package com.moredian.entrance.guard.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.moredian.entrance.guard.R;

public class VoucherCenterActivity extends BaseActivity {

    public static Intent getVoucherCenterActivityIntent(Context context) {
        Intent intent = new Intent(context, VoucherCenterActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_voucher_center;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
