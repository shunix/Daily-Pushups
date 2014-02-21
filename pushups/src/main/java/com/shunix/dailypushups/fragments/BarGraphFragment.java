package com.shunix.dailypushups.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.shunix.dailypushups.BuildConfig;
import com.shunix.dailypushups.R;
import com.shunix.dailypushups.database.CacheManager;
import com.shunix.dailypushups.ui.Bar;
import com.shunix.dailypushups.ui.BarGraph;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    /**
     * used to store the data in last few days.
     */
    private List<Integer> list;
    /**
     * used to opreate the database.
     */
    private CacheManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new CacheManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bargraph_layout, container, false);
        /**
         * Show ads from admob.
         */
        AdView adView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        dateSpinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(arrayAdapter);
        barGraph = (BarGraph) view.findViewById(R.id.bargraph);
        // By default, show the count on three days.
        list = manager.getThreeDaysCount(new Date());
        if (BuildConfig.DEBUG) {
            Log.d("ListSize", String.valueOf(list.size()));
        }
        // Set the data range by the spinner.
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        // 3 days.
                        list = manager.getThreeDaysCount(new Date());
                        drawGraph(list);
                        barGraph.invalidate();
                        if (BuildConfig.DEBUG) {
                            Log.d("Draw", "3 days");
                        }
                        break;
                    case 1:
                        // a week.
                        list = manager.getOneWeekCount(new Date());
                        drawGraph(list);
                        barGraph.invalidate();
                        if (BuildConfig.DEBUG) {
                            Log.d("Draw", "A week");
                        }
                        break;
                    case 2:
                        // a month.
                        list = manager.getOneMonthCount(new Date());
                        drawGraph(list);
                        barGraph.invalidate();
                        if (BuildConfig.DEBUG) {
                            Log.d("Draw", "A month");
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        if (BuildConfig.DEBUG) {
//            ArrayList<Bar> list = new ArrayList<Bar>();
//            Bar b1 = new Bar();
//            b1.setColor(Color.CYAN);
//            b1.setName("Bar 1");
//            b1.setValue(20);
//            list.add(b1);
//            Bar b2 = new Bar();
//            b2.setColor(Color.GRAY);
//            b2.setName("Bar 2");
//            b2.setValue(30);
//            list.add(b2);
//            Bar b3 = new Bar();
//            b3.setColor(Color.YELLOW);
//            b3.setName("Bar 3");
//            b3.setValue(15);
//            list.add(b3);
//            barGraph.setBars(list);
//        }
        return view;
    }

    /**
     * Draw the graph using the given data list.
     * Must be called in onCreateView().
     *
     * @param list
     */
    private void drawGraph(List<Integer> list) {
        int days = list.size();
        ArrayList<Bar> bars = new ArrayList<Bar>();
        for (int i = 0; i < days; ++i) {
            Bar bar = new Bar();
            bar.setColor(getRandomColor());
            bar.setName("");
            bar.setValue(list.get(i));
            bars.add(bar);
        }
        barGraph.setBars(bars);
    }

    /**
     * generate random color.
     *
     * @return
     */
    private int getRandomColor() {
        Random random = new Random();
        return (0xff000000 | random.nextInt(0x00ffffff));
    }
}
