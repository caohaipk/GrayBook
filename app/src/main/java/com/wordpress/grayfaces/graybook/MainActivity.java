package com.wordpress.grayfaces.graybook;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wordpress.grayfaces.graybook.adapters.SectionsPagerAdapter;
import com.wordpress.grayfaces.graybook.fragments.PlaceholderFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private PlaceholderFragment[] fraglist = new PlaceholderFragment[3];
    private Calendar currentDate;
    private final int PAGE_CENTER = 1;
    private int focusPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),fraglist);

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                focusPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {

                    if (focusPage < PAGE_CENTER) {
                        currentDate.add(Calendar.DATE, -1);
                    } else if (focusPage > PAGE_CENTER) {
                        currentDate.add(Calendar.DATE, 1);
                    }
                    mSectionsPagerAdapter.setDate(currentDate);
                    mViewPager.setCurrentItem(1, false);
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initDate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initDate(){
        currentDate = Calendar.getInstance();

        Calendar prevDate = Calendar.getInstance();
        prevDate.setTime(currentDate.getTime());
        prevDate.add(Calendar.DATE,-1);

        Calendar nextDate = Calendar.getInstance();
        nextDate.setTime(currentDate.getTime());
        nextDate.add(Calendar.DATE,1);
        //create all 3 fragments
        fraglist[0] = PlaceholderFragment.newInstance(prevDate);
        fraglist[1] = PlaceholderFragment.newInstance(currentDate);
        fraglist[2] = PlaceholderFragment.newInstance(nextDate);
    }

}
