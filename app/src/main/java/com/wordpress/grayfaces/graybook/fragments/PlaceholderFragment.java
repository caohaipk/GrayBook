package com.wordpress.grayfaces.graybook.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpress.grayfaces.graybook.R;
import com.wordpress.grayfaces.graybook.ultis.Ultility;

import java.util.Calendar;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    /**
     * TAG values
     */
    private static String TAG = PlaceholderFragment.class.getSimpleName();
    private TextView textView;


    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(Calendar sectionCalendar) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        Log.e(TAG, "newInstance: " + sectionCalendar.getTime().getTime() );
        args.putLong(ARG_SECTION_NUMBER, sectionCalendar.getTime().getTime());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        textView = rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format2, getSectionDate()));
        return rootView;
    }
    /**
     * updateUi when change view
     * @param date - Date change
     */
    public void updateUI(Date date) {
        View v = getView();
        if (v == null)
            return;
        textView.setText(getString(R.string.section_format2, Ultility.FormatterDate(date)));
    }

    private String getSectionDate(){
        Date date = new Date(getArguments().getLong(ARG_SECTION_NUMBER));
        return Ultility.FormatterDate(date);
    }
}