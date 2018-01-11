package com.teamtreehouse.friendlyforecast.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ForecastDataSource {

    private SQLiteDatabase database;
    private ForecastHelper forecastHelper;
    private Context context;

    public ForecastDataSource(Context context) {
        this.context = context;
        forecastHelper = new ForecastHelper(context);
    }
}
