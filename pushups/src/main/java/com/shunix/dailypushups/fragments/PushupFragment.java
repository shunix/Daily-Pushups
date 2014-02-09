package com.shunix.dailypushups.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shunix.dailypushups.R;
import com.shunix.dailypushups.ui.HoloCircularProgressBar;

/**
 * @author Ray WANG <admin@shunix.com>
 * @version 1.0.0
 * @since Feb 9th, 2014
 */
public class PushupFragment extends Fragment {

    /**
     * Show the countdown.
     */
    private HoloCircularProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pushup_layout, container, false);
        progressBar = (HoloCircularProgressBar) view.findViewById(R.id.holoCircularProgressBar);
        return view;
    }
}
