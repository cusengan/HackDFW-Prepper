package com.prepper.prepper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateMealActivity4 extends AppCompatActivity {

    public static final String TAG = "createmealactivity4";

    private ImageView mCreateMealImage;
    private ImageView mProgressBar;
    private Spinner mSpinner;
    private TextView mFoodTitle;
    private TextView mFoodDescription;
    private Spinner mSpinnerQty;
    private Button mAddButton;
    private TextView mCalCount;
    private Button mBackButton;
    private Button mNextButton;

    private String mMealName;
    private String mLastMeal;
    private int mOrderNumber = 1;

    public static Intent getIntent(Context packageContext, String mealName) {
        Intent i = new Intent(packageContext, CreateMealActivity4.class);
        i.putExtra(TAG, mealName);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("In create meal");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meal_4);

        mLastMeal = "food2";                                        //----------------------------------------TESTESTSESTSETSEt

        mCreateMealImage = (ImageView) findViewById(R.id.createMealImage4);
        mProgressBar = (ImageView) findViewById(R.id.progressBar4);
        mSpinner = (Spinner) findViewById(R.id.spinner4);
        mFoodTitle = (TextView) findViewById(R.id.foodTitle4);
        mFoodDescription = (TextView) findViewById(R.id.foodDescription4);
        mSpinnerQty = (Spinner) findViewById(R.id.spinnerQty4);
        mAddButton = (Button) findViewById(R.id.addButton4);
        mCalCount = (TextView) findViewById(R.id.calCount4);
        mBackButton = (Button) findViewById(R.id.backButton4);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CreateMealActivity3.getIntent(CreateMealActivity4.this, mLastMeal);
                startActivity(i);
            }
        });

        mNextButton = (Button) findViewById(R.id.nextButton4);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = PurchaseActivity.getIntent(CreateMealActivity4.this, mOrderNumber);
                i.putExtra(TAG, mOrderNumber);
                startActivity(i);
            }
        });
        mMealName = getIntent().getStringExtra(TAG);
        mFoodTitle.setText(mMealName);

    }
}