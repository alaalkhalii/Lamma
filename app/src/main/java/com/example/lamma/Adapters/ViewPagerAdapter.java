package com.example.lamma.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;

import com.example.lamma.Fragment.FragmentMovies;
import com.example.lamma.Fragment.FragmentSeries;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new FragmentMovies();
        }
        else if (position == 1)
        {
            fragment = new FragmentSeries();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Movies";
        }
        else if (position == 1)
        {
            title = "Series ";
        }
        return title;
    }
}
