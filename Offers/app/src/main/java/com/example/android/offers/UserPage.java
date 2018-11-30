package com.example.android.offers;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserPage extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
  //  AlhasaFragmentAdapter fragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
       // fragmentAdapter = new AlhasaFragmentAdapter(getSupportFragmentManager());
       // viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
