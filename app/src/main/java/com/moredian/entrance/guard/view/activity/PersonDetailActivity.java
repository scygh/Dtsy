package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.moredian.entrance.guard.entity.GetUserByUserID;
import com.moredian.entrance.guard.entity.PostRequestBody;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.Base64BitmapUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonDetailActivity extends BaseActivity {

    private static final String TAG = "PersonDetailActivity";
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

    @Override
    public int layoutView() {
        return R.layout.activity_person_detail;
    }

    @Override
    public void initView() {
        pageName.setText("人员详情");
    }

    @Override
    public void initData() {
        dataIntent = getIntent();
        initRequest();
    }

    /**
     * descirption: 请求数据初始化
     */
    private void initRequest() {
        abposition = dataIntent.getIntExtra(Constants.INTENT_ROWSBEAN_POSITION, 0);
        userid = dataIntent.getStringExtra(Constants.INTENT_ROWSBEAN_BEAN);
        if (!TextUtils.isEmpty(userid)) {
            api.getListByPage(1, 5000);
            api.setOnResponse(new Api.OnResponse() {
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
            api.setCreateResponse(new Api.CreateResponse() {
                @Override
                public void onCreate() {
                    updateData();
                }
            });
        } else {
            //拿position去拿数据
            int page = abposition / 20 + 1;
            abposition = abposition % 20;
            api.getListByPage(page, 20);
            api.setOnResponse(new Api.OnResponse() {
                @Override
                public void onResponse(List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
                    arowsBeans.clear();
                    arowsBeans.addAll(rowsBeans);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setData();
                        }
                    });
                }

                @Override
                public void onResponseMore(List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
                    arowsBeans = rowsBeans;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setData();
                        }
                    });
                }

                @Override
                public void onFailed() {

                }
            });
            api.setCreateResponse(new Api.CreateResponse() {
                @Override
                public void onCreate() {
                    updateData();
                }
            });
        }
    }

    /**
    * descirption: 如果创建成功就调用此方法更新人脸
    */
    private void updateData() {
        if (bitmap != null) {
            //如果是没录入过，或者是同一个人则可以更新
            if (istheSameFace || exitmemberId == null) {
                updatePerson();
            } else {
                ToastUtils.showShort("更新的人脸不是您自己的，请重新选择名字");
            }
        } else {
            ToastUtils.showShort("人脸为空");
        }
    }

    /**
    * descirption: 列表界面点击设置数据
    */
    private void setData() {
        persondetailName.setText(arowsBeans.get(abposition).getUser().getName());
        persondetailCardnum.setText(arowsBeans.get(abposition).getUser().getIdCard());
        persondetailStunum.setText(arowsBeans.get(abposition).getUser().getDepartmentId());
        persondetailTelephone.setText(arowsBeans.get(abposition).getUser().getPhone());
        if (arowsBeans.get(abposition).getUserFace() != null) {
            exitmemberId = dataIntent.getStringExtra(arowsBeans.get(abposition).getUserFace().getMemberId());
            if (!TextUtils.isEmpty(arowsBeans.get(abposition).getUserFace().getMemberBase64())) {
                bitmap = Base64BitmapUtil.base64ToBitmap(arowsBeans.get(abposition).getUserFace().getMemberBase64());
                persondetailCamera.setImageBitmap(bitmap);
            }
        }
    }

    /**
    * descirption: 查询界面点击设置数据
    */
    private void setData(GetListByPage.ContentBean.RowsBean findbean) {
        persondetailName.setText(findbean.getUser().getName());
        persondetailCardnum.setText(findbean.getUser().getIdCard());
        persondetailStunum.setText(findbean.getUser().getDepartmentId());
        persondetailTelephone.setText(findbean.getUser().getPhone());
        if (findbean.getUserFace() != null) {
            exitmemberId = dataIntent.getStringExtra(findbean.getUserFace().getMemberId());
            if (!TextUtils.isEmpty(findbean.getUserFace().getMemberBase64())) {
                bitmap = Base64BitmapUtil.base64ToBitmap(findbean.getUserFace().getMemberBase64());
                persondetailCamera.setImageBitmap(bitmap);
            }
        }
    }

    @OnClick({R.id.Manualconsumption_back, R.id.persondetail_camera, R.id.persondetail_sure, R.id.persondetail_cancle, R.id.personDetail_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.persondetail_camera:
                // TODO: 2019/7/31 启动人脸录入 一张照片
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
                deletePerson();
                break;
        }
    }

    /**
    * descirption: 删除创建
    */
    private void deletePerson() {
        if (!TextUtils.isEmpty(userid)) {
            PostRequestBody postRequestBody = new PostRequestBody(findbean.getUser().getId());
            api.postDelete(postRequestBody, SPUtils.getInstance().getString(Constants.ACCESSTOKEN), Constants.MODIAN_TOKEN);
        } else {
            if (arowsBeans.size() > 0) {
                PostRequestBody postRequestBody = new PostRequestBody(arowsBeans.get(abposition).getUser().getId());
                api.postDelete(postRequestBody, SPUtils.getInstance().getString(Constants.ACCESSTOKEN), Constants.MODIAN_TOKEN);
            } else {
                ToastUtils.showShort("没有找到列表");
            }
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
            PostRequestBody postRequestBody = new PostRequestBody(arowsBeans.get(abposition).getUser().getId(), arowsBeans.get(abposition).getUser().getName(),
                    arowsBeans.get(abposition).getUser().getPhone());
            api.postCreate(postRequestBody, SPUtils.getInstance().getString(Constants.ACCESSTOKEN), Constants.MODIAN_TOKEN);
        }

    }

    /**
     * descirption: 更新人脸
     */
    private synchronized void updatePerson() {
        String b = Base64BitmapUtil.bitmapToBase64(bitmap, PersonDetailActivity.this);
        if (!TextUtils.isEmpty(userid)) {
            PostRequestBody postRequestBody = new PostRequestBody(findbean.getUser().getId(), b);
            api.postUpdate(postRequestBody, SPUtils.getInstance().getString(Constants.ACCESSTOKEN), Constants.MODIAN_TOKEN);
        } else {
            PostRequestBody postRequestBody = new PostRequestBody(arowsBeans.get(abposition).getUser().getId(), b);
            api.postUpdate(postRequestBody, SPUtils.getInstance().getString(Constants.ACCESSTOKEN), Constants.MODIAN_TOKEN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.FACE_INPUT_REQUESTCODE && resultCode == Constants.FACE_INPUT_RESULTCODE) {
            byte[] image = data.getByteArrayExtra(Constants.INTENT_FACEINPUT_RGBDATA);
            String memberId = data.getStringExtra(Constants.INTENT_FACEINPUT_MEMBERID);
            if (memberId != null) {
                //如果当前人员已经录过，且两者是同一个人，就允许更新人脸
                if (exitmemberId != null) {
                    if (memberId == exitmemberId) {
                        istheSameFace = true;
                    }
                }
                if (image != null && image.length > 0) {
                    bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    persondetailCamera.setImageBitmap(bitmap);
                }
            } else {
                if (image != null && image.length > 0) {
                    bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    persondetailCamera.setImageBitmap(bitmap);
                }
            }
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
