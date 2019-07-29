package com.moredian.entrance.guard.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.moredian.entrance.guard.R;

public class MachinesettingActivity extends AppCompatActivity {

    public static Intent getMachinesettingActivityIntent(Context context) {
        Intent intent = new Intent(context, MachinesettingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machinesetting);
    }
}
