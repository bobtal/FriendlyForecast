package com.teamtreehouse.friendlyforecast.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teamtreehouse.friendlyforecast.services.Forecast;

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
    public void insertForecast(Forecast forecast) {
        ContentValues values = new ContentValues();
        values.put(ForecastHelper.COLUMN_TEMPERATURE, 75.0);
        database.insert(ForecastHelper.TABLE_TEMPERATURES, null, values);
    }

    // select

    // update

    // delete
}
