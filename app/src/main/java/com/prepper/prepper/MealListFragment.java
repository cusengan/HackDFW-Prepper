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

//        testing();
        updateUI();
        return view;
    }

//    public void updateView(){
//        new getId.execute();
//    }

//    private void testing(){
//        MealLab lab = MealLab.get(getContext());
//        lab.addFood(new Meal("Rice", 13.12));
//        lab.addFood(new Food("Noodles", 12.23));
//        lab.addFood(new Food("Cats", 100));
//    }

    private void setupAdapter() {
        if (isAdded()) {
            mRecyclerView.setAdapter(new MealAdapter(mMeals));
        }
    }

    public void setList(List<Meal> list){
        mMeals = list;
        System.out.println(mMeals.size() + "NEW ARRAY");
        FoodLab lab = FoodLab.get(getContext());
        lab.setFoods(mFoods);
    }

    private void updateUI() {
        AsyncTask task = new getId().execute();
        try{
            mFoods = (List<Food>)task.get();

        }catch(Exception e){

        }
//        setupAdapter();
        if(mAdapter == null){
            mAdapter = new FoodAdapter(mFoods);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
//        FoodLab lab = FoodLab.get(getContext());
//        lab.setFoods(mFoods);
        System.out.println(mFoods.size()+ "foodholder");
    }

    private class FoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Meal mMeal;

        private TextView mFoodName;
        private TextView mCalories;

        public FoodHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.food_list_row, parent, false));
            itemView.setOnClickListener(this);

            mFoodName = (TextView)itemView.findViewById(R.id.foodName);
            mCalories = (TextView)itemView.findViewById(R.id.foodCalories);
        }

        public void bind(Food food){
            mFood = food;
            mFoodName.setText(mMeal.getName());
            mCalories.setText(String.valueOf(Meal.getCalories()));

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "hi!", Toast.LENGTH_LONG).show();
            Intent intent = OrderPagerActivity.newIntent(getActivity(), mMeal.getName());
            startActivity(intent);
        }
    }

    private class MealAdapter extends RecyclerView.Adapter<MealHolder>{

        private List<Food> mFoods;

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
//            System.out.print(result.size());
            return result;
        }

        @Override
        protected void onPostExecute(List<Meal> items) {
            mMeals = items;
            setList(items);
            setupAdapter();
        }

    }
}
