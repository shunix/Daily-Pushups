package com.shunix.dailypushups.database;

import android.content.Context;
import android.util.Log;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;
import com.orm.androrm.field.IntegerField;
import com.shunix.dailypushups.BuildConfig;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author Ray WANG <admin@shunix.com>
 * @version 1.0.0
 * @since Feb 16th, 2014
 */
public class Cache extends Model {
    /**
     * used to save the current date.
     */
    protected CharField date;
    /**
     * used to save the count of current day.
     */
    protected IntegerField pushupCount;

    public Cache() {
        pushupCount = new IntegerField();
        date = new CharField();
    }

    public void setDate(Date date) {
        DateFormat format = DateFormat.getDateInstance();
        this.date.set(format.format(date));
        if (BuildConfig.DEBUG) {
            Log.d("DateString", this.date.get());
        }
    }

    public String getDate() {
        return date.get();
    }

    public void setCount(int count) {
        pushupCount.set(count);
    }

    public int getCount() {
        return pushupCount.get();
    }

    /**
     * required by androrm.
     *
     * @param context
     * @return
     */
    public static final QuerySet<Cache> objects(Context context) {
        return objects(context, Cache.class);
    }
}
