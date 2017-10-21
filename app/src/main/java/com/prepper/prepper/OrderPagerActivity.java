package com.prepper.prepper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewParent;

import java.util.List;

/**
 * Created by singh on 10/21/2017.
 */

public class OrderPagerActivity  extends AppCompatActivity {
    private static final String EXTRA_ORDER = "com.prepper.prepper.OrderActivity";
    private ViewPager mViewPager;
    private List<Food> mFoods;

    public static Intent newIntent(Context packageContext, String name){
        Intent intent = new Intent(packageContext, OrderPagerActivity.class);
        intent.putExtra(EXTRA_ORDER, name);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        String name = (String) getIntent().getSerializableExtra(EXTRA_ORDER);

        mViewPager = (ViewPager) findViewById(R.id.food_view_pager);

        mFoods = FoodLab.get(this).getFoods();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Food food = mFoods.get(position);
                Fragment FoodListFragment = new Fragment();

                Bundle args = new Bundle();
                args.putString("Food name",food.getFoodName());
                FoodListFragment.setArguments(args);
                return FoodListFragment;
            }

            @Override
            public int getCount() {
                return mFoods.size();
            }
        });

        for(int i = 0; i < mFoods.size(); i++){
            if(mFoods.get(i).getFoodName().equals(name)){
                mViewPager.setCurrentItem(i);
            }
        }
    }

}
