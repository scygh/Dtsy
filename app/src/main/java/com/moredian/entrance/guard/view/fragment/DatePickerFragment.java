package com.moredian.entrance.guard.view.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/11 09:29
 */
public class DatePickerFragment extends DialogFragment {

    private DatePicker mDatePicker;

    public static DatePickerFragment newInstance(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.DATE,new Date());
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Date date = (Date)getArguments().getSerializable(Constants.DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.datepicker_layout,null);
        mDatePicker = view.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year,month,day,null);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("选择日期")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day =mDatePicker.getDayOfMonth();
                        Date date1 = new GregorianCalendar(year,month,day).getTime();
                        if (mlistener != null) {
                            mlistener.onDialogClick(date1);
                        }
                    }
                })
                .create();
    }

    private OnDialogListener mlistener;

    public interface OnDialogListener {
        void onDialogClick(Date date);
    }

    public void setOnDialogListener(OnDialogListener dialogListener){
        this.mlistener = dialogListener;
    }
}
