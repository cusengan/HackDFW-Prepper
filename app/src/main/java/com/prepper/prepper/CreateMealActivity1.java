package com.prepper.prepper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.List;

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
    private RecyclerView mRecyclerView;


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

//    private class MealHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        private Meal mMeal;
//
//        private ImageView mImageView;
//        private TextView mMealName;
//        private TextView mMealCalories;
//
//        public MealHolder(LayoutInflater inflater, ViewGroup parent)  {
//            super(inflater.inflate(R.layout.meal_list_row, parent, false));
//
//            mImageView = (ImageView) itemView.findViewById(R.id.listImageView);
//            mMealName = (TextView)itemView.findViewById(R.id.ListMealName);
//            mMealCalories = (TextView)itemView.findViewById(R.id.ListMealCalorie);
//            itemView.setOnClickListener(this);
//        }
//
//        public void bind(Meal meal) {
//            mMeal = meal;
//            mMealName.setText(mMeal.getMealName());
//            mMealCalories.setText(String.valueOf(mMeal.getCal()));
//        }
//
//        @Override
//        public void onClick(View v) {
//            Intent intent = MealPagerActivity.newIntent(getContext(), mMeal.getMealName());
//            startActivity(intent);
//        }
//    }
//
//    private class MealAdapter extends RecyclerView.Adapter<MealListFragment.MealHolder>{
//
//        private List<Meal> mMeals;
//
//        public MealAdapter(List<Meal> meals){
//            mMeals = meals;
//        }
//
//        @Override
//        public MealListFragment.MealHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater inflater = LayoutInflater.from(getActivity());
//            return new MealListFragment.MealHolder(inflater, parent);
//        }
//
//        @Override
//        public void onBindViewHolder(MealListFragment.MealHolder holder, int position) {
//            Meal meal = mMeals.get(position);
//            holder.bind(meal);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mMeals.size();
//        }
//    }
//
//    private class getId extends AsyncTask<Void, Void, List<Meal>> {
//
//        @Override
//        protected List<Meal> doInBackground(Void... params) {
//
//            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
//                    getApplicationContext(),
//                    "us-east-1:eeb724cc-0fe5-4fa9-9282-023995ffd07e", // Identity pool ID
//                    Regions.US_EAST_1 // Region
//            );
//
//            AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
//            DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
//            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
//            PaginatedScanList<Meal> result = mapper.scan(Meal.class, scanExpression);
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(List<Meal> items) {
//            mMeals = items;
//            setupAdapter();
//        }
//
//    }
}
