package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.entity.PostRequestBody;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.Base64BitmapUtil;
import com.moredian.entrance.guard.utils.DrawableUtils;
import com.moredian.entrance.guard.utils.ToastHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.samlss.broccoli.Broccoli;
import me.samlss.broccoli.PlaceholderParameter;

public class PersonDetailActivity extends BaseActivity {

    private static final String TAG = "PersonDetailActivity";
    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.persondetail_name)
    TextView persondetailName;
    @BindView(R.id.persondetail_cardnum)
    TextView persondetailCardnum;
    @BindView(R.id.persondetail_stunum)
    TextView persondetailStunum;
    @BindView(R.id.persondetail_telephone)
    TextView persondetailTelephone;
    @BindView(R.id.persondetail_camera)
    ImageView persondetailCamera;
    @BindView(R.id.personDetail_create)
    TextView personDetailCreate;
    @BindView(R.id.personDetail_update)
    TextView personDetailUpdate;
    @BindView(R.id.personDetail_delete)
    TextView personDetailDelete;

    private Bitmap bitmap;
    private Intent dataIntent;
    //判断返回的memberID是否和当前一致
    private boolean istheSameFace = false;
    //当前的memberID
    private String exitmemberId;
    //获取的page
    List<GetListByPage.ContentBean.RowsBean> arowsBeans = new ArrayList<>();
    //数据定位
    int abposition;
    //查询传入
    GetListByPage.ContentBean.RowsBean findbean;
    String userid;
    String memberId;
    private Broccoli mBroccoli;

    /**
     * descirption: 得到列表界面需要intent
     */
    public static Intent getPersonDetailActivityIntent(Context context, int position) {
        Intent intent = new Intent(context, PersonDetailActivity.class);
        intent.putExtra(Constants.INTENT_ROWSBEAN_POSITION, position);
        return intent;
    }

    /**
     * descirption: 得到查询界面需要的intent
     */
    public static Intent getPersonDetailActivityIntent(Context context, String userid) {
        Intent intent = new Intent(context, PersonDetailActivity.class);
        intent.putExtra(Constants.INTENT_ROWSBEAN_BEAN, userid);
        return intent;
    }

    private void initPlaceholders() {
        int placeholderColor = Color.parseColor("#DDDDDD");
        mBroccoli = new Broccoli();
        mBroccoli.addPlaceholders(
                new PlaceholderParameter.Builder()
                        .setView(persondetailName)
                        .setDrawable(DrawableUtils.createRectangleDrawable(placeholderColor, 2))
                        .build(),
                new PlaceholderParameter.Builder()
                        .setView(persondetailCardnum)
                        .setDrawable(DrawableUtils.createRectangleDrawable(placeholderColor, 2))
                        .build(),
                new PlaceholderParameter.Builder()
                        .setView(persondetailStunum)
                        .setDrawable(DrawableUtils.createRectangleDrawable(placeholderColor, 2))
                        .build(),
                new PlaceholderParameter.Builder()
                        .setView(persondetailTelephone)
                        .setDrawable(DrawableUtils.createRectangleDrawable(placeholderColor, 2))
                        .build()
        );
        mBroccoli.show();
    }

    @Override
    public int layoutView() {
        return R.layout.activity_person_detail_2;
    }

    @Override
    public void initView() {
        pageName.setText("人员详情");
        initPlaceholders();
    }

    @Override
    public void initData() {
        dataIntent = getIntent();
        initRequest();
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                //如果是删除成功了，就清空ExitMemberId
                exitmemberId = null;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        persondetailCamera.setImageResource(R.mipmap.camera);
                    }
                });
            }

            @Override
            public void onFail(String err) {

            }
        });
        api.setOnCreate(new Api.OnCreate() {
            @Override
            public void created() {
                updateCheck();
            }
        });
    }

    /**
     * descirption: 请求数据初始化
     */
    private void initRequest() {
        userid = dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_BEAN);
        if (!TextUtils.isEmpty(userid)) {
            api.getListByPage(1, 5000);
            api.setOnResponse(new Api.OnResponse<GetListByPage.ContentBean.RowsBean>() {
                @Override
                public void onResponse(List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
                    arowsBeans.clear();
                    arowsBeans.addAll(rowsBeans);
                    for (GetListByPage.ContentBean.RowsBean bean : rowsBeans) {
                        if (bean.getUser().getId().equals(userid)) {
                            findbean = bean;
                            break;
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setData(findbean);
                        }
                    });
                }

                @Override
                public void onResponseMore(List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
                }

                @Override
                public void onFailed() {
                }
            });
        } else {
            ToastUtils.showShort("userid 为空");
        }
    }

    /**
     * descirption: 设置头像
     */
    private void GlideIn() {
        RoundedCorners roundedCorners = new RoundedCorners(30);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(this).load(bitmap).apply(options).into(persondetailCamera);
    }

    /**
     * descirption: 查询后设置数据
     */
    private void setData(GetListByPage.ContentBean.RowsBean findbean) {
        mBroccoli.removeAllPlaceholders();
        persondetailName.setText(findbean.getUser().getName());
        persondetailCardnum.setText(findbean.getUser().getId());
        persondetailStunum.setText(findbean.getUser().getCreateTime());
        persondetailTelephone.setText(findbean.getUser().getPhone());
        if (findbean.getUserFace() != null) {
            exitmemberId = findbean.getUserFace().getMemberId();
            if (!TextUtils.isEmpty(findbean.getUserFace().getMemberBase64())) {
                bitmap = Base64BitmapUtil.base64ToBitmap(findbean.getUserFace().getMemberBase64());
                GlideIn();
            }
        }
    }

    @OnClick({R.id.Manualconsumption_back, R.id.persondetail_camera, R.id.personDetail_create, R.id.personDetail_update, R.id.personDetail_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.persondetail_camera:
                break;
            case R.id.personDetail_create:
                startActivityForResult(FaceInputActivity.getFaceInputActivityIntent(PersonDetailActivity.this), Constants.FACE_INPUT_REQUESTCODE);
                break;
            case R.id.personDetail_update:
                break;
            case R.id.personDetail_delete:
                deletePerson();
        }
    }

    /**
     * descirption: 删除创建
     */
    private void deletePerson() {
        if (!TextUtils.isEmpty(userid)) {
            if (findbean != null) {
                PostRequestBody postRequestBody = new PostRequestBody(findbean.getUser().getId());
                api.postDelete(postRequestBody, token, Constants.MODIAN_TOKEN);
            }
        } else {
            ToastUtils.showShort("createPerson userid 为空");
        }
    }

    /**
     * descirption: 创建人员
     */
    private synchronized void createPerson() {
        if (!TextUtils.isEmpty(userid)) {
            PostRequestBody postRequestBody = new PostRequestBody(findbean.getUser().getId(), findbean.getUser().getName(),
                    findbean.getUser().getPhone());
            api.postCreate(postRequestBody, SPUtils.getInstance().getString(Constants.ACCESSTOKEN), Constants.MODIAN_TOKEN);
        } else {
            ToastUtils.showShort("createPerson userid 为空");
        }
    }

    private void createCheck() {
        if (TextUtils.isEmpty(exitmemberId)) {//已创建就不会再创建
            if (bitmap != null) {
                if (TextUtils.isEmpty(memberId)) {//如果被识别出来，就说明已经入了
                    createPerson();
                } else {
                    ToastHelper.showToast("您已经添加过人脸了！");
                }
            } else {
                ToastHelper.showToast("请先录入人脸");
            }
        } else {
            ToastHelper.showToast("您已经添加过人脸了");
        }
    }

    /**
     * descirption: 更新人脸
     */
    private synchronized void updatePerson() {
        Log.d("imageCheck", "updatePerson: " + bitmap.getHeight() + ":" + bitmap.getWidth());
        String base64 = Base64BitmapUtil.bitmapToBase64(bitmap, PersonDetailActivity.this);
        if (!TextUtils.isEmpty(userid)) {
            PostRequestBody postRequestBody = new PostRequestBody(findbean.getUser().getId(), base64);
            api.postUpdate(postRequestBody, SPUtils.getInstance().getString(Constants.ACCESSTOKEN), Constants.MODIAN_TOKEN);
        } else {
            ToastUtils.showShort("updatePerson userid 为空");
        }
    }

    private void updateCheck() {
        if (TextUtils.isEmpty(exitmemberId)) {//已创建就不会再更新
            if (bitmap != null) {
                if (TextUtils.isEmpty(memberId)) {//如果被识别出来，就说明已经入了
                    updatePerson();
                } else {
                    ToastHelper.showToast("您已经有人脸录入过啦！");
                }
            } else {
                ToastHelper.showToast("请先录入人脸");
            }
        } else {
            ToastHelper.showToast("已经创建过了！");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.FACE_INPUT_REQUESTCODE && resultCode == Constants.FACE_INPUT_RESULTCODE) {
            byte[] image = data.getByteArrayExtra(Constants.INTENT_FACEINPUT_RGBDATA);
            memberId = data.getStringExtra(Constants.INTENT_FACEINPUT_MEMBERID);
            if (!TextUtils.isEmpty(memberId)) {
                if (!TextUtils.isEmpty(exitmemberId)) {
                    if (memberId == exitmemberId) {
                        istheSameFace = true;
                    }
                }
                if (image != null && image.length > 0) {
                    bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    GlideIn();
                }
            } else {
                if (image != null && image.length > 0) {
                    bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    GlideIn();
                }
            }
            createCheck();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        istheSameFace = false;
        if (arowsBeans != null) {
            arowsBeans.clear();
        }
        if (bitmap != null) {
            bitmap = null;
        }
        userid = null;
    }

}
