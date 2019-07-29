package com.moredian.entrance.guard.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.moredian.entrance.guard.R;

public class PersonsManageActivity extends AppCompatActivity {

    public static Intent getPersonsManageActivityIntent(Context context) {
        Intent intent = new Intent(context, PersonsManageActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_manage);
    }
}
