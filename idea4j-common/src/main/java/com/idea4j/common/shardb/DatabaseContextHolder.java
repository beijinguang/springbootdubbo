package com.idea4j.common.shardb;

/**
 * Created by markee on 2017/1/4.
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDatabaseKey(String key) {
        contextHolder.set(key);
    }

    public static final String getDatabaseKey() {
        return contextHolder.get();
    }

    public static void removeDatabaseKey() {
        contextHolder.remove();
    }
}
