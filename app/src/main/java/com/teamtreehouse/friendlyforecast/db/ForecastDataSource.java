package com.teamtreehouse.friendlyforecast.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ForecastDataSource {

    private SQLiteDatabase database;
    private ForecastHelper forecastHelper;
    private Context context;

    public ForecastDataSource(Context context) {
        this.context = context;
        forecastHelper = new ForecastHelper(context);
    }

    // open a database
    public void open() throws SQLException {
        database = forecastHelper.getWritableDatabase();
    }

    // close a database
    public void close() {
        database.close();
    }
    // insert

    // select

    // update

    // delete
}
