package com.prepper.prepper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PurchaseActivity extends AppCompatActivity {

    public static final String TAG = "purchaseactivity";

    private TextView mOrderText;
    private Button mPurchaseButton;

    public static Intent getIntent(Context packageContext, int orderNumber){
        Intent i = new Intent(packageContext, PurchaseActivity.class);
        i.putExtra(TAG, orderNumber);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        mOrderText = (TextView) findViewById(R.id.totalOrderText);
        mPurchaseButton = (Button) findViewById(R.id.purchaseButton);
        mPurchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(PurchaseActivity.this, "Purchase Made", Toast.LENGTH_SHORT);
                toast.show();
                Intent i = MainActivity.getIntent(PurchaseActivity.this);
                startActivity(i);
            }
        });
    }
}
