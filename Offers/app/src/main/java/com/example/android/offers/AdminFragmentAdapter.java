package com.example.android.offers;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdminFragmentAdapter extends FragmentPagerAdapter {


    public AdminFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        UserPage.DemoElectronicFragment ee = new UserPage.DemoElectronicFragment()/*FoodFragment()*/;
        UserPage.DemoFoodFragment ef = new UserPage.DemoFoodFragment();

        switch (position) {
            case 0:

                return ee;
            case 1:
                return ef;
//            case 2:
//                return ef;
//            case 3:
//                return ef;
        }

        notifyDataSetChanged();

        return null;
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Added Offers";
            case 1:
                return "Company Offers";
            case 2:
                return "Games";
            case 3:
                return "Cars";
        }
        return super.getPageTitle(position);
    }
}



