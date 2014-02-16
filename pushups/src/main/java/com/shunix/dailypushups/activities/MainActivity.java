package com.shunix.dailypushups.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.shunix.dailypushups.R;
import com.shunix.dailypushups.fragments.LineGraphFragment;

/**
 * @author Ray WANG <admin@shunix.com>
 * @version 1.0.0
 * @since Feb 9th, 2014
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        getFragmentManager().beginTransaction().replace(R.id.container, new LineGraphFragment()).commit();
    }

    /**
     * Create Action Bar menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
