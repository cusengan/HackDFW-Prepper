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

public class CreateMealActivity1 extends AppCompatActivity {

    public static final String TAG = "createmealactivity1";

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
    private String mNextMeal;

    public static Intent getIntent(Context packageContext, String mealName) {
        Intent i = new Intent(packageContext, CreateMealActivity1.class);
        i.putExtra(TAG, mealName);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("In create meal");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meal_1);

        mNextMeal = "food2";              //--------------------------------------------------------------------TESTESTESTESTEST

        mCreateMealImage = (ImageView) findViewById(R.id.createMealImage1);
        mProgressBar = (ImageView) findViewById(R.id.progressBar1);
        mSpinner = (Spinner) findViewById(R.id.spinner1);
        mFoodTitle = (TextView) findViewById(R.id.foodTitle1);
        mFoodDescription = (TextView) findViewById(R.id.foodDescription1);
        mSpinnerQty = (Spinner) findViewById(R.id.spinnerQty1);
        mAddButton = (Button) findViewById(R.id.addButton1);
        mCalCount = (TextView) findViewById(R.id.calCount1);
        mBackButton = (Button) findViewById(R.id.backButton1);
        mNextButton = (Button) findViewById(R.id.nextButton1);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CreateMealActivity2.getIntent(CreateMealActivity1.this, mNextMeal);
                startActivity(i);
            }
        });
        mMealName = getIntent().getStringExtra(TAG);
        mFoodTitle.setText(mMealName);

    }
}
