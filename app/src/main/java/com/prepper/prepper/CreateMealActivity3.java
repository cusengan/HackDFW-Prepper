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

public class CreateMealActivity3 extends AppCompatActivity {

    public static final String TAG = "createmealactivity3";

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
    private String mNextMeal;

    public static Intent getIntent(Context packageContext, String mealName) {
        Intent i = new Intent(packageContext, CreateMealActivity3.class);
        i.putExtra(TAG, mealName);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("In create meal");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meal_3);

        mNextMeal = "food4";                         //-----------------------------------------------TESTSETTESSTE
        mLastMeal = "food2";

        mCreateMealImage = (ImageView) findViewById(R.id.createMealImage3);
        mProgressBar = (ImageView) findViewById(R.id.progressBar3);
        mSpinner = (Spinner) findViewById(R.id.spinner3);
        mFoodTitle = (TextView) findViewById(R.id.foodTitle3);
        mFoodDescription = (TextView) findViewById(R.id.foodDescription3);
        mSpinnerQty = (Spinner) findViewById(R.id.spinnerQty3);
        mAddButton = (Button) findViewById(R.id.addButton3);
        mCalCount = (TextView) findViewById(R.id.calCount3);
        mBackButton = (Button) findViewById(R.id.backButton3);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CreateMealActivity2.getIntent(CreateMealActivity3.this, mLastMeal);
                startActivity(i);
            }
        });
        mNextButton = (Button) findViewById(R.id.nextButton3);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CreateMealActivity4.getIntent(CreateMealActivity3.this, mNextMeal);
                startActivity(i);
            }
        });
        mMealName = getIntent().getStringExtra(TAG);
        mFoodTitle.setText(mMealName);

    }
}