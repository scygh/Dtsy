package com.moredian.entrance.guard.view.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.utils.ToastHelper;
import com.xyz.step.FlowViewHorizontal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/20 09:27
 */
public class PersonAddFragment extends BaseFragment {

    @BindView(R.id.fvh)
    FlowViewHorizontal fvh;
    @BindView(R.id.personadd_container)
    FrameLayout personaddContainer;
    @BindView(R.id.step_btn)
    Button stepBtn;
    @BindView(R.id.step_previous_btn)
    Button stepPreviousBtn;
    private FragmentManager fm;
    private Fragment childfragment;

    @Override
    public int initView() {
        return R.layout.fragment_person_add;
    }

    @Override
    public void initViewController() {
        stepBtn.setText("下一步");
        fvh.setProgress(0, 3, getResources().getStringArray(R.array.step), null);
        fm = this.getChildFragmentManager();
        childfragment = fm.findFragmentById(R.id.personadd_container);
        if (childfragment == null) {
            childfragment = new OrignalFragment();
            fm.beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_right_in,
                            R.anim.slide_left_out,
                            R.anim.slide_left_in,
                            R.anim.slide_right_out
                    )
                    .add(R.id.personadd_container, childfragment).commit();
        } else {
            childfragment = new OrignalFragment();
            fm.beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_right_in,
                            R.anim.slide_left_out,
                            R.anim.slide_left_in,
                            R.anim.slide_right_out
                    )
                    .replace(R.id.personadd_container, childfragment).commit();
        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.step_btn, R.id.step_previous_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.step_btn:
                if (childfragment instanceof OrignalFragment) {
                    childfragment = new FirstFragment();
                    fm.beginTransaction()
                            .setCustomAnimations(
                                    R.anim.slide_right_in,
                                    R.anim.slide_left_out,
                                    R.anim.slide_left_in,
                                    R.anim.slide_right_out
                            )
                            .replace(R.id.personadd_container, childfragment).commit();
                    stepBtn.setText("下一步");
                    stepPreviousBtn.setVisibility(View.VISIBLE);
                    fvh.setProgress(1, 3, getResources().getStringArray(R.array.step), null);
                } else if (childfragment instanceof FirstFragment) {
                    childfragment = new SecondFragment();
                    fm.beginTransaction()
                            .setCustomAnimations(
                                    R.anim.slide_right_in,
                                    R.anim.slide_left_out,
                                    R.anim.slide_left_in,
                                    R.anim.slide_right_out
                            )
                            .replace(R.id.personadd_container, childfragment).commit();
                    stepBtn.setText("开户");
                    stepPreviousBtn.setVisibility(View.VISIBLE);
                    fvh.setProgress(2, 3, getResources().getStringArray(R.array.step), null);
                } else if (childfragment instanceof SecondFragment) {
                    fvh.setProgress(3, 3, getResources().getStringArray(R.array.step), null);
                    // TODO: 2019/8/26 开户
                    ToastHelper.showToast("开户成功");
                }
                break;
            case R.id.step_previous_btn:
                if (childfragment instanceof FirstFragment) {
                    childfragment = new OrignalFragment();
                    fm.beginTransaction()
                            .setCustomAnimations(
                                    R.anim.slide_left_in,
                                    R.anim.slide_right_out,
                                    R.anim.slide_right_in,
                                    R.anim.slide_left_out
                            )
                            .replace(R.id.personadd_container, childfragment).commit();
                    stepBtn.setText("下一步");
                    stepPreviousBtn.setVisibility(View.GONE);
                    fvh.setProgress(0, 3, getResources().getStringArray(R.array.step), null);
                } else if (childfragment instanceof SecondFragment) {
                    childfragment = new FirstFragment();
                    fm.beginTransaction()
                            .setCustomAnimations(
                                    R.anim.slide_left_in,
                                    R.anim.slide_right_out,
                                    R.anim.slide_right_in,
                                    R.anim.slide_left_out
                            )
                            .replace(R.id.personadd_container, childfragment).commit();
                    stepBtn.setText("下一步");
                    fvh.setProgress(1, 3, getResources().getStringArray(R.array.step), null);
                }
                break;
        }
    }
}
