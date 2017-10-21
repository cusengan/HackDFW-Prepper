package com.prepper.prepper;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by singh on 10/21/2017.
 */

public class FoodLab {
    private static FoodLab sFoodLab;

    private List<Food> mFoods;

    public static FoodLab get(Context context){
        if(sFoodLab == null){
            sFoodLab = new FoodLab(context);
        }

        return sFoodLab;
    }

    private FoodLab(Context context){
        mFoods = new ArrayList<>();
    }

    public List<Food> getFoods(){
        return mFoods;
    }

    public void addFood(Food food){
        mFoods.add(food);
    }

    public Food getFood(String name){
        for(Food food: mFoods) {
            if(food.getFoodName().equals(name)){
                return food;
            }
        }
        
        return null;
    }
}
