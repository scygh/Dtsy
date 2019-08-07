package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.entity.PostRequestBody;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.Base64BitmapUtil;

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
    @BindView(R.id.persondetail_sure)
    Button persondetailSure;
    @BindView(R.id.persondetail_cancle)
    Button persondetailCancle;
    private GetListByPage.ContentBean.RowsBean rowsBean;
    private Bitmap bitmap;
    private Api api;
    private Intent dataIntent;

    public static Intent getPersonDetailActivityIntent(Context context, GetListByPage.ContentBean.RowsBean rowsBean) {
        Intent intent = new Intent(context, PersonDetailActivity.class);
        intent.putExtra(Constants.INTENT_ROWSBEAN_NAME, rowsBean.getName());
        intent.putExtra(Constants.INTENT_ROWSBEAN_IDCARD, rowsBean.getId());
        intent.putExtra(Constants.INTENT_ROWSBEAN_STUID, rowsBean.getDepartmentId());
        intent.putExtra(Constants.INTENT_ROWSBEAN_PHONE, rowsBean.getPhone());
        intent.putExtra(Constants.INTENT_ROWSBEAN_ID, rowsBean.getId());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        ButterKnife.bind(this);
        pageName.setText("人员详情");
        api = new Api();
        dataIntent = getIntent();
        persondetailName.setText(dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_NAME));
        persondetailCardnum.setText(dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_IDCARD));
        persondetailStunum.setText(dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_STUID));
        persondetailTelephone.setText(dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_PHONE));
        api.setCreateResponse(new Api.CreateResponse() {
            @Override
            public void onCreate() {
                if (bitmap != null) {
                    updatePerson();
                } else {
                    ToastUtils.showShort("图片为空");
                }
            }
        });
    }

    @OnClick({R.id.Manualconsumption_back, R.id.persondetail_camera, R.id.persondetail_sure, R.id.persondetail_cancle, R.id.personDetail_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.persondetail_camera:
                // TODO: 2019/7/31 启动人脸识别录入一张照片
                startActivityForResult(FaceInputActivity.getFaceInputActivityIntent(PersonDetailActivity.this), Constants.FACE_INPUT_REQUESTCODE);
                break;
            case R.id.persondetail_sure:
                // TODO: 2019/8/1 点击create  update
                if (bitmap != null) {
                    createPerson();
                } else {
                    ToastUtils.showShort("请先录脸");
                }
                break;
            case R.id.persondetail_cancle:
                finish();
                break;
            case R.id.personDetail_delete:
                PostRequestBody postRequestBody = new PostRequestBody(dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_ID));
                api.postDelete(postRequestBody, SPUtils.getInstance().getString(Constants.ACCESSTOKEN), "123");
                break;
        }
    }

    private synchronized void createPerson() {
        PostRequestBody postRequestBody = new PostRequestBody(dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_ID), dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_NAME),
                dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_PHONE));
        api.postCreate(postRequestBody, SPUtils.getInstance().getString(Constants.ACCESSTOKEN), "123");
    }

    private synchronized void updatePerson() {
        String b = Base64BitmapUtil.bitmapToBase64(bitmap, PersonDetailActivity.this);
        PostRequestBody postRequestBody = new PostRequestBody(dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_ID),b);
        api.postUpdate(postRequestBody, SPUtils.getInstance().getString(Constants.ACCESSTOKEN), "123");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.FACE_INPUT_REQUESTCODE && resultCode == Constants.FACE_INPUT_RESULTCODE) {
            byte[] image = data.getByteArrayExtra(Constants.INTENT_FACEINPUT_RGBDATA);
            if (image != null && image.length > 0) {
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                persondetailCamera.setImageBitmap(bitmap);
            }
        }
    }

}
