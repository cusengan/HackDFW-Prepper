package com.prepper.prepper;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

/**
 * Created by singh on 10/21/2017.
 */

public class OrderFragment extends Fragment{

    private static final String ARG_FOOD_NAME = "food_name";

    private Food mFood;
    private File mPhotoFile;
    private TextView mMealName;
    private TextView mMealCals;
    private TextView mMealDiscription;
    private Button mSubmit;

    public static OrderFragment newInstance(String name){
        Bundle args = new Bundle();
        args.putSerializable(ARG_FOOD_NAME, name);

        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        String name = getArguments().getString(ARG_FOOD_NAME);
        mFood = FoodLab.get(getActivity()).getFood(name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_prebuilt_order, container, false);

        mMealName = (TextView) v.findViewById(R.id.mealName);
        mMealName.setText(mFood.getFoodName());

        mMealCals = (TextView) v.findViewById(R.id.mealCalories);
        mMealCals.setText(Double.toString(mFood.getCalories()));

        mMealDiscription = (TextView) v.findViewById(R.id.mealDescription);


        mSubmit = (Button) v.findViewById(R.id.submitButton);
        mSubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
            }
        });

        return v;
    }
}
