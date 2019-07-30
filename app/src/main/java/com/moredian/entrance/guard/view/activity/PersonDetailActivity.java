package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moredian.entrance.guard.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonDetailActivity extends AppCompatActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.persondetail_name)
    EditText persondetailName;
    @BindView(R.id.persondetail_cardnum)
    EditText persondetailCardnum;
    @BindView(R.id.persondetail_stunum)
    EditText persondetailStunum;
    @BindView(R.id.persondetail_telephone)
    EditText persondetailTelephone;
    @BindView(R.id.persondetail_camera)
    ImageView persondetailCamera;

    public static Intent getPersonDetailActivityIntent(Context context) {
        Intent intent = new Intent(context, PersonDetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        ButterKnife.bind(this);
        pageName.setText("人员详情");
    }

    @OnClick({R.id.Manualconsumption_back, R.id.persondetail_camera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.persondetail_camera:
                break;
        }
    }
}
