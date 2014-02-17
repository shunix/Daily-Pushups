package com.shunix.dailypushups.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.shunix.dailypushups.R;
import com.shunix.dailypushups.fragments.BarGraphFragment;

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
        getFragmentManager().beginTransaction().replace(R.id.container, new BarGraphFragment()).commit();
    }

    /**
     * Create Action Bar menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
