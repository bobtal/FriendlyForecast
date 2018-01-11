package com.teamtreehouse.friendlyforecast.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teamtreehouse.friendlyforecast.services.Forecast;

import java.security.cert.CRLSelector;

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
        database.beginTransaction();

        try {
            for (Forecast.HourData hour : forecast.hourly.data) {
                ContentValues values = new ContentValues();
                values.put(ForecastHelper.COLUMN_TEMPERATURE, hour.temperature);
                database.insert(ForecastHelper.TABLE_TEMPERATURES, null, values);
            }
            database.setTransactionSuccessful();
        }
        finally {
            database.endTransaction();
        }
    }

    // select
    public Cursor selectAllTemperatures() {
        Cursor cursor = database.query(
                ForecastHelper.TABLE_TEMPERATURES, //table
                new String[] { ForecastHelper.COLUMN_TEMPERATURE }, //column name(s)
                null, // where clause
                null, // where parameter(s)
                null, // group by
                null, //having
                null // order by
        );

        return cursor;
    }

    public Cursor selectTempsGreaterThan(String minTemp) {
        String whereClause = ForecastHelper.COLUMN_TEMPERATURE + " > ?";

        Cursor cursor = database.query(
                ForecastHelper.TABLE_TEMPERATURES, //table
                new String[] { ForecastHelper.COLUMN_TEMPERATURE }, //column name(s)
                whereClause, // where clause
                new String[] { minTemp }, // where parameter(s)
                null, // group by
                null, //having
                null // order by
        );

        return cursor;
    }

    // update

    // delete
}
