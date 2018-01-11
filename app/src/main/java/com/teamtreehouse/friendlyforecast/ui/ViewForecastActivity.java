package com.teamtreehouse.friendlyforecast.ui;

import android.app.ActionBar;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.teamtreehouse.friendlyforecast.R;
import com.teamtreehouse.friendlyforecast.db.ForecastDataSource;
import com.teamtreehouse.friendlyforecast.db.ForecastHelper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;


public class ViewForecastActivity extends ListActivity {

    protected ForecastDataSource dataSource;
    protected ArrayList<BigDecimal> mTemperatures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_forecast);

        configureActionBar();

        dataSource = new ForecastDataSource(ViewForecastActivity.this);
        mTemperatures = new ArrayList<BigDecimal>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();

        Cursor cursor = dataSource.selectAllTemperatures();
        updateList(cursor);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    protected void updateList(Cursor cursor) {
        mTemperatures.clear();

        // TODO: Loop through cursor to populate mTemperatures
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            // do stuff
            int i = cursor.getColumnIndex(ForecastHelper.COLUMN_TEMPERATURE);
            double temperature = cursor.getDouble(i);
            mTemperatures.add(new BigDecimal(temperature, MathContext.DECIMAL32));

            cursor.moveToNext();
        }

        ArrayAdapter<BigDecimal> adapter = new ArrayAdapter<BigDecimal>(ViewForecastActivity.this,
                android.R.layout.simple_list_item_1,
                mTemperatures);

        setListAdapter(adapter);
    }

    protected void filterTemperatures(String minTemp) {
        // TODO: Select greater than
        //updateList(cursor);
    }

    protected void configureActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.ab_filter);

        final EditText minTempField = (EditText) actionBar.getCustomView().findViewById(R.id.minTempField);
        minTempField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                filterTemperatures(minTempField.getText().toString());
                return false;
            }
        });

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
    }
}
