package com.shunix.dailypushups.database;

import android.content.Context;

import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Filter;
import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;

import java.util.ArrayList;
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
        filter.is("dateString", date.toString());
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
            for (Cache cache : caches) {
                preCount = cache.getCount();
            }
            // Save the data now.
            Cache cache = new Cache();
            cache.setDate(date);
            cache.setCount(count + preCount);
        }
    }

    /**
     * used to retrieve the count for the specified date.
     */
    public int getCount(Date date) {
        int count = 0;
        Filter filter = new Filter();
        filter.is("dateString", date.toString());
        QuerySet<Cache> caches = Cache.objects(context).filter(filter);
        if (caches.isEmpty()) {
            return 0;
        }
        for (Cache cache : caches) {
            count = cache.getCount();
        }
        return count;
    }
}
