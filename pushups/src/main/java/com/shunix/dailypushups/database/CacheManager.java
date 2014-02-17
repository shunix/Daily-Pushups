package com.shunix.dailypushups.database;

import android.content.Context;
import android.util.Log;

import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Filter;
import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.shunix.dailypushups.BuildConfig;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Ray WANG <admin@shunix.com>
 * @version 1.0.0
 * @since Feb 16th, 2014
 */
public class CacheManager {

    /**
     * used to store the context for further use.
     */
    private Context context;
    /**
     * used to operate the database.
     */
    private DatabaseAdapter databaseAdapter;

    /**
     * used to save the database name.
     */
    private static final String DATABASE_NAME = "cache";

    public CacheManager(Context context) {
        this.context = context;
        /**
         * Set the database name and init the database adapter.
         */
        DatabaseAdapter.setDatabaseName(DATABASE_NAME);
        databaseAdapter = DatabaseAdapter.getInstance(context);
        List<Class<? extends Model>> models = new ArrayList<Class<? extends Model>>();
        models.add(Cache.class);
        databaseAdapter.setModels(models);
    }

    /**
     * Call this method before you do something to the database. You only need to call this method once.
     */
    public void beginTransaction() {
        databaseAdapter.beginTransaction();
    }

    /**
     * Call this method when everything have been done to the database. You only need to call this method once.
     */
    public void endTransaction() {
        databaseAdapter.commitTransaction();
        databaseAdapter.close();
    }

    /**
     * used to save an cache object.
     */
    public void saveCache(Date date, int count) {
        /**
         * Query the database to see if record exist in current day.
         */
        Filter filter = new Filter();
        DateFormat format = DateFormat.getDateInstance();
        filter.is("date", format.format(date));
        QuerySet<Cache> caches = Cache.objects(context).filter(filter);
        /**
         * No record for today.
         */
        if (caches.isEmpty()) {
            // Save the data directly.
            Cache cache = new Cache();
            cache.setDate(date);
            cache.setCount(count);

            cache.save(context);
        } else {
            // Get records for today.
            // Need add the previous count to the current one.
            // In fact, this loop will only be executed once.
            int preCount = 0;
            if (BuildConfig.DEBUG) {
                Log.d("CachesCount", String.valueOf(caches.count()));
            }
            for (Cache cache : caches) {
                preCount = cache.getCount();
                if (BuildConfig.DEBUG) {
                    Log.d("PreCount", String.valueOf(preCount));
                }
            }
            // Save the data now.
            // Delete data before saving.
//            for (Cache cache : caches) {
//                cache.delete(context);
//            }
            Cache cache = new Cache();
            cache.setDate(date);
            cache.setCount(count + preCount);
            cache.save(context);
        }
    }

    /**
     * used to retrieve the count for the specified date.
     */
    public int getCount(Date date) {
        int count = 0;
        Filter filter = new Filter();
        DateFormat format = DateFormat.getDateInstance();
        filter.is("date", format.format(date));
        QuerySet<Cache> caches = Cache.objects(context).filter(filter);
        if (caches.isEmpty()) {
            return 0;
        }
        for (Cache cache : caches) {
            count = cache.getCount();
        }
        return count;
    }

    /**
     * used to get the count in last 3 days.
     */
    public List<Integer> getThreeDaysCount(Date date) {
        List<Integer> list = new ArrayList<Integer>();
        Date tempDate;
        Calendar calendar = Calendar.getInstance();
        this.beginTransaction();
        for (int i = 2; i >= 0; ++i) {
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -i);
            tempDate = calendar.getTime();
            list.add(this.getCount(tempDate));
        }
        this.endTransaction();
        return list;
    }

    /**
     * used to get the count in last week.
     */
    public List<Integer> getOneWeekCount(Date date) {
        List<Integer> list = new ArrayList<Integer>();
        Date tempDate;
        Calendar calendar = Calendar.getInstance();
        this.beginTransaction();
        for (int i = 6; i >= 0; ++i) {
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -i);
            tempDate = calendar.getTime();
            list.add(this.getCount(tempDate));
        }
        this.endTransaction();
        return list;
    }

    /**
     * used to get the count in last month, one month is regarded as 30 days here.
     */
    public List<Integer> getOneMonthCount(Date date) {
        List<Integer> list = new ArrayList<Integer>();
        Date tempDate;
        Calendar calendar = Calendar.getInstance();
        this.beginTransaction();
        for (int i = 30; i >= 0; ++i) {
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -i);
            tempDate = calendar.getTime();
            list.add(this.getCount(tempDate));
        }
        this.endTransaction();
        return list;
    }
}
