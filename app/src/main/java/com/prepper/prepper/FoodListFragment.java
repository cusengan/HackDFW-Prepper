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

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;


import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by lordv on 10/21/2017.
 */

public class FoodListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FoodAdapter mAdapter;

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

        testing();
        updateUI();
        return view;
    }

    private void testing(){
        FoodLab lab = FoodLab.get(getContext());
        lab.addFood(new Food("Rice", 13.12));
        lab.addFood(new Food("Noodles", 12.23));
        lab.addFood(new Food("Cats", 100));
    }

    private void updateUI() {
        FoodLab lab = FoodLab.get(getContext());
        List<Food> foods = lab.getFoods();
        new getId().execute();
        if(mAdapter == null){
            mAdapter = new FoodAdapter(foods);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class FoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Food mFood;

        private TextView mFoodName;
        private TextView mCalories;

        public FoodHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.food_list_row, parent, false));
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
            Intent intent = OrderActivity.newIntent(getActivity(), mFood.getFoodName());
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
            LayoutInflater inflater = LayoutInflater.from(getContext());
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

    private class getId extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    getContext(),
                    "us-east-1:2a52890a-4b46-4922-9624-3419adad9e02", // Identity pool ID
                    Regions.US_EAST_1 // Region
            );

            AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
            DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
            Food food = new Food("Pototaes", 300);
            mapper.save(food);
            return null;
        }

//        @Override
//        protected void onPostExecute(String items) {
//
//        }

    }


}
