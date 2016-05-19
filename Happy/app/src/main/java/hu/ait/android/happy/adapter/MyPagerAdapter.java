package hu.ait.android.happy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hu.ait.android.happy.fragment.DetailsFragment;
import hu.ait.android.happy.fragment.WeatherFragment;

public class MyPagerAdapter  extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return new WeatherFragment();
            case 1:
                return new DetailsFragment();
            default:
                return new WeatherFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Weather";
            case 1:
                return "More Details";
            default:
                return "unknown";
        }
    }
}