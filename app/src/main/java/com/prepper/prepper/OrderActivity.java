package com.prepper.prepper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by singh on 10/21/2017.
 */

public class OrderActivity extends AppCompatActivity{

    private static final String EXTRA_ORDER = "com.prepper.prepper.OrderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    public static Intent newIntent(Context packageContext, String foodName) {
        Intent intent = new Intent(packageContext, OrderActivity.class);
        return intent;
    }
}
