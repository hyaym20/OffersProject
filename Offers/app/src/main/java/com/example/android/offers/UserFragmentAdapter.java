package com.example.android.offers;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UserFragmentAdapter extends FragmentPagerAdapter {


    public UserFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       /* switch (position) {
            case 0:
                return new AlhasaGardens();
            case 1:
                return new AlhasaMallsFragment();
            case 2:
                return new AlhasaRestaurant();
            case 3:
                return new AlhasaEventsFragment();
        }*/

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Electronics";
            case 1:
                return "Food";
            case 2:
                return "Games";
            case 3:
                return "Cars";
        }
        return super.getPageTitle(position);
    }
}
