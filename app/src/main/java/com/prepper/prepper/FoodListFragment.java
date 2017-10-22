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

public class FoodListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FoodAdapter mAdapter;
    private List<Food> mFoods = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prebuilt_order, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        testing();
        updateUI();
        return view;
    }

//    public void updateView(){
//        new getId.execute();
//    }

//    private void testing(){
//        FoodLab lab = FoodLab.get(getContext());
//        lab.addFood(new Food("Rice", 13.12));
//        lab.addFood(new Food("Noodles", 12.23));
//        lab.addFood(new Food("Cats", 100));
//    }

    private void setupAdapter() {
        if (isAdded()) {
            mRecyclerView.setAdapter(new FoodAdapter(mFoods));
        }
    }

    public void setList(List<Food> list){
        mFoods = list;
        System.out.println(mFoods.size() + "NEW ARRAY");
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

        private Food mFood;

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
            mFoodName.setText(mFood.getFoodName());
            mCalories.setText(String.valueOf(mFood.getCalories()));

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "hi!", Toast.LENGTH_LONG).show();
            Intent intent = OrderPagerActivity.newIntent(getActivity(), mFood.getFoodName());
            startActivity(intent);
        }
    }

    private class FoodAdapter extends RecyclerView.Adapter<FoodHolder>{

        private List<Food> mFoods;

        public FoodAdapter(List<Food> foods){
            mFoods = foods;
        }

        @Override
        public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new FoodHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(FoodHolder holder, int position) {
            Food food = mFoods.get(position);
            holder.bind(food);
        }

        @Override
        public int getItemCount() {
            return mFoods.size();
        }
    }

    private class getId extends AsyncTask<Void, Void, List<Food>> {

        @Override
        protected List<Food> doInBackground(Void... params) {

            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    getContext(),
                    "us-east-1:2a52890a-4b46-4922-9624-3419adad9e02", // Identity pool ID
                    Regions.US_EAST_1 // Region
            );

            AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
            DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            PaginatedScanList<Food> result = mapper.scan(Food.class, scanExpression);
            System.out.print(result.size());
            return result;
        }

        @Override
        protected void onPostExecute(List<Food> items) {
            mFoods = items;
            setList(items);
            setupAdapter();
        }

    }
}
