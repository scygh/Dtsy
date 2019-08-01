package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetListByPage;

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
    private GetListByPage.ContentBean.RowsBean rowsBean;

    public static Intent getPersonDetailActivityIntent(Context context, GetListByPage.ContentBean.RowsBean rowsBean) {
        Intent intent = new Intent(context, PersonDetailActivity.class);
        intent.putExtra(Constants.INTENT_ROWSBEAN_NAME, rowsBean.getName());
        intent.putExtra(Constants.INTENT_ROWSBEAN_IDCARD, rowsBean.getId());
        intent.putExtra(Constants.INTENT_ROWSBEAN_STUID, rowsBean.getDepartmentId());
        intent.putExtra(Constants.INTENT_ROWSBEAN_PHONE, rowsBean.getPhone());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        ButterKnife.bind(this);
        pageName.setText("人员详情");
        Intent dataIntent = getIntent();
        persondetailName.setText(dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_NAME));
        persondetailCardnum.setText(dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_IDCARD));
        persondetailStunum.setText(dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_STUID));
        persondetailTelephone.setText(dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_PHONE));
    }

    @OnClick({R.id.Manualconsumption_back, R.id.persondetail_camera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.persondetail_camera:
                // TODO: 2019/7/31 启动人脸识别录入一张照片
                startActivityForResult(FaceInputActivity.getFaceInputActivityIntent(PersonDetailActivity.this),Constants.FACE_INPUT_REQUESTCODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.FACE_INPUT_REQUESTCODE && resultCode == Constants.FACE_INPUT_RESULTCODE) {
            byte[] image = data.getByteArrayExtra(Constants.INTENT_FACEINPUT_RGBDATA);
            if (image.length>0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
                persondetailCamera.setImageBitmap(bitmap);
            }
        }
    }
}
