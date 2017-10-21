package com.prepper.prepper;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.add(R.id.fragment_container, new PeopleListFragment());
//        ft.commit();

        mRecommend = (TextView)findViewById(R.id.recommend_text);

        mRecommend.setText(R.string.recommend);
    }
}
