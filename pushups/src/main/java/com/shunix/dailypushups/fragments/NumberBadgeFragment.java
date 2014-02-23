package com.shunix.dailypushups.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shunix.dailypushups.R;

/**
 * Created by shunix on 2/23/14.
 */
public class NumberBadgeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.number_badge, container, false);
    }
}
