package com.shunix.dailypushups.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.shunix.dailypushups.BuildConfig;
import com.shunix.dailypushups.R;

/**
 * @author Ray WANG <admin@shunix.com>
 * @version 1.0.0
 * @since Feb 10th, 2014
 */
public class LineGraphFragment extends Fragment {

    /**
     * used as the view holder.
     */
    private LinearLayout linearLayout;
    /**
     * used as the date picker.
     */
    private Spinner dateSpinner;

    /**
     * @return the init view.
     */
    private View getView(Context context) {
        linearLayout = new LinearLayout(context);
        final int textSize = PanelFragment.dpToPx(context, 12);
        /**
         * Init the spinner.
         */
        dateSpinner = new Spinner(context);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(context, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(adapter);
        /**
         * Set listener for date spinner.
         */
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                ((TextView) view).setTextSize(textSize);
                switch (i) {
                    case 0:
                        if (BuildConfig.DEBUG) {
                            Log.d("DateSpinner", "3 days");
                        }
                        break;
                    case 1:
                        if (BuildConfig.DEBUG) {
                            Log.d("DateSpinner", "1 week");
                        }
                        break;
                    case 2:
                        if (BuildConfig.DEBUG) {
                            Log.d("DateSpinner", "1 month");
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
        linearLayout.setPadding(PanelFragment.dpToPx(context, 10), 0, PanelFragment.dpToPx(context, 10), 0);
        dateSpinner.setPadding(0, PanelFragment.dpToPx(context, 20), 0, 0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, PanelFragment.dpToPx(context, 60));
        linearLayout.addView(dateSpinner, params);
        return linearLayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getView(getActivity());
    }
}
