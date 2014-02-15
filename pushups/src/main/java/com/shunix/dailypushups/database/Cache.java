package com.shunix.dailypushups.database;

import android.content.Context;
import android.util.Log;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;
import com.orm.androrm.field.DateField;
import com.orm.androrm.field.IntegerField;
import com.shunix.dailypushups.BuildConfig;

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
    // FIXME Can't use DateField here, it will make the time a part of the field.
    protected DateField dateField;
    /**
     * used to save the count of current day.
     */
    protected IntegerField pushupCount;

    /**
     * used as a key for index. Androrm only support string filter.
     */
    protected CharField dateString;

    public Cache() {
        dateField = new DateField();
        pushupCount = new IntegerField();
        dateString = new CharField();
        dateString.set("");
    }

    public void setDate(Date date) {
        dateField.set(date);
        dateString.set(date.toString());
        if (BuildConfig.DEBUG) {
            Log.d("DateField", dateField.getDateString());
            Log.d("DateString", dateString.get());
        }
    }

    public Date getDate() {
        return dateField.get();
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
