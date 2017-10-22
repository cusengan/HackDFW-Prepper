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

public class CreateMealActivity2 extends AppCompatActivity {

    public static final String TAG = "createmealactivity2";

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
        Intent i = new Intent(packageContext, CreateMealActivity2.class);
        i.putExtra(TAG, mealName);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("In create meal");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meal_2);

        mNextMeal = "food3";                         //-----------------------------------------------TESTSETTESSTE
        mLastMeal = "food1";

        mCreateMealImage = (ImageView) findViewById(R.id.createMealImage2);
        mProgressBar = (ImageView) findViewById(R.id.progressBar2);
        mSpinner = (Spinner) findViewById(R.id.spinner2);
        mFoodTitle = (TextView) findViewById(R.id.foodTitle2);
        mFoodDescription = (TextView) findViewById(R.id.foodDescription2);
        mSpinnerQty = (Spinner) findViewById(R.id.spinnerQty2);
        mAddButton = (Button) findViewById(R.id.addButton2);
        mCalCount = (TextView) findViewById(R.id.calCount2);
        mBackButton = (Button) findViewById(R.id.backButton2);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CreateMealActivity1.getIntent(CreateMealActivity2.this, mLastMeal);
                startActivity(i);
            }
        });
        mNextButton = (Button) findViewById(R.id.nextButton2);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CreateMealActivity3.getIntent(CreateMealActivity2.this, mNextMeal);
                startActivity(i);
            }
        });
        mMealName = getIntent().getStringExtra(TAG);
        mFoodTitle.setText(mMealName);


    }
}
