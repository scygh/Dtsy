package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalSettingActivity extends BaseActivity {

    @BindView(R.id.page_name)
    TextView pageName;

    /**
     * descirption: 获取当前activity 的Intent对象
     */
    public static Intent getPersonalSettingActivityIntent(Context context) {
        Intent intent = new Intent(context, PersonalSettingActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_personal_setting;
    }

    @Override
    public void initView() {
        pageName.setText("设置");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.Manualconsumption_back, R.id.aps_rl1, R.id.aps_rl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.aps_rl1:
                startActivity(LoginActivity.getLoginActivityIntent(this));
                SPUtils.getInstance().put(Constants.ISLOGIN, false);
                finish();
                break;
            case R.id.aps_rl2:
                startActivity(LoginActivity.getLoginActivityIntent(this));
                SPUtils.getInstance().put(Constants.ISLOGIN, false);
                finish();
                break;
        }
    }
}
