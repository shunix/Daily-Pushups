package com.shunix.dailypushups.database;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.DateField;
import com.orm.androrm.field.IntegerField;

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
    protected DateField dateField;
    /**
     * used to save the count of current day.
     */
    protected IntegerField pushupCount;

    /**
     * used as a key for index. Androrm only support string filter.
     */
    protected String dateString;

    public Cache() {
        dateField = new DateField();
        pushupCount = new IntegerField();
        dateString = "";
    }

    public void setDate(Date date) {
        dateField.set(date);
        dateString = date.toString();
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
     * @param context
     * @return
     */
    public static final QuerySet<Cache> objects(Context context) {
        return objects(context, Cache.class);
    }
}
