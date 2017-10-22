package com.prepper.prepper;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lordv on 10/21/2017.
 */

public class MealListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MealAdapter mAdapter;
    private List<Meal> mMeals = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prebuilt_order, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        updateUI();
        return view;
    }


    private void setupAdapter() {
        if (isAdded()) {
            mRecyclerView.setAdapter(new MealAdapter(mMeals));
        }
    }


    private void updateUI() {
        AsyncTask task = new getId().execute();
        try{
            mMeals = (List<Meal>)task.get();

        }catch(Exception e){

        }
//        setupAdapter();
        if(mAdapter == null){
            mAdapter = new MealAdapter(mMeals);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }

    }

    private class MealHolder extends RecyclerView.ViewHolder {

        private Meal mMeal;

        private ImageView mImageView;
        private TextView mMealName;
        private TextView mMealCalories;
        private TextView mMealDescription;
        private Button mCreateOrderButton;

        public MealHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.activity_order, parent, false));

            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mMealName = (TextView)itemView.findViewById(R.id.mealName);
            mMealCalories = (TextView)itemView.findViewById(R.id.mealCalories);
            mMealDescription = (TextView) itemView.findViewById(R.id.mealDescription);
            mCreateOrderButton = (Button) itemView.findViewById(R.id.createOrderButton);
        }

        public void bind(Meal meal) {
            mMeal = meal;
            mMealName.setText(mMeal.getName());
            mMealCalories.setText(String.valueOf(mMeal.getCal()));

        }
    }

    private class MealAdapter extends RecyclerView.Adapter<MealHolder>{

        private List<Meal> mMeals;

        public MealAdapter(List<Meal> meals){
            mMeals = meals;
        }

        @Override
        public MealHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new MealHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(MealHolder holder, int position) {
            Meal meal = mMeals.get(position);
            holder.bind(meal);
        }

        @Override
        public int getItemCount() {
            return mMeals.size();
        }
    }

    private class getId extends AsyncTask<Void, Void, List<Meal>> {

        @Override
        protected List<Meal> doInBackground(Void... params) {

            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    getContext(),
                    "us-east-1:eeb724cc-0fe5-4fa9-9282-023995ffd07e", // Identity pool ID
                    Regions.US_EAST_1 // Region
            );

            AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
            DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            PaginatedScanList<Meal> result = mapper.scan(Meal.class, scanExpression);
            return result;
        }

        @Override
        protected void onPostExecute(List<Meal> items) {
            mMeals = items;
            setupAdapter();
        }

    }
}
