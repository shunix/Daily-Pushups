package com.shunix.dailypushups.ui;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;

import com.shunix.dailypushups.R;

/**
 * @author Ray WANG <admin@shunix.com>
 * @version 1.0.0
 * @since Feb 17th, 2014
 */
public class SeekBarPreference extends DialogPreference implements SeekBar.OnSeekBarChangeListener {

    /**
     * show the seek bar.
     */
    private SeekBar seekBar;

    /**
     * Indicates the current seek bar value.
     */
    private int barValue = 0;

    public SeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setProgress(barValue);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        if (positiveResult) {
            persistInt(barValue);
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        barValue = seekBar.getProgress();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    }
}
