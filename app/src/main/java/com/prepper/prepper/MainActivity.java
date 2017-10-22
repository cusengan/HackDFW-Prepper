package com.prepper.prepper;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "mainactivity";
    private TextView mRecommend;
    private String userName = "William";
    private Button mButton2;
    private String mealName = "myMeal";


    public static Intent getIntent(Context packageContext){
        Intent i = new Intent(packageContext, MainActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CreateMealActivity1.getIntent(MainActivity.this, mealName);
                i.putExtra(TAG, mealName);
                startActivity(i);
            }
        });

        mRecommend = (TextView)findViewById(R.id.recommend_text);
        mRecommend.setText(R.string.recommend);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, new MealListFragment());
        ft.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_activity_bar, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.settings:
                break;
            case R.id.profile:
                Intent i = ProfileActivity.newIntent(getApplicationContext(), userName);
                startActivity(i);
                break;

        }
        return true;
    }
}
