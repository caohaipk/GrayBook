package com.wordpress.grayfaces.graybook.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.wordpress.grayfaces.graybook.fragments.PlaceholderFragment;

import java.util.Calendar;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private String TAG = SectionsPagerAdapter.class.getSimpleName();
    PlaceholderFragment[] fraglist;
    PlaceholderFragment temp;
    int fragNumber = 3;
    Calendar[] list = new Calendar[3];

    public SectionsPagerAdapter(FragmentManager fm, PlaceholderFragment[] fraglist) {
        super(fm);
        this.fraglist = fraglist;
    }

    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
                Log.e(TAG, "getItem: " + list[position] );
//        return PlaceholderFragment.newInstance(list[position]);
        return fraglist[position];
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
    //reset fragment when focus view chaqnge
    public void setDate(Calendar currentDate){
        Calendar prevDate = Calendar.getInstance();
        prevDate.setTime(currentDate.getTime());
        prevDate.add(Calendar.DATE,-1);

        Calendar nextDate = Calendar.getInstance();
        nextDate.setTime(currentDate.getTime());
        nextDate.add(Calendar.DATE,1);
        //update all 3 fragments
        fraglist[0].updateUI(prevDate.getTime());
        fraglist[1].updateUI(currentDate.getTime());
        fraglist[2].updateUI(nextDate.getTime());
        list[0] = prevDate;
        list[1] = currentDate;
        list[2] = nextDate;
    }
}