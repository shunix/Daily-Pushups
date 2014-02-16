package com.shunix.dailypushups.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.shunix.dailypushups.BuildConfig;
import com.shunix.dailypushups.R;
import com.shunix.dailypushups.ui.Bar;
import com.shunix.dailypushups.ui.BarGraph;

import java.util.ArrayList;

/**
 * @author Ray WANG <admin@shunix.com>
 * @version 1.0.0
 * @since Feb 16th, 2014
 */
public class BarGraphFragment extends Fragment {
    /**
     * used as the date range picker.
     */
    private Spinner dateSpinner;
    /**
     * used to show the graph.
     */
    private BarGraph barGraph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bargraph_layout, container, false);
        dateSpinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(arrayAdapter);
        barGraph = (BarGraph) view.findViewById(R.id.bargraph);
        if (BuildConfig.DEBUG) {
            ArrayList<Bar> list = new ArrayList<Bar>();
            Bar b1 = new Bar();
            b1.setColor(Color.CYAN);
            b1.setName("Bar 1");
            b1.setValue(20);
            list.add(b1);
            Bar b2 = new Bar();
            b2.setColor(Color.GRAY);
            b2.setName("Bar 2");
            b2.setValue(30);
            list.add(b2);
            Bar b3 = new Bar();
            b3.setColor(Color.YELLOW);
            b3.setName("Bar 3");
            b3.setValue(15);
            list.add(b3);
            barGraph.setBars(list);
        }
        return view;
    }
}
