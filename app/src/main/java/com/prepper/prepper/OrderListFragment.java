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
import java.util.Arrays;
import java.util.List;

/**
 * Created by lordv on 10/21/2017.
 */

public class OrderListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private OrderAdapter mAdapter;
    private List<Order> mOrders = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prebuilt_order, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

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
            mRecyclerView.setAdapter(new OrderAdapter(mOrders));
        }
    }

    private void updateUI() {
        AsyncTask task = new getOrders().execute();
        try{
            mOrders = (List<Order>)task.get();

        }catch(Exception e){

        }
//        setupAdapter();
        if(mAdapter == null){
            mAdapter = new OrderAdapter(mOrders);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
//        FoodLab lab = FoodLab.get(getContext());
//        lab.setFoods(mFoods);
        System.out.println(mOrders.size()+ "foodholder");
    }

    private class OrderHolder extends RecyclerView.ViewHolder{

        private Order mOrder;
        private List<String> mFoods;

        private TextView mOrderNumber;
        private TextView mMeal;

        public OrderHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.food_list_row, parent, false));

        }

        public void bind(Order order){
            mOrder = order;
            mOrderNumber.setText(mOrder.getOrderNumber());
            StringBuilder sb = new StringBuilder();
            List<String> foods = mOrder.getFoods();
            for(int i = 0; i < foods.size(); i++){
                if(i > 0)sb.append(", ");
                sb.append(foods.get(i));
            }
            mMeal.setText(sb.toString());
            mOrderNumber.setText(mOrder.getOrderNumber());

        }

    }

    private class OrderAdapter extends RecyclerView.Adapter<OrderHolder>{

        private List<Order> mOrder;

        public OrderAdapter(List<Order> orders){
            mOrder = orders;
        }

        @Override
        public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new OrderHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(OrderHolder holder, int position) {
            Order order = mOrder.get(position);
            holder.bind(order);
        }

        @Override
        public int getItemCount() {
            return mOrder.size();
        }
    }

    private class getOrders extends AsyncTask<Void, Void, List<Order>> {

        @Override
        protected List<Order> doInBackground(Void... params) {

            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    getContext(),
                    "us-east-1:adad0d07-90d6-4e2b-bd2d-d9e39ca88b16", // Identity pool ID
                    Regions.US_EAST_1 // Region
            );

            AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
            DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            PaginatedScanList<Order> result = mapper.scan(Order.class, scanExpression);
            System.out.print(result.size());
            return result;
        }

        @Override
        protected void onPostExecute(List<Order> items) {
            mOrders = items;
            setupAdapter();
        }

    }
}
