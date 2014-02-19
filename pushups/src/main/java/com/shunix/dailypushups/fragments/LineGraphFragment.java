package com.shunix.dailypushups.fragments;

import android.app.Fragment;
import android.graphics.Color;
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
import com.shunix.dailypushups.ui.Line;
import com.shunix.dailypushups.ui.LineGraph;
import com.shunix.dailypushups.ui.LinePoint;

import java.util.Date;
import java.util.List;

/**
 * @author Ray WANG <admin@shunix.com>
 * @version 1.0.0
 * @since Feb 10th, 2014
 */
public class LineGraphFragment extends Fragment {

    /**
     * used as the date range picker.
     */
    private Spinner dateSpinner;
    /**
     * used to show the graph.
     */
    private LineGraph lineGraph;
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
        View view = inflater.inflate(R.layout.linegraph_layout, container, false);
        /**
         * Show ads from admob.
         */
        AdView adView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("02D10F4BB9960878C9265F72D88BC40F").build();
        adView.loadAd(adRequest);
        dateSpinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(arrayAdapter);
        lineGraph = (LineGraph) view.findViewById(R.id.linegraph);
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
                        lineGraph.removeAllLines();
                        list = manager.getThreeDaysCount(new Date());
                        drawGraph(list);
                        lineGraph.invalidate();
                        if (BuildConfig.DEBUG) {
                            Log.d("Draw", "3 days");
                        }
                        break;
                    case 1:
                        // a week.
                        lineGraph.removeAllLines();
                        list = manager.getOneWeekCount(new Date());
                        drawGraph(list);
                        lineGraph.invalidate();
                        if (BuildConfig.DEBUG) {
                            Log.d("Draw", "A week");
                        }
                        break;
                    case 2:
                        // a month.
                        lineGraph.removeAllLines();
                        list = manager.getOneMonthCount(new Date());
                        drawGraph(list);
                        lineGraph.invalidate();
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
//            /**
//             * Show the graph.
//             */
//            Line line = new Line();
//            LinePoint p1 = new LinePoint();
//            p1.setX(0);
//            p1.setY(20);
//            line.addPoint(p1);
//            LinePoint p2 = new LinePoint();
//            p2.setX(1);
//            p2.setY(30);
//            line.addPoint(p2);
//            LinePoint p3 = new LinePoint();
//            p3.setX(2);
//            p3.setY(15);
//            line.addPoint(p3);
//            line.setColor(Color.CYAN);
//            lineGraph.addLine(line);
//            lineGraph.setRangeY(0, 35);
//            lineGraph.setLineToFill(0);
//        }
        drawGraph(list);
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
        Line line = new Line();
        for (int i = 0; i < days; ++i) {
            LinePoint point = new LinePoint();
            point.setX((double) i);
            point.setY((double) list.get(i));
            line.addPoint(point);
        }
        line.setColor(Color.CYAN);
        lineGraph.addLine(line);
        // In case there're some zero count in last few days. Set the min to -5 will make it look normal.
        lineGraph.setRangeY(-5, getMax(list) + 5);
        lineGraph.setLineToFill(0);
    }

    /**
     * return the max element in the given list.
     *
     * @param list
     * @return
     */
    private int getMax(List<Integer> list) {
        int max = 0;
        int size = list.size();
        for (int i = 0; i < size; ++i) {
            if (max < list.get(i)) {
                max = list.get(i);
            }
        }
        return max;
    }
}
